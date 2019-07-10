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
import com.ylzinfo.matrix.model.his.mz.yygh.QueryBookTimeModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class QueryXcphTimeService extends QueryBookTimeModelMatrix{

	private Logger logger = null;
	public QueryXcphTimeService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public Vector queryBookTime(Map para) throws SSTException {
		String funcTitle = "[获取挂号时间信息]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"开始 ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"开始时间：" + startTime);
			
			CallHisService ws = new CallHisService("080");
			Map map = ws.callWs(para);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "号源已满";
				}
				throw new SSTException(errmsg);
			}
			Vector<HashMap<String, String>> vt2 = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt2 == null || vt2.size() <= 0) {
				logger.error("号源已满");
				throw new SSTException("号源已满");
			}
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
			String time = sdf2.format(new Date());
			
			for(int i=0;i<vt2.size();i++) {
				String yysj00 = ((HashMap<String,String>)vt2.get(i)).get("yysj00");
				if(time.compareTo(yysj00) >= 0) {
					continue;
				}
				vt.add(vt2.get(i));
			}
			
			if(vt == null || vt.size() <= 0) {
				logger.error("号源已满");
				throw new SSTException("号源已满");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle+"结束时间：" + endTime);
			logger.info("************"+funcTitle+"结束************");
			logger.info(funcTitle+"额耗时:"+ StringUtil.consumeTime2String(startTime, endTime));
			return vt;
//			return vt2;
		} catch (Exception pe) {
			logger.error(funcTitle+"出错,原因："+ pe.getMessage());
			throw new SSTException(pe.getMessage(), pe);
		}
	}
}
