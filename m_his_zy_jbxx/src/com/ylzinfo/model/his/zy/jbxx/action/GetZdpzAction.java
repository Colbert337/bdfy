package com.ylzinfo.model.his.zy.jbxx.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.jbxx.service.GetZdpzService;
/**
 * 
* @Project: m_his_mz_jbxx
* @ClassName: GetZdpzAction 
* @Description: ��ȡ�ն�������ϢAction
* @author huangjj
* @date 2015-3-25 ����10:06:34 
*
 */
public class GetZdpzAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//��������
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		//��֤����
		SoapUtil.checkParam(param, this.getClass().getName());
		return new GetZdpzService().getZdpz(param);
	}
	
}
