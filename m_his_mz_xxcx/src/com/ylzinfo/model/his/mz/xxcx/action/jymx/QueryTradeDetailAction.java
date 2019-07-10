package com.ylzinfo.model.his.mz.xxcx.action.jymx;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.xxcx.server.jymx.QueryTradeDetailServer;

public class QueryTradeDetailAction extends BaseAction{
	
	@Override
	public String performe(SoapHead head, String requestXml) throws SSTException {
		Map param = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new QueryTradeDetailServer().mainQueryTradeDetail(param);
	}
	
}
