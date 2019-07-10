package com.ylzinfo.model.his.mz.mzyy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.ylzinfo.matrix.model.his.mz.yygh.QueryBookDepartmentModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @describe :获取预约科室
 * @classname:QueryBookDepartmentService
 * @author   :Lan
 * @date     :2018-3-30
 */
public class QueryBookDepartmentService extends QueryBookDepartmentModelMatrix {
	
	private Logger logger = null;
	public QueryBookDepartmentService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
 
	/**
	 * 获取预约科室
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public Vector queryBookDepartment(Map para) throws SSTException {
		String funcTitle = "[查询预约科室信息]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"开始 ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"开始时间：" + startTime);
			
			String zdbh00 = (String)para.get("zdbh00");
			if(zdbh00.compareTo("130600600101") >= 0 && zdbh00.compareTo("130600600108") <=0) {
				para.put("kslb00", "S114275");
			} else if(zdbh00.compareTo("130600600109") >= 0 && zdbh00.compareTo("130600600114") <= 0) {
				para.put("kslb00", "S114276");
			} else if(zdbh00.compareTo("130600600115") >= 0 && zdbh00.compareTo("130600600119") <= 0) {
				para.put("kslb00", "S114277");
			}else if(zdbh00.compareTo("130600600199") == 0) {
				para.put("kslb00", "S114243");
			}
			
			CallHisService ws = new CallHisService("006");
			Map map = ws.callWs(para);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "号源已满";
				}
				throw new SSTException(errmsg);
			}
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("号源已满");
				throw new SSTException("号源已满");
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
