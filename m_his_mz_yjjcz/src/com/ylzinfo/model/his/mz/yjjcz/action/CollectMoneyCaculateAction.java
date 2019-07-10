package com.ylzinfo.model.his.mz.yjjcz.action;

import java.util.Map;
import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.yjjcz.service.CollectMoneyCaculateService;

/**
 * 
 * @description: 本次收钱统计
 * @author wujf
 * @date: 2015-8-4 上午11:31:36
 */
public class CollectMoneyCaculateAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new CollectMoneyCaculateService().mainCollectMoneyCaculate(param);
	}
	
}
