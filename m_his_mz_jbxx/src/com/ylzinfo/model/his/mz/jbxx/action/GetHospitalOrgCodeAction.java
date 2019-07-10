package com.ylzinfo.model.his.mz.jbxx.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.mz.jbxx.GetHospitalOrgCodeModelMatrix;
/**
 * 
* @Project: m_his_mz_jbxx
* @ClassName: GetHospitalOrgCodeAction 
* @Description: 获取医院机构代码
* @author huangjj
* @date 2015-7-16 下午03:15:05 
*
 */
@SuppressWarnings("unchecked")
public class GetHospitalOrgCodeAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		//验证参数
		SoapUtil.checkParam(param, this.getClass().getName());
		//获取医院机构代码
		return new GetHospitalOrgCodeModelMatrix().mainGetHospitalOrgCode((String)param.get("zdbh00"));
	}

}
