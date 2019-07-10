package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.CollectMoneyCaculateModelMatrix;
import com.ylzinfo.matrix.model.his.zy.yjjcz.dao.ScsqsjDao;

public class CollectMoneyCaculateService extends CollectMoneyCaculateModelMatrix {
	
	private Logger log = null;
	
	public CollectMoneyCaculateService() {
		log = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public  Vector collectMoneyCaculate(Map param) throws SSTException{
		Connection conn = null;
		Map sqmx = new HashMap();
		String zdbh00 = "";// �ն˱��
		String zdmc00 = "";// �ն�����
		String scsqsj = "";// �ϴ���Ǯʱ��
		String bcsqsj = "";// ������Ǯʱ��
		String cgjybs = "0";// ���ܳɹ����ױ���
		String cgjyje = "0";// ���ܳɹ����׽��
		String hzsbbs = "0";// ����ʧ�ܽ��ױ���
		String hzsbje = "0";// ����ʧ�ܽ��׽��
		String dbsbbs = "0";// ����ʧ�ܱ���
		String dbsbje = "0";// ����ʧ�ܽ��
		String dbzybs = "0";// �����������
		String dbzyje = "0";// ����������
		String message = "";
		try{
			conn = DBUtil.getConnection();
			ScsqsjDao dao = new ScsqsjDao();
			Vector vt = dao.collectMoney(conn, param);
			if(vt==null||vt.size()<=0){
				throw new SSTException("δ�ҵ���Ǯͳ�Ƽ�¼");
			}
			sqmx = (Map)vt.get(0);
			scsqsj = (String)sqmx.get("scsqsj");
			bcsqsj = (String)sqmx.get("bcsqsj");
			zdbh00 = (String)sqmx.get("zdbh00");
			zdmc00 = (String)sqmx.get("zdmc00");
			cgjybs = (String)sqmx.get("cgjybs");
			cgjyje = (String)sqmx.get("cgjyje");
			hzsbbs = (String)sqmx.get("hzsbbs");
			hzsbje = (String)sqmx.get("hzsbje");
			dbsbbs = (String)sqmx.get("dbsbbs");
			dbsbje = (String)sqmx.get("dbsbje");
			dbzybs = (String)sqmx.get("dbzybs");
			dbzyje = (String)sqmx.get("dbzyje");
			
			message = "\\n\n�ն˱�ţ�" + zdbh00 + "\\n\n" + "�ն����ƣ�" + zdmc00 + "\\n\n"
			+ "�ϴ���Ǯʱ�䣺" + scsqsj + "\\n\n" + "������Ǯʱ�䣺" + bcsqsj + "\\n\n" 
			+ "�ɹ����ױ�����" + cgjybs + "\\n\n"+ "�ɹ����׽�" + cgjyje + "\\n\n"
			+ "���ܴ�����������" + hzsbbs + "\\n\n"+ "���ܴ�������" + hzsbje + "\\n\n" 
			+ "���ʴ�����������" + dbsbbs + "\\n\n"+ "���ʴ�������" + dbsbje + "\\n\n" 
			+ "���������������" + dbzybs + "\\n\n"+ "�����������" + dbzyje + "\\n\n\\n\n\\n\n\\n\n";
			try{
				Map map = new HashMap();
				map.put("zdbh00", zdbh00);
				map.put("scsqsj", scsqsj);
				map.put("bcsqsj", bcsqsj);
				map.put("message", message);
				dao.doSqtjLog(conn, map);
			}catch(Exception e){
				log.info("��¼��Ǯͳ����־ʧ�ܣ�");
			}
			
			Vector evtBody = new Vector();
			Map mp = new HashMap();
			mp.put("message", message);
			evtBody.add(mp);
			return evtBody;
		}catch (Exception e) {
			log.error("������Ǯͳ��ʧ��:"+e.getMessage());
			throw new SSTException("������Ǯͳ��ʧ��:"+e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
