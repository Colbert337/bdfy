package com.ylzinfo.model.his.mz.bgddy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.bgddy.bs.GetReportPdfService;
/**
 * 
* @Project: m_his_mz_bgddy
* @ClassName: GetReportPdf 
* @Description: 获取报告单打印pdf
* @author huangjj
* @date 2015-10-20 上午10:54:08 
*
 */
public class GetReportPdfAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		GetReportPdfService bs = new GetReportPdfService();
		return bs.getReportPdf(param);
	}
	
}
