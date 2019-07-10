package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.yjjcz.service.ZfbpayChargeService;

public class ZfbpaySgczAction extends BaseAction{
	
	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//½âÎö±¨ÎÄ
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		param.put("iscz00", "1");
		return new ZfbpayChargeService().ZfbpayFinishCharge(param);
	}
}
