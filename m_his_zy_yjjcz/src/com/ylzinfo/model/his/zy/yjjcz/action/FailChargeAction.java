package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.FailChargeModelMatrix;

/**
 * 
 * @description: ����ʧ��
 * @author wujf
 * @date: 2015-8-4 ����11:41:42
 */

public class FailChargeAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//��������
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new FailChargeModelMatrix().mainFailCharge(param);
	}

}