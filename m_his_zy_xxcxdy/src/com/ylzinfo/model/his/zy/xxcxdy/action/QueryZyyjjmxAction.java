package com.ylzinfo.model.his.zy.xxcxdy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.xxcxdy.bs.QueryZyyjjmxService;

/**
 * 
* @Project: m_his_zy_xxcxdy
* @ClassName: QueryZyyjjmxAction 
* @Description: 住院预交金信息查询
* @author bsj
* @date Apr 2, 2015 10:02:58 AM 
*
 */
public class QueryZyyjjmxAction extends BaseAction{
	
	@Override
	public String performe (SoapHead head, String requestXml) throws SSTException{
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new QueryZyyjjmxService().mainQueryInpatientPrepayment(srq);
	}
	
}