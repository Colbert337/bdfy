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
 * @description: 预交金充值初始化 
 * @author wujf
 * @date: 2015-8-4 上午11:38:28
 */
public class InitChargeAction extends BaseAction {

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		param.put("czqx00", "1");
		return new InitChargeModelMatrix().mainInitCharge(param);
	}
}
