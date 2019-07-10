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
 * @describe :��ȡҽ���ŰࣨԤԼ���ڣ�
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
	 * ��ȡҽ���ŰࣨԤԼ���ڣ�
	 * @param para
	 * @throws SSTException
	 */
	@Override
	public Vector queryBookDate(Map para) throws SSTException {
		String funcTitle = "[��ȡҽ���ŰࣨԤԼ���ڣ�]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"��ʼʱ�䣺" + startTime);
			
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
					hm.put("week00", "����һ");
				} else if (dayOfWeek == 2) {
					hm.put("week00", "���ڶ�");
				} else if (dayOfWeek == 3) {
					hm.put("week00", "������");
				} else if (dayOfWeek == 4) {
					hm.put("week00", "������");
				} else if (dayOfWeek == 5) {
					hm.put("week00", "������");
				} else if (dayOfWeek == 6) {
					hm.put("week00", "������");
				} else {
					hm.put("week00", "������");
				}
				vt.add(hm);
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
