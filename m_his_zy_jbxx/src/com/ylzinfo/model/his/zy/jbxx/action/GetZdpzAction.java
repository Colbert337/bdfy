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
* @Description: 获取终端配置信息Action
* @author huangjj
* @date 2015-3-25 上午10:06:34 
*
 */
public class GetZdpzAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		//验证参数
		SoapUtil.checkParam(param, this.getClass().getName());
		return new GetZdpzService().getZdpz(param);
	}
	
}
