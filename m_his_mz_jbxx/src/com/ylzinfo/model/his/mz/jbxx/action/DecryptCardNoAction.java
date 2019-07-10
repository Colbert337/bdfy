/**
 * 
 */
package com.ylzinfo.model.his.mz.jbxx.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.jbxx.service.DecryptCardNoService;

/**
 * @description: Ω‚√‹ø®∫≈
 * @author wujf
 * @date: 2015-12-29 …œŒÁ10:35:39
 */
public class DecryptCardNoAction extends BaseAction {
	
	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		Map srq=SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new DecryptCardNoService().mainDecryptCardNo(srq);
	}

}
