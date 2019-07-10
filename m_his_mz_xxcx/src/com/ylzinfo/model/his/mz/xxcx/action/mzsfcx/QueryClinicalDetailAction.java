package com.ylzinfo.model.his.mz.xxcx.action.mzsfcx;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.xxcx.server.mzsfcx.MzsfcxServer;

public class QueryClinicalDetailAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml) throws SSTException {
		Map request = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(request, this.getClass().getName());
		return new MzsfcxServer().getSfmxByDjlsh(request);
	}
	
}
