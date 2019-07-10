package com.ylzinfo.model.his.mz.xxcx.server.jymx;

import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.ylzinfo.matrix.model.his.mz.xxcx.QueryTradeDetailModelMatrix;

public class QueryTradeDetailServer extends QueryTradeDetailModelMatrix{
	
	private Logger logger = null;
	
	public QueryTradeDetailServer() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public Map queryTradeDetail(Map para) throws SSTException {
		return null;
	}
	
}
