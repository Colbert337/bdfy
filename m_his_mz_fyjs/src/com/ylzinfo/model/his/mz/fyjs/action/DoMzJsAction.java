package com.ylzinfo.model.his.mz.fyjs.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.fyjs.service.DoMzJsService;

/**
 * @description: √≈’ÔΩ·À„
 * @author wujf
 * @date: 2015-8-16 …œŒÁ08:38:26
 */
public class DoMzJsAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml) throws SSTException {
		Map params =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(params, this.getClass().getName());
		return new DoMzJsService().mainDoMzJs(params);
	}
	
}
