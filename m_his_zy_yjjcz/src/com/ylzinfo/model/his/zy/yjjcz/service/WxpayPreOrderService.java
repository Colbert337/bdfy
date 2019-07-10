package com.ylzinfo.model.his.zy.yjjcz.service;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.log4j.Logger;

import basesst.SstBaseBundleUtil;

import com.start.sst.exception.SSTException;
import com.start.sst.soap.Parser;
import com.start.sst.soap.soapentity.SoapResponse;
import com.start.sst.util.Base64Util;
import com.start.sst.util.DBUtil;
import com.ylzinfo.model.his.zy.yjjcz.dao.EylzPayDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallDxService;

public class WxpayPreOrderService {
	
	private Logger log=null;
	
	public WxpayPreOrderService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 元转换成分
	 * 
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if (amount == null) {
			return "";
		}
		// 金额转化为分为单位
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
		// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "")
					+ "00");
		}
		return amLong.toString();
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
