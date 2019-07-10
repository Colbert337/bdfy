package com.ylzinfo.model.his.mz.jbxx.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.jbxx.service.GetPatientInfoService;
/**
 * 
 * @description: 获取个人基本信息
 * @author baisj
 * @date: Sep 6, 2015 2:03:50 PM
 */
public class GetPatientInfoAction extends BaseAction {

	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map srq=SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new GetPatientInfoService().mainGetPatientInfo(srq);
	}
}
