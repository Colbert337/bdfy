package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.BankFinishChargeModelMatrix;
import com.ylzinfo.model.his.zy.yjjcz.service.BankChargeServiceImpl;
/**
 * 
 * @description: 银行卡充值确认
 * @author wujf
 * @date: 2015-8-4 下午12:07:13
 */
public class BankFinishChargeAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		param.put("czqx00", "1");
		return new BankFinishChargeModelMatrix(new BankChargeServiceImpl()) {}.mainBankFinishCharge(param);
	}
	
}
