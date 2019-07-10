package com.ylzinfo.model.his.mz.mzyy.bs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.ylzinfo.matrix.model.his.mz.yygh.QueryBookDoctorModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class QueryXcphDoctorService extends QueryBookDoctorModelMatrix{
	
	private Logger logger = null;
	public QueryXcphDoctorService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public Vector queryBookDoctor(Map para) throws SSTException {
		String funcTitle = "[��ȡ�Һ�ҽ����Ϣ]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"��ʼʱ�䣺" + startTime);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			para.put("yyrq00", sdf.format(calendar.getTime()));
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
			String currenttime = sdf2.format(new Date());
			if(currenttime.compareTo("16:50:00") > 0)
			{
				throw new SSTException("��Դ����");
			}else if(currenttime.compareTo("11:30:00") > 0) {
				para.put("mzsj00", "3");
			} else {
				para.put("mzsj00", "ALL");
			}
			
			CallHisService ws = new CallHisService("007");
			Map map = ws.callWs(para);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "��Դ����";
				}
				throw new SSTException(errmsg);
			}
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("��Դ����");
				throw new SSTException("��Դ����");
			}
			
			for(int i=0;i<vt.size();i++) {
				String mzsj00 = ((HashMap<String, String>)vt.get(i)).get("mzsj00");
				if(mzsj00.equals("1")) {
					((HashMap<String, String>)vt.get(i)).put("mzsjmc", "����");
				} else if(mzsj00.equals("3")) {
					((HashMap<String, String>)vt.get(i)).put("mzsjmc", "����");
				} else if(mzsj00.equals("4")) {
					((HashMap<String, String>)vt.get(i)).put("mzsjmc", "����");
				}
				String ghf000 = ((HashMap<String, String>)vt.get(i)).get("ghf000");
				((HashMap<String, String>)vt.get(i)).put("ghfmc0", "�Һŷѣ�"+ghf000);
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle+"����ʱ�䣺" + endTime);
			logger.info("************"+funcTitle+"����************");
			logger.info(funcTitle+"���ʱ:"+ StringUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception pe) {
			pe.printStackTrace();
			logger.error(funcTitle+"����,ԭ��"+ pe.getMessage());
			throw new SSTException(funcTitle+"����,ԭ��"+ pe.getMessage(), pe);
		}
	}
}
