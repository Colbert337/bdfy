package com.ylzinfo.model.his.mz.ynkfk.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.MapUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ynkfk.dao.ZzfkDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @description: ����
 * @author wujf
 * @date: 2015-9-9 ����09:55:54
 */
public class DoArchiveCreditService {
	
	private Logger logger = null;
	
	private String errmsg="";
	
	public DoArchiveCreditService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainDoArchiveCredit(Map params) throws  SSTException{
		Connection conn = null;
		ZzfkDao dao = new ZzfkDao();
		String jdlsh0 = (String)params.get("jdlsh0");
		String jylsh0 = (String)params.get("jylsh0");
		String dblsh0 = (String)params.get("dblsh0");
		Map map = null;
		try {
			logger.info("***************����ȷ�Ͽ�ʼ[������ˮ��:"+jdlsh0+"]***************");
			
			conn = DBUtil.getConnection();
			
			logger.info("��ȡ������ʼ����Ϣ��������ˮ�ţ�" + jdlsh0 + "��...");
			Map cshxx = dao.getJdcshxx(conn, params);
			
			logger.info("���µ��ʳ�ֵ��¼״̬Ϊ���ҳɹ�");
			dao.updateSstYjjDbczmx(conn, dblsh0);
			logger.info("���ʽ���ۼӵ�������...");
			dao.updateSstYjjZhczmx(conn,jylsh0);
			
			String jdzt00 = (String)MapUtil.getValue(cshxx, "jdzt00", "����״̬", false);//����״̬
			if (ZzfkDao.ALLSUCCESS.equals(jdzt00)) {// �������
				throw new SSTException("�����ɹ����������������");
			} else if (jdzt00.equals(ZzfkDao.TKCGYCSB)) { // �¿��ɹ���ѹ��ʧ��
				throw new SSTException("Ǯ���Ѿ��³�������ʧ�ܣ��������������");
			} else {// ������ʼ��
				try {
					logger.info("����HIS������������ˮ�ţ�" +  jdlsh0 + "��...");
					
					cshxx.put("cxfs00", "1");
					
					String xbie00 = (String)cshxx.get("xbie00");
					if(xbie00.equals("1")) {
						cshxx.put("xbie00", "M");
					} else {
						cshxx.put("xbie00", "F");
					}
					
					String csrq00 = (String)cshxx.get("csrq00");
					cshxx.put("csrq00", csrq00.substring(0, 4) + "-" + csrq00.substring(4, 6) + "-" + csrq00.substring(6, 8));
					
					cshxx.put("zy0000","");
					
					cshxx.put("hyzt00","");
					
					cshxx.put("czlx00","CA");
					
					cshxx.put("type00","1");
					cshxx.put("minzu0", (String)params.get("mzubm0"));
					
					CallHisService ws = new CallHisService("002");
					map = ws.callWs(cshxx);
					String result = (String)map.get("result");
					if(!result.equals("1")) {
						String errmsg = (String)map.get("errmsg");
						if(errmsg == null || errmsg.equals("")) {
							errmsg = "δ֪����";
						}
						throw new SSTException(errmsg);
					}
				} catch (Exception e) {
					logger.info("HIS����ʧ�ܣ��޸Ľ���״̬Ϊ����ֵ�ɹ���HIS����ʧ��");
					dao.updateCzztbz(conn, jylsh0, "1");
					dao.updateJdcshJdzt(conn, jdlsh0,ZzfkDao.CZCGHISZCSB);
					throw new SSTException(e.getMessage());
				}
				logger.info("Ԥ�����ֵ�ɹ���HIS�����ɹ����޸�Ԥ�����ֵ״̬����ֵ�ɹ���HIS���³ɹ�");
				String cgjysj = DateUtil.getSystemDateTime();
				dao.updateCzztbzToSuccess(conn, jylsh0, jylsh0, cgjysj, (String)map.get("cxdm00"));
				logger.info("HIS�����ɹ����޸Ľ���״̬");
				dao.updateJdcshJdzt(conn, jdlsh0, ZzfkDao.HISSUCCESS);
			}
			//�ύ��������
			conn.commit();
			logger.info("���½�����ʼ��״̬״̬Ϊ���...");
			String now = DateUtil.getSystemDateTime();
			dao.finishJdcsh(conn, jdlsh0, now, (String)map.get("cxdm00"));
			
			logger.info("���ظ��˻�����Ϣ����װ���ݣ�...");
			Vector<HashMap<String, String>> vt = new Vector<HashMap<String, String>>();
			HashMap<String, String> rtn = new HashMap<String, String>();
			rtn.put("yjjye0", (String)map.get("yjjye0"));
			rtn.put("jdsj00", now);
			rtn.put("gbf000", "0");
			rtn.put("xtgzh0", "");
			rtn.put("brid00", (String)map.get("brid00"));
			rtn.put("cxdm00", (String)map.get("cxdm00"));
			vt.addElement(rtn);
			// ��װ������Ϣ
			logger.info("***************����ȷ�Ͻ���[������ˮ��:"+jdlsh0+"]***************");
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception e) {
			e.printStackTrace();
			errmsg = "����������ʧ�ܣ�";
			logger.error(errmsg+e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		} finally {
			logger.info("�رձ������ݿ�����");
			DBUtil.closeConnection(conn);
		}
	}
}
