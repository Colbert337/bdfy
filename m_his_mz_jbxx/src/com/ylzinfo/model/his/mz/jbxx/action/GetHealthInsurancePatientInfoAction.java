package com.ylzinfo.model.his.mz.jbxx.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.mz.jbxx.GetHealthInsurancePatientInfoModelMatrix;
/**
 * 
 * @description: 获取医保病人基本信息
 * @author baisj
 * @date: Sep 6, 2015 2:04:27 PM
 */
public class GetHealthInsurancePatientInfoAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map srq=SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, requestXml);
		return new GetHealthInsurancePatientInfoModelMatrix().mainGetHealthInsurancePatientInfo(srq);
	}

}
