/**
 * 
 */
package com.ylzinfo.model.his.mz.yjjcz.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ctc.wstx.util.DataUtil;
import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.TransferUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.MapUtil;
import com.start.sst.util.StringUtil;
import com.ylzinfo.his.service.PatientService;
import com.ylzinfo.matrix.model.his.mz.yjjcz.CashChargeService;
import com.ylzinfo.matrix.model.his.mz.yjjcz.dao.ZhczmxDao;
import com.ylzinfo.model.his.mz.yjjcz.dao.YjjczDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;
import com.ylzinfo.util.HisDBUtil;
import com.ylzinfo.ykt.YktClient;
import com.ylzinfo.ykt.dao.YktDao;
import com.ylzinfo.ykt.service.YktService;
import com.ylzinfo.ykt.service.impl.YktServiceImpl;

/**
 * @description: �ֽ��ֵͨ�÷�����
 * @author wujf
 * @date: 2015-8-11 ����10:50:37
 */
public class CashChargeServiceImpl implements CashChargeService {
	
	private Logger logger=null;
	
	public CashChargeServiceImpl() {
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
		String jylsh0 = (String)MapUtil.getValue(param, "jylsh0", "������ˮ��", true);
		logger.info("["+jylsh0+"]����his�ӿڽ����ֽ��ֵ");
		Map hisReq = new HashMap();
		MapUtil.copyProperties(param, hisReq);
		Map hisRet = null;
		try{
			hisReq.put("czlx00", "CA");
			
			hisReq.put("type00", "1");
			
			CallHisService ws = new CallHisService("005");
			hisRet = ws.callWs(hisReq);
			String result = (String)hisRet.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)hisRet.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "δ֪����";
				}
				throw new SSTException(errmsg);
			}
			hisRet.put("xtgzh0", hisReq.get("jylsh0"));
			hisRet.put("cgjysj", DateUtil.getSystemDateTime());
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("֪ͨhis�쳣:"+e.getMessage());
			throw new SSTException("֪ͨhis�쳣:"+e.getMessage());
		}finally{
		}
		
		MapUtil.copyProperties(hisRet, res);
	}
}