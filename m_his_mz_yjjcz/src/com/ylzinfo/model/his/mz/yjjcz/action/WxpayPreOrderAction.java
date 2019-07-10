package com.ylzinfo.model.his.mz.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.yjjcz.service.WxpayPreOrderService;

/**
 * 
* @Project: eylzpay_wn
* @ClassName: WxpayPreOrderAction 
* @Description: 微信支付预下单Action 
* @author huangjj
* @date 2017-5-8 下午3:27:12 
*
 */
public class WxpayPreOrderAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new WxpayPreOrderService().wxpayPreOrder(srq);
	}
}
