package com.ylzinfo.model.his.mz.bgddy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.bgddy.bs.GetUserQueryListService;
/**
 * 
* @Project: m_his_mz_bgddy
* @ClassName: GetUserQueryListAction 
* @Description:查询所有未打印报告单
* @author huangjj
* @date 2015-10-19 下午3:42:17 
*
 */
public class GetUserQueryListAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		GetUserQueryListService bs = new GetUserQueryListService();
		return bs.getUserQueryList(param);
	}
	
}
