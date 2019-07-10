package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.yjjcz.service.WxpayCancelOrderService;

/**
 * 
* @Project: eylzpay_wn
* @ClassName: WxpayCancelOrderAction 
* @Description:微信支付撤销订单Action
* @author huangjj
* @date 2017-5-8 下午3:35:26 
*
 */
public class WxpayCancelOrderAction extends BaseAction{

	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		Map srq = SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(srq, this.getClass().getName());
		return new WxpayCancelOrderService().wxpayCancelOrder(srq);
	}
}
