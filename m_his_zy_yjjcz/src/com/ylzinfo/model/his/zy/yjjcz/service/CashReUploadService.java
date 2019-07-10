package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.MapUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.dao.DbczmxDao;
import com.ylzinfo.matrix.model.his.zy.yjjcz.dao.ZhczmxDao;

public class CashReUploadService {
	
	private Logger logger=null;
	
	public CashReUploadService() {
		logger=Logger.getLogger(this.getClass().getName());
	}
	
	public String doCashReUpload(Map param)throws SSTException{
		DbczmxDao dbDao=new DbczmxDao();
		ZhczmxDao zhDao=new ZhczmxDao();
		Connection conn=null;
		Map zhczmx=null;
		Vector<HashMap<String, String>> vt = new Vector<HashMap<String, String>>();
		String dblsh0=(String)MapUtil.getValue(param, "dblsh0", "单笔流水号", true);
		String jylsh0 = (String)MapUtil.getValue(param, "jylsh0", "交易流水号", true);
		String czje00 = (String)MapUtil.getValue(param, "czje00", "充值金额", true);
		try {
			conn=DBUtil.getConnection();
			zhczmx=zhDao.getZhczmx(conn, jylsh0);
			if(zhczmx == null || zhczmx.size() ==0){
				throw new SSTException("找不到交易流水号为["+jylsh0+"]的账户充值明细");
			}
			if("0".equals(zhczmx.get("czztbz"))){
				conn.setAutoCommit(false);
				logger.info("获取单笔充值明细");
				Map dbczmx = dbDao.queryDbczmxByDblsh0(conn, dblsh0);
				if(dbczmx == null || dbczmx.size() ==0){
					throw new SSTException("找不到单笔流水号为["+dblsh0+"]的单笔充值明细");
				}
				if("0".equals(dbczmx.get("cpxrzt")))
				{
					logger.info("更新单笔充值明细记录的金额为新金额且吸币状态修改为吸币成功且累加成功状态");
					dbDao.updateDbczmx2Success(conn,dblsh0,czje00);
					logger.info("累加到账户充值明细");
					dbDao.addToZhczmx(conn,jylsh0);
					logger.info("["+jylsh0+"]更新账户充值明细记录为吸币成功，His更新失败");
					zhDao.updateCzztbzToWaitRecharge(conn,jylsh0);
				}
				//提交事物
				conn.commit();
			}
			return SoapUtil.getSoapResponse(vt);
		}catch (Exception e) {
			//事务回滚
			String msg="单笔累加失败"+e.getMessage()+";事务回滚";
			logger.error(msg);
			DBUtil.rollback(conn);
			throw new SSTException(msg,e);
		}finally{
			logger.info("关闭本地数据库连接");
			DBUtil.closeConnection(conn);
		}
	}
}
