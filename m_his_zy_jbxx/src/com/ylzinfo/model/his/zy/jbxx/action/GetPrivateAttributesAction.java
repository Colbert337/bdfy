/**   
* @Title: GetPrivateAttributesAction.java 
* @Package com.ylzinfo.model.his.mz.jbxx.action 
* @Description: 
* @author thinkpad
* @date 2015-10-8 下午01:36:43 
* @version V1.0   
*/
package com.ylzinfo.model.his.zy.jbxx.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.jbxx.service.GetPrivateAttributesService;

/**
 * 
* @Project: m_his_zy_jbxx
* @ClassName: GetPrivateAttributesAction 
* @Description: 获取私有属性
* @author huangjj
* @date 2015-11-11 上午10:22:41 
*
 */
public class GetPrivateAttributesAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml) throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		//验证参数
		SoapUtil.checkParam(param, this.getClass().getName());
		GetPrivateAttributesService bs = new GetPrivateAttributesService();
		return bs.getPrivateAttributes(param);
	}

}
