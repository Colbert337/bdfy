package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.ZhczSgczModelMatrix;
import com.ylzinfo.model.his.zy.yjjcz.service.CashChargeServiceImpl;
/**
 * 
 * @description: 合计充值手工冲正
 * @author wujf
 * @date: 2015-8-4 下午12:13:53o
 */
public class ZhczSgczAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new ZhczSgczModelMatrix(new CashChargeServiceImpl()){}.mainZhczSgcz(param);
	}
	
}
