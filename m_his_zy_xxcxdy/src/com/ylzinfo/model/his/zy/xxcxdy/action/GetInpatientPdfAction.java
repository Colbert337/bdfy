package com.ylzinfo.model.his.zy.xxcxdy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.xxcxdy.bs.GetInpatientPdfService;

/**
 * @description: 获取住院清单PDF文件
 * @author fanggx
 * @date: 2015-11-11 下午03:44:31
 */
public class GetInpatientPdfAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map request = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(request, this.getClass().getName());
		GetInpatientPdfService service = new GetInpatientPdfService();
		return service.mainGetInpatientPdf(request);
	}

}
