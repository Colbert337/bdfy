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
import com.ylzinfo.service.his.mz.ws.webservice.CallDxService;

public class WxpayCancelOrderService {
	
	private Logger log=null;
	
	public WxpayCancelOrderService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	/**
	* 
	* @Description:΢�ų�������
	* @param para
	* @return
	* @throws SSTException 
	* @throws
	*/
	public String wxpayCancelOrder(Map para)throws SSTException{
		Connection conn = null;
		EylzPayDao dao = new EylzPayDao();
		String jylsh0 = "";
		String cxdm00 = "";
		String ptddls = "";
		String czztbz = "";
		try{
			conn = DBUtil.getConnection();
			jylsh0 = (String)para.get("jylsh0");
			cxdm00 = (String)para.get("cxdm00");
			log.info("====================["+jylsh0+"]΢�ų���������ʼ=====================");
			Map wxczmx = dao.queryYjjWxcz(conn,jylsh0,cxdm00);
			if(wxczmx == null||wxczmx.size()<=0){
				throw new SSTException("û�иö���"+jylsh0+"��Ϣ");
			}
			ptddls = (String)wxczmx.get("ptddls");
			czztbz = (String)wxczmx.get("czztbz");
			if("2".equals(czztbz)){
				throw new SSTException("["+jylsh0+"]��֧���ɹ����ܳ���");
			} else if(!("1".equals(czztbz)||"3".equals(czztbz))){
				para.put("ptddls", ptddls);
				
				CallDxService ws = new CallDxService();
				Map map = ws.RealOneFaceCancelTrade(para);
				
				String code = (String)map.get("code");
				if(!code.equals("SUCCESS")) {
					throw new SSTException("΢�ų�������ʧ��");
				}
				
				para.put("czztbz", "1");
				para.put("errmsg", "�������׳ɹ���");
				dao.updateWxczCzztbz(conn, para);
			} else {
				
			}
			HashMap<String, Object> evtBody = new HashMap<String, Object>();
	        SoapResponse evtResponse = new SoapResponse();
	        evtResponse.setBody(evtBody);
	        evtResponse.setMsg("OK");
	        evtResponse.setSucessFlag(true);
	        log.info("====================["+jylsh0+"]΢�ų�����������=====================");
	        Parser parser = new Parser();
	        return  parser.formatSoap(evtResponse);
		} catch (Exception e) {
			log.error("["+jylsh0+"]΢�ų�������ʧ��"+e.getMessage());
			throw new SSTException("["+jylsh0+"]΢�ų�������ʧ��"+e.getMessage());
		}finally{
				DBUtil.closeConnection(conn);
		}
	}
}
