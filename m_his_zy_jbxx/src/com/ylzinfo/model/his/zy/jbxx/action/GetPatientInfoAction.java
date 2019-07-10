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
 * @description:��ȡסԺ���˻�����Ϣ
 * @author fanggx
 * @date: 2016-3-22 ����03:06:46
 */
public class GetPatientInfoAction extends BaseAction {
	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		//��������
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		//������֤
		SoapUtil.checkParam(param, this.getClass().getName());
		PatientService service = new PatientServiceImpl();
		Map patientInfo = service.getPatientInfo(param);
		Vector vt = new Vector();
		vt.add(patientInfo);
		return SoapUtil.getSoapResponse(vt);
	}
}
