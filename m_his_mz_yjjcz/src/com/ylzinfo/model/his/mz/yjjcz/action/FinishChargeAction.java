package com.ylzinfo.model.his.mz.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.mz.yjjcz.FinishChargeModelMatrix;
import com.ylzinfo.model.his.mz.yjjcz.service.CashChargeServiceImpl;

/**
 * 
 * @description: 完成现金充值
 * @author wujf
 * @date: 2015-8-4 上午11:43:42
 */
public class FinishChargeAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		param.put("czqx00", "1");
		return new FinishChargeModelMatrix(new CashChargeServiceImpl()) {}.mainFinishCharge(param);
	}

}
