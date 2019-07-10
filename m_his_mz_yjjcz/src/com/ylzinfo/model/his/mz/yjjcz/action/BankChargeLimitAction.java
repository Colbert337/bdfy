/**
 * 
 */
package com.ylzinfo.model.his.mz.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.yjjcz.service.BankChargeLimitService;

/**
 * @description: ���п���ֵ����ۼ�
 * @author fanggx
 * @date: 2016-4-27 ����09:29:44
 */
public class BankChargeLimitAction extends BaseAction {

	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		BankChargeLimitService service =  new BankChargeLimitService();
		return service.mainBankChargeLimit(param);
	}

}
