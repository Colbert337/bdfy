package com.ylzinfo.model.his.mz.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.ylzinfo.matrix.model.his.mz.yjjcz.CollectMoneyCaculateModelMatrix;
import com.ylzinfo.matrix.model.his.mz.yjjcz.dao.ScsqsjDao;
import com.ylzinfo.model.his.mz.yjjcz.dao.YjjczDao;

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
		String yktzbs = "0";// һ��ͨ�ܱ���
		String yktzje = "0";// һ��ͨ�ܽ��
		String ynzbs0 = "0";// Ժ���ܱ���
		String ynzje0 = "0";// Ժ���ܽ��
		String cgjybs = "0";// ���ܳɹ����ױ���
		String cgjyje = "0";// ���ܳɹ����׽��
		String hzsbbs = "0";// ����ʧ�ܽ��ױ���
		String hzsbje = "0";// ����ʧ�ܽ��׽��
		String dbsbbs = "0";// ����ʧ�ܱ���
		String dbsbje = "0";// ����ʧ�ܽ��
		String dbzybs = "0";// �����������
		String dbzyje = "0";// ����������
		String yhjybs = "0";// ���н��ױ���
		String yhjyje = "0";// ���н��׽��
		String wxjybs = "0";// ΢�Ž��ױ���
		String wxjyje = "0";// ΢�Ž��׽��
		String zfjybs = "0";// ֧�������ױ���
		String zfjyje = "0";// ֧�������׽��
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
			yktzbs = (String)sqmx.get("yktzbs");
			yktzje = (String)sqmx.get("yktzje");
			ynzbs0 = (String)sqmx.get("ynzbs0");
			ynzje0 = (String)sqmx.get("ynzje0");
			hzsbbs = (String)sqmx.get("hzsbbs");
			hzsbje = (String)sqmx.get("hzsbje");
			dbsbbs = (String)sqmx.get("dbsbbs");
			dbsbje = (String)sqmx.get("dbsbje");
			dbzybs = (String)sqmx.get("dbzybs");
			dbzyje = (String)sqmx.get("dbzyje");
			
			String sfykt0 = (String)param.get("sfykt0");
			if("0".equals(sfykt0)){
				message = "\\n\n�ն˱�ţ�" + zdbh00 + "\\n\n" + "�ն����ƣ�" + zdmc00 + "\\n\n"
						+ "�ϴ���Ǯʱ�䣺" + scsqsj + "\\n\n" + "������Ǯʱ�䣺" + bcsqsj + "\\n\n"
						+ "�ɹ����ױ�����" + cgjybs + "\\n\n" + "�ɹ����׽�" + cgjyje + "\\n\n"
						+ "���ܴ�����������" + hzsbbs + "\\n\n"+ "���ܴ�������" + hzsbje + "\\n\n" 
						+ "���ʴ�����������" + dbsbbs + "\\n\n"+ "���ʴ�������" + dbsbje + "\\n\n" 
						+ "���������������" + dbzybs + "\\n\n"+ "�����������" + dbzyje + "\\n\n";
			}else{
				message =  "\\n\n�ն˱�ţ�" + zdbh00 + "\\n\n" + "�ն����ƣ�" + zdmc00 + "\\n\n"
						+ "�ϴ���Ǯʱ�䣺" + scsqsj + "\\n\n" + "������Ǯʱ�䣺" + bcsqsj + "\\n\n" 
						+ "����ͨ�ܱ�����" + yktzbs + "\\n\n" + "����ͨ�ܽ�" + yktzje + "\\n\n"
						+ "Ժ����  ��  ����" + ynzbs0 + "\\n\n" + "Ժ����  ��  �" + ynzje0 + "\\n\n"
						+ "�ɹ����ױ�����" + cgjybs + "\\n\n"+ "�ɹ����׽�" + cgjyje + "\\n\n" 
						+ "���ܴ�����������" + hzsbbs + "\\n\n"+ "���ܴ�������" + hzsbje + "\\n\n" 
						+ "���ʴ�����������" + dbsbbs + "\\n\n"+ "���ʴ�������" + dbsbje + "\\n\n" 
						+ "���������������" + dbzybs + "\\n\n"+ "�����������" + dbzyje + "\\n\n";
			}
			try{
				Map map = new HashMap();
				map.put("zdbh00", zdbh00);
				map.put("scsqsj", scsqsj);
				map.put("bcsqsj", bcsqsj);
				map.put("message", message);
				map.put("zdmc00", zdmc00);
				map.put("cgjybs", cgjybs);
				map.put("cgjyje", cgjyje);
				map.put("yktzbs", yktzbs);
				map.put("yktzje", yktzje);
				map.put("hzsbbs", hzsbbs);   
				map.put("hzsbje", hzsbje);
				map.put("dbsbbs", dbsbbs);
				map.put("dbsbje", dbsbje);
				map.put("dbzybs", dbzybs);
				map.put("dbzyje", dbzyje);
				dao.doSqtjLog(conn, map);
			}catch(Exception e){
				log.info("��¼��Ǯͳ����־ʧ�ܣ�");
			}
			
			YjjczDao dao2 = new YjjczDao();
			param.put("scsqsj", scsqsj);
			param.put("bcsqsj", bcsqsj);
			
			Map bank = dao2.queryBankMoney(conn, param);
			yhjybs = (String)bank.get("cgjybs");
			yhjyje = (String)bank.get("cgjyje");	
			message += "���п����ױ�����" + yhjybs + "\\n\n"+ "���п����׽�" + yhjyje + "\\n\n";
			
			Map wx = dao2.queryWxMoney(conn, param);
			wxjybs = (String)wx.get("cgjybs");
			wxjyje = (String)wx.get("cgjyje");
			message += "΢�Ž��ױ�����" + wxjybs + "\\n\n"+ "΢�Ž��׽�" + wxjyje + "\\n\n";
			
			Map zfb = dao2.queryZfbMoney(conn, param);
			zfjybs = (String)zfb.get("cgjybs");
			zfjyje = (String)zfb.get("cgjyje");
			message += "֧�������ױ�����" + zfjybs + "\\n\n"+ "֧�������׽�" + zfjyje + "\\n\n";
			
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
