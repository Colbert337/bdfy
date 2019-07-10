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
		String funcTitle = "[获取挂号医生信息]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"开始 ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"开始时间：" + startTime);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			para.put("yyrq00", sdf.format(calendar.getTime()));
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
			String currenttime = sdf2.format(new Date());
			if(currenttime.compareTo("16:50:00") > 0)
			{
				throw new SSTException("号源已满");
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
					errmsg = "号源已满";
				}
				throw new SSTException(errmsg);
			}
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("号源已满");
				throw new SSTException("号源已满");
			}
			
			for(int i=0;i<vt.size();i++) {
				String mzsj00 = ((HashMap<String, String>)vt.get(i)).get("mzsj00");
				if(mzsj00.equals("1")) {
					((HashMap<String, String>)vt.get(i)).put("mzsjmc", "上午");
				} else if(mzsj00.equals("3")) {
					((HashMap<String, String>)vt.get(i)).put("mzsjmc", "下午");
				} else if(mzsj00.equals("4")) {
					((HashMap<String, String>)vt.get(i)).put("mzsjmc", "晚上");
				}
				String ghf000 = ((HashMap<String, String>)vt.get(i)).get("ghf000");
				((HashMap<String, String>)vt.get(i)).put("ghfmc0", "挂号费："+ghf000);
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle+"结束时间：" + endTime);
			logger.info("************"+funcTitle+"结束************");
			logger.info(funcTitle+"额耗时:"+ StringUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception pe) {
			pe.printStackTrace();
			logger.error(funcTitle+"出错,原因："+ pe.getMessage());
			throw new SSTException(funcTitle+"出错,原因："+ pe.getMessage(), pe);
		}
	}
}
