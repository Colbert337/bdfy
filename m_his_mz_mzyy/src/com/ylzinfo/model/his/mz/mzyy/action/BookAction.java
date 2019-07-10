package com.ylzinfo.model.his.mz.mzyy.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.mzyy.bs.BookService;

/**
 * @describe :预约挂号确认
 * @classname:BookAction
 * @author   :Lan
 * @date     :2018-3-31
 */
public class BookAction extends BaseAction{
    
	/**
	 * 门诊预约
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public String performe(SoapHead head, String requestXml)throws SSTException {
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());   
		return new BookService().mainBook(srq);
	}

}
