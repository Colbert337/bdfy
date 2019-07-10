/**
 * 
 */
package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.CollectMoneyCaculateV2ModelMatrix;

/**
 * @description: ��Ǯͳ��V2������ͳ��
 * @author fanggx
 * @date: 2016-4-22 ����05:01:41
 */
public class CollectMoneyCaculateV2Action extends BaseAction {

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//��������
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return  new CollectMoneyCaculateV2ModelMatrix().mainCollectMoneyCaculateV2(param);
	}

}
