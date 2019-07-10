package com.ylzinfo.model.his.mz.yjjcz.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.soap.Parser;
import com.start.sst.soap.soapentity.SoapResponse;
import com.start.sst.util.DBUtil;
import com.ylzinfo.model.his.mz.yjjcz.dao.EylzPayDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallDxService;

public class WxpayPreOrderService {
	
	private Logger log=null;
	
	public WxpayPreOrderService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 
	* @Description: 微信支付预下单
	* @param para
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String wxpayPreOrder(Map para)throws SSTException{
		Connection conn = null;
		EylzPayDao dao = new EylzPayDao();
		String jylsh0 = "";
		String ptqqls = "";
		String ptddls = "";
		String cshsj0 = "";
		try{
			conn = DBUtil.getConnection();
			ptqqls = dao.getPtqqls(conn) + "BDFY";
			jylsh0 = dao.getWxJylsh(conn);
			log.info("====================["+jylsh0+"]微信预下单开始=====================");
			para.put("jylsh0", jylsh0);
			para.put("ptqqls", ptqqls);
			
			para.put("jylx00", "WX");
			
			CallDxService ws = new CallDxService();
			Map map = ws.RealOneFacePayPre(para);
			
			ptddls = ptqqls;
			para.put("czztbz", "0");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cshsj0 = sf.format(new Date());
			para.put("cshsj0", cshsj0);
			para.put("ptddls", ptddls);
			para.put("ddlx00", "01");
			dao.insertYjjWxcz(conn, para);
			
			Map res = new HashMap();
			Vector vt = new Vector();
			res.put("jylsh0", jylsh0);
			res.put("cshsj0", cshsj0);
			res.put("wxcode", (String)map.get("payurl"));
	        res.put("wxqqls", ptddls);
	        vt.add(res);
	        HashMap<String, Object> evtBody = new HashMap<String, Object>();
	        SoapResponse evtResponse = new SoapResponse();
	        evtBody.put("retrieve", vt);
	        evtResponse.setBody(evtBody);
	        evtResponse.setMsg("OK");
	        evtResponse.setSucessFlag(true);
	        log.info("====================["+jylsh0+"]微信预下单结束=====================");
	        Parser parser = new Parser();
	        return  parser.formatSoap(evtResponse);
		}catch (Exception e) {
			log.error("["+jylsh0+"]微信预下单失败"+e.getMessage());
			throw new SSTException("["+jylsh0+"]微信预下单失败"+e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
