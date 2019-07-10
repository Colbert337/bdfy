package com.ylzinfo.model.his.zy.jbxx.action;

import java.util.Map;
import java.util.Vector;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.his.zy.service.HospitalService;

/**
 * @description: ��ȡҽԺ��������
 * @author: huangjj
 * @date:2014-12-8 ����09:40:35
 *
 */
@SuppressWarnings("rawtypes")
public class GetHospitalOrgCodeAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		//��������
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		//��֤����
		SoapUtil.checkParam(param, this.getClass().getName());
		HospitalService service = new HospitalService();
		Map hospitalInfo = service.getHospitalOrgCode((String)param.get("zdbh00"));
		Vector vt = new Vector();
		vt.add(hospitalInfo);
		return SoapUtil.getSoapResponse(vt);
	}
	
}
