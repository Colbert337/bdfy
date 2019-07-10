package com.ylzinfo.model.his.zy.jbxx.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.jbxx.service.GetCardNoService;
/**
 * 
* @Project: m_his_zy_jbxx
* @ClassName: GetCardNoAction 
* @Description: 获取卡号Action
* @author huangjj
* @date 2015-4-3 下午2:33:00 
*
 */
public class GetCardNoAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		//验证参数
		SoapUtil.checkParam(param, this.getClass().getName());
		return new GetCardNoService().readCardNo((String)param.get("cxdm00"),(String)param.get("zdbh00"));
	}
	
}
