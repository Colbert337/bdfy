package com.ylzinfo.model.his.zy.xxcxdy.bs;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.ylzinfo.matrix.model.his.zy.xxcxdy.QueryInpatientListModelMatrix;

public class QueryZyqdhzService extends QueryInpatientListModelMatrix{
	
	private Logger logger = null;
	
	public QueryZyqdhzService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public HashMap queryInpatientList(Map param) throws SSTException {
		return null;
	}
}
