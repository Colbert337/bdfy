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
 * @describe :ȡ��ԤԼ
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
	 * ȡ��ԤԼ
	 * 
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public Vector cancelBook(Map para) throws SSTException {
		String funcTitle = "[ԤԼȡ��ȷ��]";
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
