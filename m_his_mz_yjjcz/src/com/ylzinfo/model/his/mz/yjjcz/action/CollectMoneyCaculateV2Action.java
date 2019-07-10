/**
 * 
 */
package com.ylzinfo.model.his.mz.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.mz.yjjcz.CollectMoneyCaculateV2ModelMatrix;

/**
 * @description: 本次收钱分日期统计
 * @author fanggx
 * @date: 2016-2-29 上午11:54:06
 */
public class CollectMoneyCaculateV2Action extends BaseAction {

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return  new CollectMoneyCaculateV2ModelMatrix().mainCollectMoneyCaculateV2(param);
	}

}
