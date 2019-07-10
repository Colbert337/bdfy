package com.ylzinfo.model.his.mz.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.soap.Parser;
import com.start.sst.soap.soapentity.SoapResponse;
import com.start.sst.util.DBUtil;
import com.ylzinfo.model.his.mz.yjjcz.dao.EylzPayDao;

public class WxpayRefundService {
	
	private Logger log=null;
	
	public WxpayRefundService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	/**
	* 
	* @Description: 微信退款
	* @param para
	* @return
	* @throws SSTException 
	* @throws
	*/
	public String wxpayRefund(Map para)throws SSTException{
		Connection conn = null;
		EylzPayDao dao = new EylzPayDao();
		String jylsh0 = "";
		String cxdm00 = "";
		String ptddls = "";
		String czztbz = "";
		String tklsh0 = "";
		try{
			conn = DBUtil.getConnection();
			jylsh0 = (String)para.get("jylsh0");
			cxdm00 = (String)para.get("cxdm00");
			log.info("====================["+jylsh0+"]微信退款开始=====================");
			Map wxczmx = dao.queryYjjWxcz(conn,jylsh0,cxdm00);
			if(wxczmx == null||wxczmx.size()<=0){
				throw new SSTException("没有该订单"+jylsh0+"信息");
			}
			ptddls = (String)wxczmx.get("ptddls");
			czztbz = (String)wxczmx.get("czztbz");
			if(!("1".equals(czztbz)||"3".equals(czztbz))){
				para.put("ptddls", ptddls);
				tklsh0 = dao.getPtqqls(conn);
				para.put("tklsh0", tklsh0);
				para.put("sfcz00", "1");
				dao.updateWxczTklsh(conn, para);
				
				//调用退费
				
				para.put("czztbz", "3");
				para.put("errmsg", "退款成功！");
				dao.updateWxczCzztbz(conn, para);
			} else {
				
			}
			HashMap<String, Object> evtBody = new HashMap<String, Object>();
	        SoapResponse evtResponse = new SoapResponse();
	        evtResponse.setBody(evtBody);
	        evtResponse.setMsg("OK");
	        evtResponse.setSucessFlag(true);
	        log.info("====================["+jylsh0+"]微信退款结束=====================");
	        Parser parser = new Parser();
	        return  parser.formatSoap(evtResponse);
		} catch (Exception e) {
			log.error("["+jylsh0+"]微信退款失败"+e.getMessage());
			throw new SSTException("["+jylsh0+"]微信退款失败"+e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
