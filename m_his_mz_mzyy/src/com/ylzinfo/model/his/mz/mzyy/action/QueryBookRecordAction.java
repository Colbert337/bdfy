package com.ylzinfo.model.his.mz.mzyy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.mzyy.bs.QueryBookRecordService;
/**
 * 
 * @description: 获取预约记录
 * @author baisj
 * @date: Aug 29, 2015 3:22:05 PM
 */
public class QueryBookRecordAction extends BaseAction{
    
	/**
	 * 获取预约记录
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new QueryBookRecordService().mainQueryBookRecord(srq);
	}
	
}
