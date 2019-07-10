/**
 * 
 */
package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.MapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.CashChargeService;

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
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("֪ͨhis�쳣:"+e.getMessage());
			throw new SSTException("֪ͨhis�쳣:"+e.getMessage());
		}finally{
		}
		
		MapUtil.copyProperties(hisRet, res);
	}
}
