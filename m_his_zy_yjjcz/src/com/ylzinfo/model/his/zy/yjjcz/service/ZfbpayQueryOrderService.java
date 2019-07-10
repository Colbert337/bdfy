package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.soap.Parser;
import com.start.sst.soap.soapentity.SoapResponse;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.ylzinfo.model.his.zy.yjjcz.config.AlipayConfig;
import com.ylzinfo.model.his.zy.yjjcz.dao.EylzPayDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallDxService;

public class ZfbpayQueryOrderService {

	private Logger log=null;
	
	public ZfbpayQueryOrderService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	/**
	* 
	* @Description: ��ѯ֧��������״̬
	* @param para
	* @throws SSTException 
	* @throws
	*/
	public String zfbpayQueryOrder(Map para)throws SSTException{
		Connection conn = null;
		EylzPayDao dao = new EylzPayDao();
		String jylsh0 = "";
		String cxdm00 = "";
		String czje00 = "";
		String ptddls = "";
		String czztbz = "";
		String jyzt00 = "";
		String zfblsh = "";
		String cgjysj = "";
		try{
			conn = DBUtil.getConnection();
			jylsh0 = (String)para.get("jylsh0");
			cxdm00 = (String)para.get("cxdm00");
			log.info("====================["+jylsh0+"]֧������ѯ������ʼ=====================");
			Map zfbczmx = dao.queryYjjZfbcz(conn,jylsh0,cxdm00);
			if(zfbczmx == null||zfbczmx.size()<=0){
				throw new SSTException("û�иö���"+jylsh0+"��Ϣ");
			}
			czje00 = (String)zfbczmx.get("czje00");
			ptddls = (String)zfbczmx.get("ptddls");
			czztbz = (String)zfbczmx.get("czztbz");
			if(!("2".equals(czztbz)||"1".equals(czztbz))){
				para.put("ptddls", ptddls);
				
				CallDxService ws = new CallDxService();
				Map map = ws.RealOnePayQueryPay(para);
				
				jyzt00 = (String)map.get("code");
				
				if (jyzt00.equals("SUCCESS")) {
					czztbz = "2";
					para.put("czztbz", czztbz);
					zfblsh = (String)map.get("fhlsh0");
					para.put("zfblsh", zfblsh);
					cgjysj = DateUtil.getSystemDateTime();
					para.put("ddjysj", cgjysj);
					para.put("cgjysj", cgjysj);
					dao.updateZfbcz2Success(conn, para);
				}
			} else {
				cgjysj = (String)zfbczmx.get("ddjysj");
				zfblsh = (String)zfbczmx.get("zfblsh");
			}
			Map res = new HashMap();
			Vector vt = new Vector();
			res.put("czztbz", czztbz);
			res.put("czje00", czje00);
			res.put("zfjyls", zfblsh);
			res.put("zfjysj", cgjysj);
			vt.add(res);
			HashMap<String, Object> evtBody = new HashMap<String, Object>();
	        SoapResponse evtResponse = new SoapResponse();
	        evtBody.put("retrieve", vt);
	        evtResponse.setBody(evtBody);
	        evtResponse.setMsg("OK");
	        evtResponse.setSucessFlag(true);
	        log.info("====================["+jylsh0+"]֧������ѯ��������=====================");
	        Parser parser = new Parser();
	        return  parser.formatSoap(evtResponse);
		}catch (Exception e) {
			log.error("["+jylsh0+"]֧������ѯ����ʧ��"+e.getMessage());
			throw new SSTException("["+jylsh0+"]֧������ѯ����ʧ��"+e.getMessage());
		}finally{
				DBUtil.closeConnection(conn);
		}
	}
}
