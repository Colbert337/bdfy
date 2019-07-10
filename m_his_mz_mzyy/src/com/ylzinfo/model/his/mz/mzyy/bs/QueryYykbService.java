package com.ylzinfo.model.his.mz.mzyy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;

/**
 * ��ȡһ����������б�
 * @author CRD
 *
 */
public class QueryYykbService {
	private Logger logger = null;
	public QueryYykbService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
 
	/**
	 * ��ѯһ�����������б�
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	public String queryYylbList(Map para) throws SSTException {
		String funcTitle = "[��ѯ���������Ϣ]";
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
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception pe) {
			pe.printStackTrace();
			logger.error(funcTitle+"����,ԭ��"+ pe.getMessage());
			throw new SSTException(funcTitle+"����,ԭ��"+ pe.getMessage(), pe);
		}
	}
	
}
