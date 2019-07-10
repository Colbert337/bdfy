package com.ylzinfo.model.his.mz.xxcx.action.wjgs;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.xxcx.server.wjgs.WjgsService;

public class priceQueryAction extends BaseAction{
	
	@Override
	public String performe(SoapHead head, String requestXml) throws SSTException {
		Map param = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new WjgsService().queryPrice(param);
	}
	
}
