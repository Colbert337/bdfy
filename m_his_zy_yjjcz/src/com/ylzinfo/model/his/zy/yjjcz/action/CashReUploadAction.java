package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.yjjcz.service.CashReUploadService;

public class CashReUploadAction extends BaseAction {
	
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//½âÎö±¨ÎÄ
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new CashReUploadService().doCashReUpload(param);
	}
}