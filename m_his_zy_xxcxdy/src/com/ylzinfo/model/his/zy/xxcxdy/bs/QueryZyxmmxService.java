package com.ylzinfo.model.his.zy.xxcxdy.bs;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.ylzinfo.matrix.model.his.zy.xxcxdy.QueryInpatientItemModelMatrix;

public class QueryZyxmmxService extends QueryInpatientItemModelMatrix{
	
	private Logger logger = null;
	
	public QueryZyxmmxService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public HashMap queryInpatientItem(Map param) throws SSTException {
		return null;
	}	
}
