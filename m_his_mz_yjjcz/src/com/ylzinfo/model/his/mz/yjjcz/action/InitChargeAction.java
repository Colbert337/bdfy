/**
 * 
 */
package com.ylzinfo.model.his.mz.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.mz.yjjcz.InitChargeModelMatrix;

/**
 * 
 * @description: Ԥ�����ֵ��ʼ�� 
 * @author wujf
 * @date: 2015-8-4 ����11:38:28
 */
public class InitChargeAction extends BaseAction {

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//��������
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		param.put("czqx00", "1");
		return new InitChargeModelMatrix().mainInitCharge(param);
	}
}
