package com.ylzinfo.service.his.mz.ws.webservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.neusoft.realone.pay.sdk.DefaultReaonePayClient;
import com.neusoft.realone.pay.sdk.RealonePayClient;
import com.neusoft.realone.pay.sdk.domain.RealOneFaceCancelTradeBizModel;
import com.neusoft.realone.pay.sdk.domain.RealOneFacePayPreBizModel;
import com.neusoft.realone.pay.sdk.domain.RealOnePayQueryPayBizModel;
import com.neusoft.realone.pay.sdk.enums.OrderType;
import com.neusoft.realone.pay.sdk.request.RealOneFaceCancelTradeRequest;
import com.neusoft.realone.pay.sdk.request.RealOneFacePayPreRequest;
import com.neusoft.realone.pay.sdk.request.RealOnePayQueryPayRequest;
import com.neusoft.realone.pay.sdk.response.RealOneFaceCancelTradeResponse;
import com.neusoft.realone.pay.sdk.response.RealOneFacePayPreResponse;
import com.neusoft.realone.pay.sdk.response.RealOnePayQueryPayResponse;
import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.BundleConfigUtil;

public class CallDxService {
	private static Logger log;
	private RealonePayClient payClient = null;
	
	public CallDxService() throws SSTException {
		log = Logger.getLogger(this.getClass().getName());
		try {			
			String dx_wsUrl = BundleConfigUtil.getConstantValue("s_his_mz_ws","dx_wsUrl");
			String dx_appid = BundleConfigUtil.getConstantValue("s_his_mz_ws","dx_appid");
			String dx_publickey = BundleConfigUtil.getConstantValue("s_his_mz_ws","dx_publickey");
			String dx_privatekey = BundleConfigUtil.getConstantValue("s_his_mz_ws","dx_privatekey");
			
			payClient = new DefaultReaonePayClient(dx_wsUrl,dx_appid,dx_privatekey,dx_publickey);
		} catch (Exception e) {
			log.error("初始化webservices链接出错：" + e.getMessage());
			throw new SSTException("初始化webservices链接出错：" + e.getMessage());
		}
	}
	
	//扫码支付预下单
	public Map RealOneFacePayPre(Map para) throws SSTException {
		Map retMap = new HashMap();
		try {
			String orderNo = (String)para.get("ptqqls");
			String jylx00 = (String)para.get("jylx00");
			String czje00 = (String)para.get("czje00");
			
			RealOneFacePayPreBizModel bizModel = new RealOneFacePayPreBizModel();
			bizModel.setOrderNo(orderNo);
			if(jylx00.equals("WX")) {
				bizModel.setPayWayType(31);
			} else if(jylx00.equals("ZFB")) {
				bizModel.setPayWayType(24);
			}
			bizModel.setOrderType(OrderType.HOS_CARD_ORDER.getId());
			bizModel.setSubject("自助机充值");
			bizModel.setTotalAmount(new BigDecimal(czje00));
			bizModel.setOvertime(5);
			
			RealOneFacePayPreRequest request = new RealOneFacePayPreRequest();
			request.setBizModel(bizModel);
			
			RealOneFacePayPreResponse response = payClient.execute(request);
			if(!response.isSuccess()){
				throw new SSTException(response.getMsg());
			}
			
			retMap.put("payurl", response.getPayInfo());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SSTException(e.getMessage());
		}
		return retMap;
	}
	
	//查询订单状态
	public Map RealOnePayQueryPay(Map para) throws SSTException {
		Map retMap = new HashMap();
		try {
			String orderNo = (String)para.get("ptddls");
			
			RealOnePayQueryPayBizModel bizModel = new RealOnePayQueryPayBizModel();
			bizModel.setOrderNo(orderNo);
			
			RealOnePayQueryPayRequest request = new RealOnePayQueryPayRequest();
			request.setBizModel(bizModel);
			
			RealOnePayQueryPayResponse response = payClient.execute(request);
			if(!response.isSuccess()){
				throw new SSTException(response.getMsg());
			}
			
			retMap.put("code", response.getCode());
			retMap.put("jyje00", response.getIncome());
			retMap.put("fhlsh0", response.getTradeNo());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SSTException(e.getMessage());
		}
		return retMap;
	}
	
	//撤销订单
	public Map RealOneFaceCancelTrade(Map para) throws SSTException {
		Map retMap = new HashMap();
		try {
			String orderNo = (String)para.get("ptddls");
			
			RealOneFaceCancelTradeBizModel bizModel = new RealOneFaceCancelTradeBizModel();
			bizModel.setOrderNo(orderNo);
			
			RealOneFaceCancelTradeRequest request = new RealOneFaceCancelTradeRequest();
			request.setBizModel(bizModel);
			
			RealOneFaceCancelTradeResponse response = payClient.execute(request);
			if(!response.isSuccess()){
				throw new SSTException(response.getMsg());
			}
			
			retMap.put("code", response.getCode());
			retMap.put("fhlsh0", response.getTradeNo());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SSTException(e.getMessage());
		}
		return retMap;
	}
}
