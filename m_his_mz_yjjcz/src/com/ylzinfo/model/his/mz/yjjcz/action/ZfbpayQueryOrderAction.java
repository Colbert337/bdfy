package com.ylzinfo.model.his.mz.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.yjjcz.service.ZfbpayQueryOrderService;
/**
 * 
* @Project: eylzpay_wn
* @ClassName: ZfbpayQueryOrderAction 
* @Description:支付宝支付查询订单Action
* @author huangjj
* @date 2017-5-8 下午3:30:35 
*
 */
public class ZfbpayQueryOrderAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new ZfbpayQueryOrderService().zfbpayQueryOrder(srq);
	}
}
