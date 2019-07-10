package com.ylzinfo.model.his.mz.yjjcz.service;

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
import com.ylzinfo.model.his.mz.yjjcz.dao.EylzPayDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallDxService;

public class WxpayQueryOrderService {

	private Logger log=null;
	
	public WxpayQueryOrderService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	/**
	* 
	* @Description: 查询微信订单状态
	* @param para
	* @throws SSTException 
	* @throws
	*/
	public String wxpayQueryOrder(Map para)throws SSTException{
		Connection conn = null;
		EylzPayDao dao = new EylzPayDao();
		String jylsh0 = "";
		String cxdm00 = "";
		String czje00 = "";
		String ptddls = "";
		String czztbz = "";
		String jyzt00 = "";
		String ddid00 = "";
		String wxlsh0 = "";
		String cgjysj = "";
		try{
			conn = DBUtil.getConnection();
			jylsh0 = (String)para.get("jylsh0");
			cxdm00 = (String)para.get("cxdm00");
			log.info("====================["+jylsh0+"]微信查询订单开始=====================");
			Map wxczmx = dao.queryYjjWxcz(conn,jylsh0,cxdm00);
			if(wxczmx == null||wxczmx.size()<=0){
				throw new SSTException("没有该订单"+jylsh0+"信息");
			}
			czje00 = (String)wxczmx.get("czje00");
			ptddls = (String)wxczmx.get("ptddls");
			czztbz = (String)wxczmx.get("czztbz");
			if(!("2".equals(czztbz)||"1".equals(czztbz))){
				para.put("ptddls", ptddls);
				
				CallDxService ws = new CallDxService();
				Map map = ws.RealOnePayQueryPay(para);
				
				jyzt00 = (String)map.get("code");
				if(jyzt00.equals("SUCCESS")) {
					czztbz = "2";
					para.put("czztbz", czztbz);
					wxlsh0 = (String)map.get("fhlsh0");
					para.put("wxlsh0", wxlsh0);
					cgjysj = DateUtil.getSystemDateTime();
					para.put("ddjysj", cgjysj);
					para.put("cgjysj", cgjysj);
					dao.updateWxcz2Success(conn, para);
				}
			} else {
				cgjysj = (String)wxczmx.get("ddjysj");
				wxlsh0 = (String)wxczmx.get("wxlsh0");
			}
			Map res = new HashMap();
			Vector vt = new Vector();
			res.put("czztbz", czztbz);
			res.put("czje00", czje00);
			res.put("wxjyls", wxlsh0);
			res.put("wxjysj", cgjysj);
			vt.add(res);
			HashMap<String, Object> evtBody = new HashMap<String, Object>();
	        SoapResponse evtResponse = new SoapResponse();
	        evtBody.put("retrieve", vt);
	        evtResponse.setBody(evtBody);
	        evtResponse.setMsg("OK");
	        evtResponse.setSucessFlag(true);
	        log.info("====================["+jylsh0+"]微信查询订单结束=====================");
	        Parser parser = new Parser();
	        return  parser.formatSoap(evtResponse);
		}catch (Exception e) {
			log.error("["+jylsh0+"]微信查询订单失败"+e.getMessage());
			throw new SSTException("["+jylsh0+"]微信查询订单失败"+e.getMessage());
		}finally{
				DBUtil.closeConnection(conn);
		}
	}
}
