package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.MapUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.dao.DbczmxDao;
import com.ylzinfo.matrix.model.his.zy.yjjcz.dao.ZhczmxDao;

public class CashReUploadService {
	
	private Logger logger=null;
	
	public CashReUploadService() {
		logger=Logger.getLogger(this.getClass().getName());
	}
	
	public String doCashReUpload(Map param)throws SSTException{
		DbczmxDao dbDao=new DbczmxDao();
		ZhczmxDao zhDao=new ZhczmxDao();
		Connection conn=null;
		Map zhczmx=null;
		Vector<HashMap<String, String>> vt = new Vector<HashMap<String, String>>();
		String dblsh0=(String)MapUtil.getValue(param, "dblsh0", "������ˮ��", true);
		String jylsh0 = (String)MapUtil.getValue(param, "jylsh0", "������ˮ��", true);
		String czje00 = (String)MapUtil.getValue(param, "czje00", "��ֵ���", true);
		try {
			conn=DBUtil.getConnection();
			zhczmx=zhDao.getZhczmx(conn, jylsh0);
			if(zhczmx == null || zhczmx.size() ==0){
				throw new SSTException("�Ҳ���������ˮ��Ϊ["+jylsh0+"]���˻���ֵ��ϸ");
			}
			if("0".equals(zhczmx.get("czztbz"))){
				conn.setAutoCommit(false);
				logger.info("��ȡ���ʳ�ֵ��ϸ");
				Map dbczmx = dbDao.queryDbczmxByDblsh0(conn, dblsh0);
				if(dbczmx == null || dbczmx.size() ==0){
					throw new SSTException("�Ҳ���������ˮ��Ϊ["+dblsh0+"]�ĵ��ʳ�ֵ��ϸ");
				}
				if("0".equals(dbczmx.get("cpxrzt")))
				{
					logger.info("���µ��ʳ�ֵ��ϸ��¼�Ľ��Ϊ�½��������״̬�޸�Ϊ���ҳɹ����ۼӳɹ�״̬");
					dbDao.updateDbczmx2Success(conn,dblsh0,czje00);
					logger.info("�ۼӵ��˻���ֵ��ϸ");
					dbDao.addToZhczmx(conn,jylsh0);
					logger.info("["+jylsh0+"]�����˻���ֵ��ϸ��¼Ϊ���ҳɹ���His����ʧ��");
					zhDao.updateCzztbzToWaitRecharge(conn,jylsh0);
				}
				//�ύ����
				conn.commit();
			}
			return SoapUtil.getSoapResponse(vt);
		}catch (Exception e) {
			//����ع�
			String msg="�����ۼ�ʧ��"+e.getMessage()+";����ع�";
			logger.error(msg);
			DBUtil.rollback(conn);
			throw new SSTException(msg,e);
		}finally{
			logger.info("�رձ������ݿ�����");
			DBUtil.closeConnection(conn);
		}
	}
}
