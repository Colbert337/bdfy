package com.ylzinfo.model.his.zy.xxcxdy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.xxcxdy.bs.FinishPrintInpatientService;

/**
 * @description:סԺ�嵥��ӡȷ��
 * @author fanggx
 * @date: 2015-11-11 ����03:43:37
 */
public class FinishPrintInpatientAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map request = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(request, this.getClass().getName());
		FinishPrintInpatientService service = new FinishPrintInpatientService();
		return service.mainFinishPrintInpatient(request);
	}

}
