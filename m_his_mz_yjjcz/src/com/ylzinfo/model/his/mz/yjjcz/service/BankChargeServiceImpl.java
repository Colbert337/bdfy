/**
 * 
 */
package com.ylzinfo.model.his.mz.yjjcz.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.MapUtil;
import com.ylzinfo.matrix.model.his.mz.yjjcz.BankChargeService;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @description: 银行卡充值通用服务类
 * @author wujf
 * @date: 2015-8-11 下午10:50:37
 */
public class BankChargeServiceImpl implements BankChargeService {
	
	private Logger logger=null;
	
	public BankChargeServiceImpl() {
		logger=Logger.getLogger(this.getClass().getName());
	}
	
	/* (non-Javadoc)
	 * @see com.ylzinfo.matrix.model.his.mz.yjjcz.CashChargeService#doThirdOperate(java.util.Map)
	 */
	@Override
	public void doThirdOperate(Connection conn,Map param,Map res) throws SSTException {
		
	}
	
	/* (non-Javadoc)
	 * @see com.ylzinfo.matrix.model.his.mz.yjjcz.CashChargeService#doThirdPartSgczOperate(java.util.Map)
	 */
	@Override
	public void doThirdPartSgczOperate(Connection conn,Map param,Map res) throws SSTException {
		
	}
	
	/* (non-Javadoc)
	 * @see com.ylzinfo.matrix.model.his.mz.yjjcz.CashChargeService#noticeChargeFinish(java.util.Map)
	 */
	@Override
	public void noticeChargeFinish(Connection conn,Map param,Map res) throws SSTException {
		String jylsh0 = (String)MapUtil.getValue(param, "jylsh0", "交易流水号", true);
		logger.info("["+jylsh0+"]调用his接口进行银行卡充值");
		Map hisReq = new HashMap();
		MapUtil.copyProperties(param, hisReq);
		Map hisRet = null;
		try{
			hisReq.put("czlx00", "CD");
			
			hisReq.put("type00", "1");
			
			CallHisService ws = new CallHisService("005");
			hisRet = ws.callWs(hisReq);
			String result = (String)hisRet.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)hisRet.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "未知错误";
				}
				throw new SSTException(errmsg);
			}
			hisRet.put("xtgzh0", hisReq.get("jylsh0"));
			hisRet.put("cgjysj", DateUtil.getSystemDateTime());
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("通知his异常:"+e.getMessage());
			throw new SSTException("通知his异常:"+e.getMessage());
		}finally{
		}
		
		MapUtil.copyProperties(hisRet, res);
	}
}