package com.ylzinfo.model.his.mz.bgddy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.bgddy.bs.UpdateFlagAfterPrintService;
/**
 * 
* @Project: m_his_mz_bgddy
* @ClassName: UpdateFlagAfterPrintAction 
* @Description: 更新报告单状态为已打印
* @author huangjj
* @date 2015-10-21 上午10:40:06 
*
 */
public class UpdateFlagAfterPrintAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		UpdateFlagAfterPrintService bs = new UpdateFlagAfterPrintService();
		return bs.updateFlagAfterPrint(param);
	}
	
}
