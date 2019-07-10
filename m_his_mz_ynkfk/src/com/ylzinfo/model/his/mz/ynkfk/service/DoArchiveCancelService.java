/**
 * 
 */
package com.ylzinfo.model.his.mz.ynkfk.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ynkfk.dao.ZzfkDao;

/**
 * @description: 取消发卡
 * @author wujf
 * @date: 2015-9-9 下午12:09:33
 */
public class DoArchiveCancelService {
	
	private Logger logger = null;
	
	private String errmsg="";
	
	public DoArchiveCancelService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainDoArchiveCancel(Map params) throws SSTException{
		Connection conn = null;
		String jdlsh0 = (String)params.get("jdlsh0");
		String tkzt00 = (String)params.get("tkzt00");
		ZzfkDao dao = new ZzfkDao();
		try {
			logger.info("***************取消建档开始[建档流水号:"+jdlsh0+",状态:"+tkzt00+"]***************");
			conn = DBUtil.getConnection();
			if(tkzt00.equals("0"))
			{
				dao.updateJdcshJdzt(conn, jdlsh0, ZzfkDao.TKSBQBTC);
			}
			else
			{
				dao.updateJdcshJdzt(conn, jdlsh0, ZzfkDao.TKCGYCSB);
			}
		    Vector vt = new Vector();
		    Map item = new HashMap();
		    item.put("mess00", "ok");
		    vt.add(item);
		    return SoapUtil.getSoapResponse(vt);
		} catch (Exception e) {
			errmsg = "取消建档失败：";
			logger.error(errmsg+e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		} finally {
			logger.info("关闭本地数据库连接");
			DBUtil.closeConnection(conn);
		}
	}
}
