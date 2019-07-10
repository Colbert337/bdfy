package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.soap.Parser;
import com.start.sst.soap.soapentity.SoapResponse;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.MapUtil;
import com.ylzinfo.model.his.zy.yjjcz.dao.EylzPayDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class WxpayChargeService {
	
	private Logger log=null;
	
	public WxpayChargeService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public String WxpayFinishCharge(Map para)throws SSTException{
		Connection conn = null;
		EylzPayDao dao = new EylzPayDao();
		String jylsh0 = (String)MapUtil.getValue(para, "jylsh0", "交易流水号", true);
		String cxdm00 = (String)MapUtil.getValue(para, "cxdm00", "卡号", true);
		log.info("["+jylsh0+"]调用his接口进行微信充值");
		Map hisRet = null;
		try{
			conn = DBUtil.getConnection();
			
			Map wxczmx = dao.queryYjjWxcz(conn,jylsh0,cxdm00);
			if(wxczmx == null||wxczmx.size()<=0){
				throw new SSTException("没有该订单"+jylsh0+"信息");
			}
			
			String czztbz = (String)wxczmx.get("czztbz");
			if(czztbz.equals("6")) {
				throw new SSTException("该交易已经成功");
			} else if(!(czztbz.equals("2")|| czztbz.equals("7"))) {
				throw new SSTException("该交易无法进行");
			}
			
			para.put("czlx00", "WC");
			para.put("yhkhao", jylsh0);
			para.put("type00", "1");
			para.put("ddh000", wxczmx.get("ptddls"));
			CallHisService ws = new CallHisService("028");
			hisRet = ws.callWs(para);
			String result = (String)hisRet.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)hisRet.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "未知错误";
				}
				para.put("czztbz", "7");
				para.put("errmsg", errmsg);
				dao.updateZfbczCzztbz(conn, para);
				throw new SSTException(errmsg);
			}
			
			hisRet.put("cgjysj", DateUtil.getSystemDateTime());
			
			para.put("czztbz", "6");
			para.put("errmsg", "his确认成功");
			dao.updateWxczCzztbz(conn, para);
			
			Vector vt = new Vector();
			vt.add(hisRet);
			
			HashMap<String, Object> evtBody = new HashMap<String, Object>();
		    SoapResponse evtResponse = new SoapResponse();
		    evtBody.put("retrieve", vt);
		    evtResponse.setBody(evtBody);
		    evtResponse.setMsg("OK");
		    evtResponse.setSucessFlag(true);
		    Parser parser = new Parser();
	        return  parser.formatSoap(evtResponse);
		}catch (Exception e) {
			para.put("czztbz", "7");
			para.put("errmsg", "his确认失败");
			dao.updateWxczCzztbz(conn, para);
			e.printStackTrace();
			log.info("通知his异常:"+e.getMessage());
			throw new SSTException("通知his异常:"+e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
