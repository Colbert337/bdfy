/**
 * 
 */
package com.ylzinfo.model.his.mz.ynkfk.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ynkfk.service.InitArchiveService;

/**
 * @description: 建档初始化
 * @author wujf
 * @date: 2015-9-9 上午09:45:44
 */
public class InitArchiveAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml) throws SSTException {
		Map params = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(params, this.getClass().getName());
		return new InitArchiveService().mainInitArchive(params);
	}
	
}
