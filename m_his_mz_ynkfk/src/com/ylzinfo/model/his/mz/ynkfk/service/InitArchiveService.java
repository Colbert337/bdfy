/**
 * 
 */
package com.ylzinfo.model.his.mz.ynkfk.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ynkfk.dao.ZzfkDao;

/**
 * @description: ������ʼ��
 * @author wujf
 * @date: 2015-9-9 ����09:46:28
 */
public class InitArchiveService {
	
	private Logger logger = null;
	
	private String errmsg="";
	
	public InitArchiveService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainInitArchive(Map params) throws SSTException{
		Vector<HashMap<String, String>> vt = null;
		Connection ylzConn = null;
		ZzfkDao dao = new ZzfkDao();
		try {
			logger.info("***************Ժ�ڿ�������ʼ����ʼ***************");
			
			//�������
			ylzConn = DBUtil.getConnection();
			//��������
			ylzConn.setAutoCommit(false);
			
			String jylsh0 = dao.getJylsh(ylzConn);
			logger.info("��ȡ������ˮ��..."+jylsh0);
			params.put("jylsh0", jylsh0);
			
			String cshsj0 = DateUtil.getSystemDateTime();// ��ȡϵͳʱ��yyyy-MM-dd HH:mm:ss
			params.put("cshsj0", cshsj0);
			
			params.put("ycmklx", "2");
			params.put("czqx00", "1"); //��ֵȥ�� 1 Ժ�� 2 ����ͨ
		
			logger.info("����д��Ԥ�����˻���ֵ��ϸ��...");
			dao.insertSstYjjZhczmx(ylzConn, params);
			
			String dblsh0 = dao.getDblsh0(ylzConn);
			logger.info("��ȡ������ˮ��..."+dblsh0);
			params.put("dblsh0", dblsh0);
			
			logger.info("����д��Ԥ���𵥱ʳ�ֵ��ϸ��...");
			params.put("zzs000", "1");
			params.put("dzjec0", params.get("czje00"));
			params.put("cpxrsj",cshsj0);
			dao.insertSstYjjDbczmx(ylzConn, params);
			
			// ���н�����ʼ������������ý�����ˮ��
			String jdlsh0 = dao.getJDLSH(ylzConn);//������ˮ��
			logger.info("��ȡ������ˮ��..."+jdlsh0);
			
			logger.info("���뽨����ʼ������...");
			params.put("jdlsh0",jdlsh0); //������ˮ��
			params.put("zcycje", params.get("czje00"));
			params.put("gbfei0", "0");
			params.put("jdkssj", cshsj0); //��ʼ��ʱ��
			dao.doJdcsh(ylzConn, params);
			
			//�ύ����
			ylzConn.commit();
			
			logger.info(" ���ؽ�����ˮ����Ϣ����װ���ݣ�...");
			vt = new Vector<HashMap<String, String>>();
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("jdlsh0", jdlsh0);
			map.put("jylsh0", jylsh0);
			map.put("dblsh0", dblsh0);
			vt.addElement(map);
			logger.info("***************[Ժ�ڿ�������ʼ������***************");
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception e) {
			DBUtil.rollback(ylzConn);
			errmsg = "��������, ������������ϵ������Ա!";
			logger.error(errmsg+e.getMessage()+"��������ع�");
			throw new SSTException(errmsg+e.getMessage());
		} finally {
			logger.info("�رձ������ݿ�����");
			DBUtil.closeConnection(ylzConn);
		}
	}
}
