package com.ylzinfo.model.his.mz.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.yjjcz.service.ZfbpayRefundService;
/**
 * 
 * @describe :֧���������˿�
 * @classname:ZfbpayRefundAction
 * @author   :Lan
 * @date     :2018-4-13
 */
public class ZfbpayRefundAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new ZfbpayRefundService().zfbpayRefund(srq);
	}
}
