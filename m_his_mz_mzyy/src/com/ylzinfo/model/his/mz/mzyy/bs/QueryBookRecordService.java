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
 * @describe :��ȡԤԼ��¼
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
	 * ��ȡԤԼ��¼
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public Vector queryBookRecord(Map para) throws SSTException {
		String funcTitle = "[��ȡԤԼ��¼]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"��ʼʱ�䣺" + startTime);
			
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
