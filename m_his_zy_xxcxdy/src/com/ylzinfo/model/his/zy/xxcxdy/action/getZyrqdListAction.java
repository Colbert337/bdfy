package com.ylzinfo.model.his.zy.xxcxdy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.xxcxdy.bs.getZyrqdListActionServer;

/**
 * @description: 获取住院清单列表
 * @author fanggx
 * @date: 2015-11-11 下午03:44:31
 */
public class getZyrqdListAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map request = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(request, this.getClass().getName());
		getZyrqdListActionServer service = new getZyrqdListActionServer();
		return service.getZyrqdList(request);
	}
	
}
