package com.ylzinfo.model.his.mz.ybjk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.JdbcUtils;

public class YbjkDao {

	private Logger log = null;
	
	public YbjkDao() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public Vector queryYbConfig(Connection conn,Map params)throws SSTException {
		String sql = "select * from sst_ybconfig where zdbh00=:zdbh00:";
		Vector vt=JdbcUtils.executeQuery(conn, sql, params);
		return vt;
	}
	
	public void insertKc21(Connection conn, Map param, java.sql.Date date) throws SSTException {
		PreparedStatement ps = null;
		String sql = "insert into KC21(AKC190,CKC502,AAC003,AAC001,AAB001,AAE073,AKA130,AKC192,AKC193,AKC194,AKC195,AKC196,AAE011," +
					 "AAE036,AKA121,CKC126,AAC903,CKA038,CKA039,ZKB204,YYZYH,CKA102) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					 "?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("mzh000"));
			ps.setString(2, (String)param.get("ybcdno"));
			ps.setString(3, (String)param.get("xming0"));
			ps.setString(4, (String)param.get("ybgrbh"));
			ps.setString(5, (String)param.get("ybdwbh"));
			ps.setDouble(6, 1);
			ps.setString(7, "11");
//			ps.setDate(8, (java.sql.Date) new Date(times));
//			ps.setDate(8, new java.sql.Date(times));
			ps.setDate(8, date);
			ps.setString(9, "/");
			ps.setDate(10, date);
			ps.setString(11, "/");
			ps.setString(12, "/");
			ps.setString(13, (String)param.get("jbr00"));
			ps.setDate(14, date);
			ps.setString(15, "/");
			ps.setDouble(16, 0);
			ps.setString(17, (String)param.get("sfgwy0"));
			ps.setString(18, (String)param.get("ksbh00"));
			ps.setString(19, (String)param.get("ysbh00"));
			ps.setString(20, "/");
			ps.setString(21, "/");
			ps.setDouble(22, 0);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			log.error("插入病人就诊信息表出错:"+e.getMessage());
			throw new SSTException("插入病人就诊信息表出错:"+e.getMessage());
		}finally{
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void insertKc22(Connection conn, Map param, java.sql.Date date) throws SSTException {
		PreparedStatement ps = null;
		String sql = "insert into KC22(AKC190,AKC220,AAE072,AKC221,AKC515,AKC223,AKC224,AKC225,AKC226,AKC227,AKA070,AKA071,AKA072," + 
		"AKA073,AKC229,AAE040,ZKA100,ZKA101,AAE073,CKC048,CKC126,CKC125,AKA074,AKA075,AKA076,AKA077,AKA078,AKA079,AKA080,AKA081,BKA101," + 
		"BKA102,BKA103,BKA104,BKA105,CKA038,CKA039,ZKB204) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
		"?,?,?,?,?,?)";
		double ghf000 = Double.valueOf((String)param.get("ghf000"));
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("mzh000"));
			ps.setString(2, ((String)param.get("ybdjh0")).substring(1));
			ps.setString(3, (String)param.get("ybdjh0"));
			ps.setDate(4, date);
			ps.setString(5, (String)param.get("sfxmbm"));
			ps.setString(6, (String)param.get("sfxmmc"));
			ps.setString(7, "2");
			ps.setDouble(8, Double.valueOf(new DecimalFormat("#.0000").format(ghf000))); 
			ps.setDouble(9, 1);
			ps.setDouble(10, Double.valueOf(new DecimalFormat("#.00").format(ghf000)));
			ps.setString(11, "/");
			ps.setDouble(12, 1);
			ps.setString(13, "/");
			ps.setString(14, "/");
			ps.setDouble(15, 1);
			ps.setDate(16, date);
			ps.setString(17, "/");
			ps.setString(18, "/");
			ps.setDouble(19, 1);
			ps.setString(20, "/");
			ps.setDouble(21, 0);
			ps.setDouble(22, 0);
			ps.setString(23, "/");
			ps.setString(24, "/");
			ps.setDouble(25, 1);
			ps.setDouble(26, 1);
			ps.setString(27, "/");
			ps.setDouble(28, 1);
			ps.setDouble(29, 1);
			ps.setString(30, "/");
			ps.setDouble(31, 1);
			ps.setDouble(32, 0);
			ps.setString(33, "/");
			ps.setString(34, "/");
			ps.setString(35, "/");
			ps.setString(36, (String)param.get("ksbh00"));
			ps.setString(37, (String)param.get("ysbh00"));
			ps.setString(38, "/");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("插入收费项目明细信息表出错:"+e.getMessage());
			throw new SSTException("插入收费项目明细信息表出错:"+e.getMessage());
		}finally{
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
//	public String getYbcfh(Connection conn)throws SSTException{
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String sql = "";
//		String ybcfh="";
//		try{
//			sql = "select SEQ_YBCFH.nextval ybcfh from dual";
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//			if(rs.next()){
//				ybcfh = rs.getString(1);
//			}
//		}catch(Exception e){
//			log.error("获取医保处方号序列出错"+e.getMessage());
//			throw new SSTException("获取医保处方号序列出错"+e.getMessage());
//		}finally{
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (ps != null) {
//					ps.close();
//				}
//			} catch (Exception e) {
//				log.error(e.getMessage());
//				e.printStackTrace();
//			}
//		}
//		return ybcfh;
//	}
	
	public String getYbdjh(Connection conn)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String ybdjh="";
		try{
			sql = "select SEQ_YBDJH.nextval ybdjh from dual";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				ybdjh = rs.getString(1);
			}
		}catch(Exception e){
			log.error("获取医保单据号序列出错"+e.getMessage());
			throw new SSTException("获取医保单据号序列出错"+e.getMessage());
		}finally{
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return ybdjh;
	}
	
	public void updateKc22(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update KC22 set KC22.CKC125 = '1' where KC22.AAE072 =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("billno"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新Kc22表出错:"+e.getMessage());
			throw new SSTException("更新Kc22表出错:"+e.getMessage());
		}finally{
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
