package com.ylzinfo.model.his.mz.mzyy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.mzyy.bs.QueryBookDepartmentService;
/**
 * 
 * @description: ��ȡԤԼ����
 * @author baisj
 * @date: Aug 29, 2015 3:21:21 PM
 */
public class QueryBookDepartmentAction extends BaseAction{
    
	/**
	 * ��ȡԤԼ����
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new QueryBookDepartmentService().mainQueryBookDepartment(srq);
	}

}
