package com.ylzinfo.model.his.mz.mzyy.bs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.ylzinfo.matrix.model.his.mz.yygh.QueryBookRecordModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;

/**
 * @describe :获取预约记录
 * @classname:QueryBookRecordService
 * @author   :Lan
 * @date     :2018-3-30
 */
public class QueryBookRecordService extends QueryBookRecordModelMatrix{
    
	private Logger logger = null;
	public QueryBookRecordService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 获取预约记录
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public Vector queryBookRecord(Map para) throws SSTException {
		String funcTitle = "[获取预约记录]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"开始 ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"开始时间：" + startTime);
			
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
