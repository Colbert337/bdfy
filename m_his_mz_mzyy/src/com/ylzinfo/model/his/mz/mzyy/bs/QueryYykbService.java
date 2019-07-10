package com.ylzinfo.model.his.mz.mzyy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;

/**
 * 获取一级科室类别列表
 * @author CRD
 *
 */
public class QueryYykbService {
	private Logger logger = null;
	public QueryYykbService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
 
	/**
	 * 查询一级科室名称列表
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	public String queryYylbList(Map para) throws SSTException {
		String funcTitle = "[查询科室类别信息]";
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
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception pe) {
			pe.printStackTrace();
			logger.error(funcTitle+"出错,原因："+ pe.getMessage());
			throw new SSTException(funcTitle+"出错,原因："+ pe.getMessage(), pe);
		}
	}
	
}
