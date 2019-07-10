package com.ylzinfo.model.his.mz.mzyy.bs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.ylzinfo.matrix.model.his.mz.yygh.QueryBookDateModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;

/**
 * @describe :获取医生排班（预约日期）
 * @classname:QueryBookDateService
 * @author   :Lan
 * @date     :2018-3-31
 */
public class QueryBookDateService extends QueryBookDateModelMatrix {
		
	private Logger logger = null;
	public QueryBookDateService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 获取医生排班（预约日期）
	 * @param para
	 * @throws SSTException
	 */
	@Override
	public Vector queryBookDate(Map para) throws SSTException {
		String funcTitle = "[获取医生排班（预约日期）]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"开始 ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"开始时间：" + startTime);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar calendar = Calendar.getInstance();
			for(int i=1;i<=7;i++)
			{
				HashMap<String, String> hm = new HashMap<String, String>();
				calendar.setTime(new Date());
				calendar.add(Calendar.DAY_OF_YEAR, i);
				hm.put("yyrq00", sdf.format(calendar.getTime()));
				int dayOfWeek = calendar.get(7) - 1;
				if (dayOfWeek < 0) {
					dayOfWeek = 0;
				}
				if (dayOfWeek == 1) {
					hm.put("week00", "星期一");
				} else if (dayOfWeek == 2) {
					hm.put("week00", "星期二");
				} else if (dayOfWeek == 3) {
					hm.put("week00", "星期三");
				} else if (dayOfWeek == 4) {
					hm.put("week00", "星期四");
				} else if (dayOfWeek == 5) {
					hm.put("week00", "星期五");
				} else if (dayOfWeek == 6) {
					hm.put("week00", "星期六");
				} else {
					hm.put("week00", "星期日");
				}
				vt.add(hm);
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
