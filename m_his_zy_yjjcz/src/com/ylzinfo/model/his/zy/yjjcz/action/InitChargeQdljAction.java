/**
 * 
 */
package com.ylzinfo.model.his.zy.yjjcz.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.action.BaseAction;
import com.start.sst.exception.SSTException;
import com.start.sst.soap.soapentity.SoapHead;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.InitChargeModelMatrix;

/**
 * 
 * @description: 现金充值初始化（前端累加）
 * @author fanggx
 * @date: 2018-1-10 下午04:08:18
 */
public class InitChargeQdljAction extends BaseAction {
	private Logger log=null;
	
	public InitChargeQdljAction() {
		log=Logger.getLogger(this.getClass().getName());
	}
	@Override
	public String performe(SoapHead head, String requestXml)
			throws SSTException {
		//解析报文
		Map param =SoapUtil.getRequestFieldMap(head, requestXml);
		SoapUtil.checkParam(param, this.getClass().getName());
		param.put("czqx00", "1");
		return new InitChargeModelMatrix().mainInitCharge(param);		
	}
}
