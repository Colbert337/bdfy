package com.ylzinfo.model.his.mz.mzyy.dao;

import java.sql.Connection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.JdbcUtils;

public class MzyyDao {

	private Logger log = null;
	
	public MzyyDao() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public void insertSstLogYyghForMzgh(Connection conn,Map params) throws SSTException{
		if (null == conn) {
			throw new SSTException("数据连接资源为空，无法访问数据:insertSstLogYyghForMzgh");
		}
		String sql = "insert into sst_log_yygh values(to_char(sysdate,'YYYYMMDD')||SEQ_SST_LOG_MZYY_ID.nextval,"
			+ ":cxdm00:,:cxfs00:,:ksbh00:,:ysbh00:,:yysj00:,'4',:ghf000:,'',:jysj00:,:brfb00:,:ybzhzf:,:tczf00:,:grxjzf:)";
		log.info("insert SQL : " + sql);
		JdbcUtils.executeUpdate(conn, sql, params);
	}
	
	public void insertSstLogYyghForMzyy(Connection conn,Map params) throws SSTException{
		if (null == conn) {
			throw new SSTException("数据连接资源为空，无法访问数据:insertSstLogYyghForMzyy");
		}
		String sql = "insert into sst_log_yygh values(to_char(sysdate,'YYYYMMDD')||SEQ_SST_LOG_MZYY_ID.nextval,"
			+ ":cxdm00:,:cxfs00:,:ksbh00:,:ysbh00:,:yysj00:,'1',:ghf000:,'',:jysj00:,:brfb00:,:ybzhzf:,:tczf00:,:grxjzf:)";
		log.info("insert SQL : " + sql);
		JdbcUtils.executeUpdate(conn, sql, params);
	}
	
	public void insertSstLogYyghForYyqh(Connection conn,Map params) throws SSTException{
		if (null == conn) {
			throw new SSTException("数据连接资源为空，无法访问数据:insertSstLogYyghForYyqh");
		}
		String sql = "insert into sst_log_yygh values(to_char(sysdate,'YYYYMMDD')||SEQ_SST_LOG_MZYY_ID.nextval,"
			+ ":cxdm00:,:cxfs00:,:ksbh00:,:ysbh00:,:yysj00:,'3',:ghf000:,'',:jysj00:)";
		log.info("insert SQL : " + sql);
		JdbcUtils.executeUpdate(conn, sql, params);
	}
}