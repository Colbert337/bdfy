package com.ylzinfo.model.his.mz.fyjs.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.fyjs.service.GetMzsfInfoService;

/**
 * @description: 获取门诊收费结算信息
 * @author wujf
 * @date: 2015-8-16 上午08:24:51
 */
public class GetMzsfInfoAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml) throws SSTException {
		Map params =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(params, this.getClass().getName());
		return new GetMzsfInfoService().mainGetMzsfInfo(params);
	}
	
}
