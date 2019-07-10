/**
 * 
 */
package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.MapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.BankChargeService;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @description: ���п���ֵͨ�÷�����
 * @author wujf
 * @date: 2015-8-11 ����10:50:37
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
		String jylsh0 = (String)MapUtil.getValue(param, "jylsh0", "������ˮ��", true);
		logger.info("["+jylsh0+"]����his�ӿڽ������п���ֵ");
		Map hisReq = new HashMap();
		MapUtil.copyProperties(param, hisReq);
		Map hisRet = null;
		try{
			hisReq.put("zffs00", "4");
			hisReq.put("lsh000", hisReq.get("zxlsh0"));
			hisReq.put("jysj00", DateUtil.getSystemDateTime());
			String zdbh00 = (String)hisReq.get("zdbh00");
			hisReq.put("czybh0", "999" + zdbh00.substring(10,12));
			hisReq.put("ly0000", "02");
			
			CallHisService ws = new CallHisService("saveIpPrepayToIIH");
			Map map = ws.callWs(hisReq);
			
			ws = new CallHisService("getPrepaymentBalace");
			map = ws.callWs(hisReq);
			Vector<HashMap<String, String>> vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("��ȡ����סԺ��Ϣ");
				throw new SSTException("��ȡ����סԺ��Ϣ");
			}
			
			vt.get(0).put("cgjysj", DateUtil.getSystemDateTime());
			
			hisRet = vt.get(0);			
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("֪ͨhis�쳣:"+e.getMessage());
			throw new SSTException("֪ͨhis�쳣:"+e.getMessage());
		}finally{
		}
		
		MapUtil.copyProperties(hisRet, res);
	}
}
