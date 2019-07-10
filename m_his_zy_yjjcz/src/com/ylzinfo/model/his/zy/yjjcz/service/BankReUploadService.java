package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.SoapUtil;
import com.start.sst.util.StringUtil;
import com.ylzinfo.model.his.zy.yjjcz.dao.YjjczDao;

public class BankReUploadService {

private Logger logger=null;
	
	public BankReUploadService() {
		logger=Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * ���п���ֵ���ϴ����������п��ܺų�ֵ���ϴ�
	 * @param param
	 * @return
	 * @throws SSTException
	 */
	public String doBankReUpload(Map param)throws SSTException{
		Connection conn=null;
		Vector<HashMap<String, String>> vt = null;
		String zdbh00=StringUtil.Null2BlankAndTrim(param.get("zdbh00"));
		String cxdm00=StringUtil.Null2BlankAndTrim(param.get("cxdm00"));
		YjjczDao dao = new YjjczDao();
		String logMsg = "�ն˱�ţ�" + zdbh00 + " ���ţ�" + cxdm00 + "--";
		try{
			logger.info(logMsg+"1.�������г�ֵ��Ϣ�����ݿ��У�����״̬дΪ������״̬...");			
			conn=DBUtil.getConnection();
			dao.updateJyztToHisFail(conn, param);
			vt=new Vector<HashMap<String, String>>();
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("OK", "���׳ɹ���");
			vt.add(hm);
			logger.info(logMsg + "***************doBankReUploadԤ�������п���ֵ��Ϣ���ϴ�����*************");
			return SoapUtil.getSoapResponse(vt);
		}catch(Exception e){
			logger.info("������Ϣ���ϴ�ʧ��:"+e.getMessage());
			throw new SSTException(e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
