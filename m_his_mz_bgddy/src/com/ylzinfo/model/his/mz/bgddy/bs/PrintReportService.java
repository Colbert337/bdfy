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
		String errMsg = "��ӡ����";
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+errMsg+"��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(errMsg+"��ʼʱ�䣺" + startTime);
			
			param.put("brlx00", "����");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			param.put("jzzj00", sdf.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_YEAR, -14);
			param.put("qssj00", sdf.format(calendar.getTime()));
			
			CallLisService ws = new CallLisService();
			vt = ws.printForm(param);
			if(vt == null || vt.size() <= 0) {
				logger.error("û�пɴ�ӡ�ı��浥");
				throw new SSTException("û�пɴ�ӡ�ı��浥");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(errMsg+"����ʱ�䣺" + endTime);
			logger.info("************"+errMsg+"����************");
			
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception pe) {
			logger.error(errMsg+"����,ԭ��"+ pe.getMessage());
			throw new SSTException(errMsg+"����,ԭ��"+ pe.getMessage(), pe);
		}
	}
}
