package com.ylzinfo.model.his.zy.xxcxdy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.xxcxdy.bs.QueryZyqdhzService;

/**
 * 
* @Project: m_his_zy_xxcxdy
* @ClassName: QueryZyqdhzAction
* @Description: 住院清单汇总查询
* @author bsj
* @date Apr 2, 2015 10:02:58 AM 
*
 */
public class QueryZyqdhzAction extends BaseAction {
	
	@Override
	public String performe (SoapHead head, String requestXml) throws SSTException{
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new QueryZyqdhzService().mainQueryInpatientList(srq);
	}
	
}