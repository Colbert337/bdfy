package com.ylzinfo.model.his.zy.xxcxdy.bs;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.ylzinfo.matrix.model.his.zy.qddy.FinishPrintInpatientModelMatrix;

/**
 * @description: 住院清单打印确认
 * @author fanggx
 * @date: 2015-11-11 下午03:43:49
 */
public class FinishPrintInpatientService extends FinishPrintInpatientModelMatrix{
	
	public Logger logger = null;
	
	public FinishPrintInpatientService(){
		logger = Logger.getLogger(this.getClass().getName());
	}	
	
	@Override
	public HashMap finishPrintInpatient(Map mp) throws SSTException {
		return null;
	}
}
