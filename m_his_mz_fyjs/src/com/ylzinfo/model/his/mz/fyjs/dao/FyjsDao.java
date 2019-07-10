package com.ylzinfo.model.his.mz.fyjs.dao;

import java.sql.Connection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.JdbcUtils;

public class FyjsDao {

	private Logger log = null;
	
	public FyjsDao() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public void insertSstLogSfjs(Connection conn,Map params) throws SSTException{
		if (null == conn) {
			throw new SSTException("数据连接资源为空，无法访问数据:insertSstLogSfjs");
		}
		String sql = "insert into sst_log_sfjs values(to_char(sysdate,'YYYYMMDD')||SEQ_SST_LOG_SFJS.nextval,"
			+ ":cxdm00:,'',:zdbh00:,'','','','','','',:zfje00:,:jysj00:,'',:cxfs00:,:cxdm00:)";
		log.info("insert SQL : " + sql);
		JdbcUtils.executeUpdate(conn, sql, params);
	}
}
