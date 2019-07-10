package com.ylzinfo.model.his.mz.mzyy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.ylzinfo.matrix.model.his.mz.yygh.CancelBookModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;

/**
 * @describe :取消预约
 * @classname:CancelBookService
 * @author :Lan
 * @date :2018-3-31
 */
public class CancelBookService extends CancelBookModelMatrix {
	
	private Logger logger = null;
	public CancelBookService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 取消预约
	 * 
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public Vector cancelBook(Map para) throws SSTException {
		String funcTitle = "[预约取消确认]";
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
