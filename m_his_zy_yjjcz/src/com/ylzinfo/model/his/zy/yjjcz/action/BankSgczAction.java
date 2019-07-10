package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.BankSgczModelMatrix;
import com.ylzinfo.model.his.zy.yjjcz.service.BankChargeServiceImpl;
/**
 * 
 * @description: ���п��ֹ�����
 * @author wujf
 * @date: 2015-8-4 ����12:13:23
 */
public class BankSgczAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//��������
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new BankSgczModelMatrix(new BankChargeServiceImpl()).mainBankSgcz(param);
	}
	
}
