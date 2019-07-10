package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.CheckChargeTimeModelMatrix;

/**
 * 
 * @description: 检查充值时间
 * @author wujf
 * @date: 2015-8-4 上午11:23:05
 */
public class CheckChargeTimeAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new CheckChargeTimeModelMatrix().mainCheckChargeTime(param);
	}
	
}
