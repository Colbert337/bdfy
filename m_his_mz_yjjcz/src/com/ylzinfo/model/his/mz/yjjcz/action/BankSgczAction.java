package com.ylzinfo.model.his.mz.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.mz.yjjcz.BankSgczModelMatrix;
import com.ylzinfo.model.his.mz.yjjcz.service.BankChargeServiceImpl;
/**
 * 
 * @description: 银行卡手工冲正
 * @author wujf
 * @date: 2015-8-4 下午12:13:23
 */
public class BankSgczAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new BankSgczModelMatrix(new BankChargeServiceImpl()).mainBankSgcz(param);
	}
	
}
