package com.ylzinfo.model.his.mz.mzyy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.mzyy.bs.QueryXcphDoctorService;

public class QueryXcphDoctorAction extends BaseAction{
	
	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new QueryXcphDoctorService().mainQueryBookDoctorMatrix(srq);
	}
}
