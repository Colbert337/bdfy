package com.ylzinfo.model.his.mz.mzyy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.ylzinfo.matrix.model.his.mz.yygh.QueryBookTimeModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @describe :��ȡԤԼʱ��
 * @classname:QueryBookTimeService
 * @author   :Lan
 * @date     :2018-3-31
 */
public class QueryBookTimeService extends QueryBookTimeModelMatrix{
    
	private Logger logger = null;
	public QueryBookTimeService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * ��ȡԤԼʱ��
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public Vector queryBookTime(Map para) throws SSTException {
		String funcTitle = "[��ȡԤԼʱ����Ϣ]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"��ʼʱ�䣺" + startTime);
			
			CallHisService ws = new CallHisService("080");
			Map map = ws.callWs(para);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "��Դ����";
				}
				throw new SSTException(errmsg);
			}
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("��Դ����");
				throw new SSTException("��Դ����");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle+"����ʱ�䣺" + endTime);
			logger.info("************"+funcTitle+"����************");
			logger.info(funcTitle+"���ʱ:"+ StringUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception pe) {
			logger.error(funcTitle+"����,ԭ��"+ pe.getMessage());
			throw new SSTException(pe.getMessage(), pe);
		}
	}	
}
