package com.ylzinfo.model.his.zy.jbxx.action;

import java.util.Map;
import java.util.Vector;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.his.zy.service.PatientService;
import com.ylzinfo.model.his.zy.jbxx.service.PatientServiceImpl;

/**
 * 
 * @description:获取住院病人基本信息
 * @author fanggx
 * @date: 2016-3-22 下午03:06:46
 */
public class GetPatientInfoAction extends BaseAction {
	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		//参数验证
		SoapUtil.checkParam(param, this.getClass().getName());
		PatientService service = new PatientServiceImpl();
		Map patientInfo = service.getPatientInfo(param);
		Vector vt = new Vector();
		vt.add(patientInfo);
		return SoapUtil.getSoapResponse(vt);
	}
}
