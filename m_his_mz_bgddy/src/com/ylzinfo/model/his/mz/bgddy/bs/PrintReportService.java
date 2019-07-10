package com.ylzinfo.model.his.mz.bgddy.bs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallLisService;

public class PrintReportService {

	private Logger logger = null;
	
	public PrintReportService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String printReport(Map param)throws SSTException{
		String errMsg = "打印报告";
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+errMsg+"开始 ************");
			long startTime = System.currentTimeMillis();
			logger.info(errMsg+"开始时间：" + startTime);
			
			param.put("brlx00", "门诊");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			param.put("jzzj00", sdf.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_YEAR, -14);
			param.put("qssj00", sdf.format(calendar.getTime()));
			
			CallLisService ws = new CallLisService();
			vt = ws.printForm(param);
			if(vt == null || vt.size() <= 0) {
				logger.error("没有可打印的报告单");
				throw new SSTException("没有可打印的报告单");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(errMsg+"结束时间：" + endTime);
			logger.info("************"+errMsg+"结束************");
			
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception pe) {
			logger.error(errMsg+"出错,原因："+ pe.getMessage());
			throw new SSTException(errMsg+"出错,原因："+ pe.getMessage(), pe);
		}
	}
}
