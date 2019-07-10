package com.ylzinfo.model.his.zy.jbxx.service;

import java.sql.Connection;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.SoapUtil;
import com.start.sst.util.StringUtil;
import com.ylzinfo.model.his.zy.jbxx.dao.GrjbxxDao;
/**
 * 
* @Project: m_his_zy_jbxx
* @ClassName: GetPrivateAttributesService 
* @Description: ��ȡ˽������ҵ���
* @author huangjj
* @date 2015-11-11 ����10:08:17 
*
 */
public class GetPrivateAttributesService {
	private Logger logger;
	public GetPrivateAttributesService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String getPrivateAttributes(Map params) throws SSTException{
		Connection conn = null;
		Vector vt = null;
		String zdbh00 = StringUtil.Null2BlankAndTrim(params.get("zdbh00"));
		try{
			conn = DBUtil.getConnection();
			GrjbxxDao dao = new GrjbxxDao();
			vt = dao.getPrivateAttributes(conn, zdbh00);
		}catch (Exception e) {
			logger.error("��ȡ�ն�˽������ʧ��,"+e.getMessage());
			throw new SSTException("��ȡ�ն�˽������ʧ�ܣ�"+e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
		return SoapUtil.getSoapResponse(vt);
		
	}
}
