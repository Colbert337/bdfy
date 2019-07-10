/**
 * 
 */
package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.AccumulateChargeModelMatrix;

/**
 * 现金充值单笔累加到合计
 * @description: 方高翔
 * @author fanggx
 * @date: 2016-3-22 下午07:34:51
 */
public class AccumulateChargeAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new AccumulateChargeModelMatrix().mainAccumulateCharge(param);
	}

}
