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
import com.ylzinfo.matrix.model.his.mz.yygh.QueryBookDepartmentModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class QueryXcphDepartmentService extends QueryBookDepartmentModelMatrix {
	
	private Logger logger = null;
	public QueryXcphDepartmentService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public Vector queryBookDepartment(Map para) throws SSTException {
		String funcTitle = "[��ѯ�Һſ�����Ϣ]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"��ʼʱ�䣺" + startTime);
			
			String zdbh00 = (String)para.get("zdbh00");
			if(zdbh00.compareTo("130600600101") >= 0 && zdbh00.compareTo("130600600108") <=0) {
				para.put("kslb00", "S114275");
			} else if(zdbh00.compareTo("130600600109") >= 0 && zdbh00.compareTo("130600600114") <= 0) {
				para.put("kslb00", "S114276");
			} else if(zdbh00.compareTo("130600600115") >= 0 && zdbh00.compareTo("130600600119") <= 0) {
				para.put("kslb00", "S114277");
			}else if(zdbh00.compareTo("130600600199") == 0) {
				para.put("kslb00", "S114242");
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			para.put("yyrq00", sdf.format(calendar.getTime()));
			
			CallHisService ws = new CallHisService("006");
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
