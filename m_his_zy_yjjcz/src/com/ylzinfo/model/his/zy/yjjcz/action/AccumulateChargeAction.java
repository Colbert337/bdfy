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
 * �ֽ��ֵ�����ۼӵ��ϼ�
 * @description: ������
 * @author fanggx
 * @date: 2016-3-22 ����07:34:51
 */
public class AccumulateChargeAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//��������
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		return new AccumulateChargeModelMatrix().mainAccumulateCharge(param);
	}

}
