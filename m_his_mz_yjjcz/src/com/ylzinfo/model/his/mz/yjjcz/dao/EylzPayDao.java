package com.ylzinfo.model.his.mz.yjjcz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.start.sst.exception.SSTException;
import com.start.sst.logger.LogHelper;
import com.start.sst.util.DateUtil;

/**
 * 
* @Project: eylzpay
* @ClassName: ElzpayDao 
* @Description: 支付数据层
* @author huangjj
* @date 2017-2-14 下午7:59:43 
*
 */
public class EylzPayDao {
	private static LogHelper log = new LogHelper(EylzPayDao.class.getName());
	/**
	 * 
	* @Description: 获取微信交易流水号
	* @param conn
	* @param seqName
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String getWxJylsh(Connection conn)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String jylsh0="";
		try{
			sql = "select to_char(sysdate,'YYYYMMDD')||lpad(SEQ_SST_MZYJJ_WXCZ_JYLSH0.nextval,5,'0')||'W' jylsh0 from dual";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				jylsh0 = rs.getString(1);
			}
		}catch(Exception e){
			log.error("获取序列出错"+e.getMessage());
			throw new SSTException("获取序列出错"+e.getMessage());
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
		return jylsh0;
	}
	/**
	 * 
	* @Description: 获取聚合支付交易流水号
	* @param conn
	* @param seqName
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String getJhzfJylsh(Connection conn)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String jylsh0="";
		try{
			sql = "select to_char(sysdate,'YYYYMMDD')||lpad(SEQ_SST_MZYJJ_JHZF_JYLSH0.nextval,5,'0')||'J' jylsh0 from dual";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				jylsh0 = rs.getString(1);
			}
		}catch(Exception e){
			log.error("获取序列出错"+e.getMessage());
			throw new SSTException("获取序列出错"+e.getMessage());
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
		return jylsh0;
	}
	/**
	 * 
	* @Description: 获取平台请求流水
	* @param conn
	* @param seqName
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String getPtqqls(Connection conn)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String ptqqls="";
		try{
			sql = "select to_char(sysdate,'YYYYMMDD')||lpad(SEQ_PTQQLS.nextval,5,'0') ptqqls from dual";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				ptqqls = rs.getString(1);
			}
		}catch(Exception e){
			log.error("获取序列出错"+e.getMessage());
			throw new SSTException("获取序列出错"+e.getMessage());
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
		return ptqqls;
	}

	/**
	 * 
	* @Description:初始化微信预交金充值表
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void insertYjjWxcz(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String insertSQL = "";
		try{
			
			insertSQL = "insert into sst_mzyjj_wxcz (jylsh0,cxdm00,czje00,zdbh00,yyjgdm,czztbz," +
					"cshsj0,brid00,cxfs00,ptqqls,ptddls,sfzhao,xming0,ddlx00) values (?,?,?,?,?," +
					"?,?,?,?,?,?,?,?,?)";
			log.info("sql:" + insertSQL);
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, (String)param.get("jylsh0"));
			ps.setString(2, (String)param.get("cxdm00"));
			ps.setString(3, (String)param.get("czje00"));
			ps.setString(4, (String)param.get("zdbh00"));
			ps.setString(5, (String)param.get("yyjgdm"));
			ps.setString(6, (String)param.get("czztbz"));
			ps.setString(7, (String)param.get("cshsj0"));
			ps.setString(8, (String)param.get("brid00"));
			ps.setString(9, (String)param.get("cxfs00"));
			ps.setString(10, (String)param.get("ptqqls"));
			ps.setString(11, (String)param.get("ptddls"));
			ps.setString(12, (String)param.get("sfzhao"));
			ps.setString(13, (String)param.get("xming0"));
			ps.setString(14, (String)param.get("ddlx00"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("初始化插入微信充值表出错:"+e.getMessage());
			throw new SSTException("初始化插入微信充值表出错:"+e.getMessage());
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
	/**
	 * 
	* @Description:初始化聚合支付预交金充值表
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void insertYjjJhzf(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String insertSQL = "";
		try{
			
			insertSQL = "insert into sst_mzyjj_jhzf (jylsh0,cxdm00,czje00,zdbh00,yyjgdm,czztbz," +
					"cshsj0,brid00,cxfs00,ptqqls,ptddls,sfzhao,xming0,ddlx00) values (?,?,?,?,?," +
					"?,?,?,?,?,?,?,?,?)";
			log.info("sql:" + insertSQL);
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, (String)param.get("jylsh0"));
			ps.setString(2, (String)param.get("cxdm00"));
			ps.setString(3, (String)param.get("czje00"));
			ps.setString(4, (String)param.get("zdbh00"));
			ps.setString(5, (String)param.get("yyjgdm"));
			ps.setString(6, (String)param.get("czztbz"));
			ps.setString(7, (String)param.get("cshsj0"));
			ps.setString(8, (String)param.get("brid00"));
			ps.setString(9, (String)param.get("cxfs00"));
			ps.setString(10, (String)param.get("ptqqls"));
			ps.setString(11, (String)param.get("ptddls"));
			ps.setString(12, (String)param.get("sfzhao"));
			ps.setString(13, (String)param.get("xming0"));
			ps.setString(14, (String)param.get("ddlx00"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("初始化插入聚合支付充值表出错:"+e.getMessage());
			throw new SSTException("初始化插入聚合支付充值表出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 查询微信订单明细
	* @param conn
	* @param param
	* @return
	* @throws SSTException 
	* @throws
	 */
	public Map queryYjjWxcz(Connection conn,String jylsh0,String cxdm00)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Map result = new HashMap();
		try{
			sql = "select t.* from sst_mzyjj_wxcz t  where t.jylsh0 = ? and t.cxdm00 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, jylsh0);
			ps.setString(2, cxdm00);
			rs = ps.executeQuery();
			if(rs.next()){
				result.put("jylsh0", rs.getString("jylsh0"));
				result.put("cxdm00", rs.getString("cxdm00"));
				result.put("czje00", rs.getString("czje00"));
				result.put("czztbz", rs.getString("czztbz"));
				result.put("ptqqls", rs.getString("ptqqls"));
				result.put("cgjysj", rs.getString("cgjysj"));
				result.put("wxlsh0", rs.getString("wxlsh0"));
				result.put("ptddls", rs.getString("ptddls"));
				result.put("ddlx00", rs.getString("ddlx00"));
				result.put("yyjgdm", rs.getObject("yyjgdm"));
				result.put("zdbh00", rs.getString("zdbh00"));
				result.put("cxfs00", rs.getString("cxfs00"));
				result.put("brid00", rs.getString("brid00"));
				result.put("cshsj0", rs.getString("cshsj0"));
				result.put("xtgzh0", rs.getString("xtgzh0"));
			}
		}catch(Exception e){
			log.error("查询微信充值明细出错"+e.getMessage());
			throw new SSTException("查询微信充值明细出错"+e.getMessage());
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
		return result;
	}
	/**
	 * 
	* @Description: 查询微信订单明细
	* @param conn
	* @param param
	* @return
	* @throws SSTException 
	* @throws
	 */
	public Map queryZyYjjWxcz(Connection conn,String jylsh0,String cxdm00)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Map result = new HashMap();
		try{
			sql = "select t.* from SST_ZYYJJ_WXCZ t  where t.jylsh0 = ? and t.cxdm00 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, jylsh0);
			ps.setString(2, cxdm00);
			rs = ps.executeQuery();
			if(rs.next()){
				result.put("jylsh0", rs.getString("jylsh0"));
				result.put("cxdm00", rs.getString("cxdm00"));
				result.put("czje00", rs.getString("czje00"));
				result.put("czztbz", rs.getString("czztbz"));
				result.put("ptqqls", rs.getString("ptqqls"));
				result.put("cgjysj", rs.getString("cgjysj"));
				result.put("wxlsh0", rs.getString("wxlsh0"));
				result.put("ddlx00", rs.getString("ddlx00"));
			}
		}catch(Exception e){
			log.error("查询微信充值明细出错"+e.getMessage());
			throw new SSTException("查询微信充值明细出错"+e.getMessage());
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
		return result;
	}
	/**
	 * 
	* @Description: 更新微信充值明细为订单成功
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateWxcz2Success(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_wxcz d set d.czztbz = ?,d.ddjysj=?,wxlsh0=?,cgjysj=?,xtgzh0=? where d.jylsh0 =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("czztbz"));
			ps.setString(2, (String)param.get("ddjysj"));
			ps.setString(3, (String)param.get("wxlsh0"));
			ps.setString(4, (String)param.get("cgjysj"));
			ps.setString(5, (String)param.get("xtgzh0"));
			ps.setString(6, (String)param.get("jylsh0"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新微信充值明细为订单成功出错:"+e.getMessage());
			throw new SSTException("更新微信充值明细为订单成功出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 更新微信充值明细为His成功
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateWxcz2HisSuccess(Connection conn,String jylsh0,String xtgzh0,String cgjysj)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_wxcz d set d.czztbz = '6',xtgzh0=?,cgjysj=? where d.jylsh0 =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, xtgzh0);
			ps.setString(2, cgjysj);
			ps.setString(3, jylsh0);
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新微信充值明细为His成功出错:"+e.getMessage());
			throw new SSTException("更新微信充值明细为His成功出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 更新微信充值明细为His失败
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateWxcz2HisFail(Connection conn,String jylsh0,String errmsg)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_wxcz d set d.czztbz = '7',d.errmsg =? where d.jylsh0 =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, errmsg);
			ps.setString(2, jylsh0);
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新微信充值明细为His失败出错:"+e.getMessage());
			throw new SSTException("更新微信充值明细为His失败出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 更新微信充值明细订单状态
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateWxczCzztbz(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_wxcz d set d.czztbz =?,d.errmsg =? where  d.cxdm00 = ? and d.jylsh0 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("czztbz"));
			ps.setString(2, (String)param.get("errmsg"));
			ps.setString(3, (String)param.get("cxdm00"));
			ps.setString(4, (String)param.get("jylsh0"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新微信充值明细订单状态出错:"+e.getMessage());
			throw new SSTException("更新微信充值明细订单状态出错:"+e.getMessage());
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
	
	/**
	 * 
	* @Description: 获取支付宝交易流水号
	* @param conn
	* @param seqName
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String getZfbJylsh(Connection conn)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String jylsh0="";
		try{
			sql = "select to_char(sysdate,'YYYYMMDD')||lpad(SEQ_SST_MZYJJ_ZFBCZ_JYLSH0.nextval,5,'0')||'Z' jylsh0 from dual";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				jylsh0 = rs.getString(1);
			}
		}catch(Exception e){
			log.error("获取序列出错"+e.getMessage());
			throw new SSTException("获取序列出错"+e.getMessage());
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
		return jylsh0;
	}
	/**
	 * 
	* @Description: 获取支付宝交易流水号
	* @param conn
	* @param seqName
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String getZfbPtqqls(Connection conn)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String ptqqls="";
		try{
			sql = "select to_char(sysdate,'YYYYMMDD')||lpad(SEQ_PTQQLS.nextval,5,'0') ptqqls from dual";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				ptqqls = rs.getString(1);
			}
		}catch(Exception e){
			log.error("获取序列出错"+e.getMessage());
			throw new SSTException("获取序列出错"+e.getMessage());
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
		return ptqqls;
	}

	/**
	 * 
	* @Description:初始化支付宝预交金充值表
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void insertYjjZfbcz(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String insertSQL = "";
		try{
			
			insertSQL = "insert into sst_mzyjj_zfbcz (jylsh0,cxdm00,czje00,zdbh00,yyjgdm,czztbz," +
					"cshsj0,brid00,cxfs00,ptqqls,ptddls,sfzhao,xming0,ddlx00) values (?,?,?,?,?," +
					"?,?,?,?,?,?,?,?,?)";
			log.info("sql:" + insertSQL);
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, (String)param.get("jylsh0"));
			ps.setString(2, (String)param.get("cxdm00"));
			ps.setString(3, (String)param.get("czje00"));
			ps.setString(4, (String)param.get("zdbh00"));
			ps.setString(5, (String)param.get("yyjgdm"));
			ps.setString(6, (String)param.get("czztbz"));
			ps.setString(7, (String)param.get("cshsj0"));
			ps.setString(8, (String)param.get("brid00"));
			ps.setString(9, (String)param.get("cxfs00"));
			ps.setString(10, (String)param.get("ptqqls"));
			ps.setString(11, (String)param.get("ptddls"));
			ps.setString(12, (String)param.get("sfzhao"));
			ps.setString(13, (String)param.get("xming0"));
			ps.setString(14, (String)param.get("ddlx00"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("初始化插入支付宝充值表出错:"+e.getMessage());
			throw new SSTException("初始化插入支付宝充值表出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 查询支付宝订单明细
	* @param conn
	* @param param
	* @return
	* @throws SSTException 
	* @throws
	 */
	public Map queryYjjZfbcz(Connection conn,String jylsh0,String cxdm00)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Map result = new HashMap();
		try{
			sql = "select t.* from sst_mzyjj_zfbcz t  where t.jylsh0 = ? and t.cxdm00 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, jylsh0);
			ps.setString(2, cxdm00);
			rs = ps.executeQuery();
			if(rs.next()){
				result.put("jylsh0", rs.getString("jylsh0"));
				result.put("cxdm00", rs.getString("cxdm00"));
				result.put("czje00", rs.getString("czje00"));
				result.put("czztbz", rs.getString("czztbz"));
				result.put("ptqqls", rs.getString("ptqqls"));
				result.put("ptddls", rs.getString("ptddls"));
				result.put("zfblsh", rs.getString("zfblsh"));
				result.put("ddlx00", rs.getString("ddlx00"));
				result.put("yyjgdm", rs.getObject("yyjgdm"));
				result.put("zdbh00", rs.getString("zdbh00"));
				result.put("cxfs00", rs.getString("cxfs00"));
				result.put("brid00", rs.getString("brid00"));
				result.put("cshsj0", rs.getString("cshsj0"));
				result.put("xtgzh0", rs.getString("xtgzh0"));
			}
		}catch(Exception e){
			log.error("查询支付宝充值明细出错"+e.getMessage());
			throw new SSTException("查询支付宝充值明细出错"+e.getMessage());
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
		return result;
	}
	/**
	 * 
	* @Description: 查询住院支付宝订单明细
	* @param conn
	* @param param
	* @return
	* @throws SSTException 
	* @throws
	 */
	public Map queryZyYjjZfbcz(Connection conn,String jylsh0,String cxdm00)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Map result = new HashMap();
		try{
			sql = "select t.* from sst_zyyjj_zfbcz t  where t.jylsh0 = ? and t.cxdm00 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, jylsh0);
			ps.setString(2, cxdm00);
			rs = ps.executeQuery();
			if(rs.next()){
				result.put("jylsh0", rs.getString("jylsh0"));
				result.put("cxdm00", rs.getString("cxdm00"));
				result.put("czje00", rs.getString("czje00"));
				result.put("czztbz", rs.getString("czztbz"));
				result.put("ptqqls", rs.getString("ptqqls"));
				result.put("ddlx00", rs.getString("ddlx00"));
			}
		}catch(Exception e){
			log.error("查询住院支付宝充值明细出错"+e.getMessage());
			throw new SSTException("查询住院支付宝充值明细出错"+e.getMessage());
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
		return result;
	}
	/**
	 * 
	* @Description: 更新支付宝充值明细为订单成功
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateZfbcz2Success(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_zfbcz d set d.czztbz = ?,d.ddjysj=?,zfblsh=?,cgjysj=?,xtgzh0=? where d.jylsh0 =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("czztbz"));
			ps.setString(2, (String)param.get("ddjysj"));
			ps.setString(3, (String)param.get("zfblsh"));
			ps.setString(4, (String)param.get("cgjysj"));
			ps.setString(5, (String)param.get("xtgzh0"));
			ps.setString(6, (String)param.get("jylsh0"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新支付宝充值明细为订单成功出错:"+e.getMessage());
			throw new SSTException("更新支付宝充值明细为订单成功出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 更新支付宝充值明细为his成功
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateZfbcz2HisSuccess(Connection conn,String jylsh0,String xtgzh0,String cgjysj)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_zfbcz d set d.czztbz = '6',d.xtgzh0 = ?,cgjysj=? where d.jylsh0 =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, xtgzh0);
			ps.setString(2, cgjysj);
			ps.setString(3, jylsh0);
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新支付宝充值明细为his成功出错:"+e.getMessage());
			throw new SSTException("更新支付宝充值明细为his成功出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 更新支付宝充值明细为his成功
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateZfbcz2HisFail(Connection conn,String jylsh0,String errmsg)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_zfbcz d set d.czztbz = '7',d.errmsg =? where d.jylsh0 =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, errmsg);
			ps.setString(2, jylsh0);
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新支付宝充值明细为his失败出错:"+e.getMessage());
			throw new SSTException("更新支付宝充值明细为his失败出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 更新支付宝充值明细订单状态
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateZfbczCzztbz(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_zfbcz d set d.czztbz =?,d.errmsg =? where  d.cxdm00 = ? and d.jylsh0 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("czztbz"));
			ps.setString(2, (String)param.get("errmsg"));
			ps.setString(3, (String)param.get("cxdm00"));
			ps.setString(4, (String)param.get("jylsh0"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新支付宝充值明细订单状态出错:"+e.getMessage());
			throw new SSTException("更新支付宝充值明细订单状态出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 更新支付宝充值明细订单状态
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateZfbczTklsh(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_zfbcz d set d.tklsh0 =? where  d.cxdm00 = ? and d.jylsh0 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("tklsh0"));
			ps.setString(2, (String)param.get("cxdm00"));
			ps.setString(3, (String)param.get("jylsh0"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新支付宝退款流水号出错:"+e.getMessage());
			throw new SSTException("更新支付宝退款流水号出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 更新微信充值明细订单状态
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateWxczTklsh(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_wxcz d set d.tklsh0 =? where  d.cxdm00 = ? and d.jylsh0 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("tklsh0"));
			ps.setString(2, (String)param.get("cxdm00"));
			ps.setString(3, (String)param.get("jylsh0"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新微信退款流水号出错:"+e.getMessage());
			throw new SSTException("更新微信退款流水号出错:"+e.getMessage());
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
	/**
	 * 微信充值预交金退款
	 * @param conn
	 * @param param
	 * @throws SSTException
	 */
	public void initWxczYjjTk(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String insertSQL = "";
		try{
			
			insertSQL = "insert into sst_mzyjj_wxtk (tklsh0,jylsh0,cxdm00,cxfs00,czje00,tkje00,tkzt00," +
					"cshsj0,qqlsh0,ddlx00) values (?,?,?,?,?,?,?,?,?,?)";
			log.info("sql:" + insertSQL);
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, (String)param.get("tklsh0"));
			ps.setString(2, (String)param.get("jylsh0"));
			ps.setString(3, (String)param.get("cxdm00"));
			ps.setString(4, (String)param.get("cxfs00"));
			ps.setString(5, (String)param.get("czje00"));
			ps.setString(6, (String)param.get("tkje00"));
			ps.setString(7, (String)param.get("tkzt00"));
			ps.setString(8, (String)param.get("cshsj0"));
			ps.setString(9, (String)param.get("qqlsh0"));
			ps.setString(10, (String)param.get("ddlx00"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("初始化插入微信预交金退款出错:"+e.getMessage());
			throw new SSTException("初始化插入微信预交金退款出错:"+e.getMessage());
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
	/**
	 * 支付宝充值预交金退款
	 * @param conn
	 * @param param
	 * @throws SSTException
	 */
	public void initZfbczYjjTk(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String insertSQL = "";
		try{
			
			insertSQL = "insert into sst_mzyjj_zfbtk (tklsh0,jylsh0,cxdm00,cxfs00,czje00,tkje00,tkzt00," +
					"cshsj0,qqlsh0,ddlx00) values (?,?,?,?,?,?,?,?,?,?)";
			log.info("sql:" + insertSQL);
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, (String)param.get("tklsh0"));
			ps.setString(2, (String)param.get("jylsh0"));
			ps.setString(3, (String)param.get("cxdm00"));
			ps.setString(4, (String)param.get("cxfs00"));
			ps.setString(5, (String)param.get("czje00"));
			ps.setString(6, (String)param.get("tkje00"));
			ps.setString(7, (String)param.get("tkzt00"));
			ps.setString(8, (String)param.get("cshsj0"));
			ps.setString(9, (String)param.get("qqlsh0"));
			ps.setString(10, (String)param.get("ddlx00"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("初始化插入支付宝预交金退款出错:"+e.getMessage());
			throw new SSTException("初始化插入支付宝预交金退款出错:"+e.getMessage());
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
	/**
	 * 微信充值预交金退款
	 * @param conn
	 * @param param
	 * @throws SSTException
	 */
	public void updateWxczYjjTkzt(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_wxtk d set d.tkzt00 =?,errmsg=?,cgjysj=? where  d.cxdm00 = ? and d.tklsh0 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("tkzt00"));
			ps.setString(2, (String)param.get("errmsg"));
			ps.setString(3, (String)param.get("cgjysj"));
			ps.setString(4, (String)param.get("cxdm00"));
			ps.setString(5, (String)param.get("tklsh0"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新微信充值预交金退款状态出错:"+e.getMessage());
			throw new SSTException("更新微信充值预交金退款状态出错:"+e.getMessage());
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
	/**
	 * 支付宝充值预交金退款
	 * @param conn
	 * @param param
	 * @throws SSTException
	 */
	public void updateZfbczYjjTkzt(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_zfbtk d set d.tkzt00 =?,errmsg=?,cgjysj=? where  d.cxdm00 = ? and d.tklsh0 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("tkzt00"));
			ps.setString(2, (String)param.get("errmsg"));
			ps.setString(3, (String)param.get("cgjysj"));
			ps.setString(4, (String)param.get("cxdm00"));
			ps.setString(5, (String)param.get("tklsh0"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新支付宝充值预交金退款状态出错:"+e.getMessage());
			throw new SSTException("更新支付宝充值预交金退款状态出错:"+e.getMessage());
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
	/**
	 * 
	* @Description: 查询支付宝订单明细
	* @param conn
	* @param param
	* @return
	* @throws SSTException 
	* @throws
	 */
	public Map queryYjjJhzf(Connection conn,String jylsh0,String cxdm00)throws SSTException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		Map result = new HashMap();
		try{
			sql = "select t.* from sst_mzyjj_jhzf t  where t.jylsh0 = ? and t.cxdm00 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, jylsh0);
			ps.setString(2, cxdm00);
			rs = ps.executeQuery();
			if(rs.next()){
				result.put("jylsh0", rs.getString("jylsh0"));
				result.put("cxdm00", rs.getString("cxdm00"));
				result.put("czje00", rs.getString("czje00"));
				result.put("czztbz", rs.getString("czztbz"));
				result.put("ptqqls", rs.getString("ptqqls"));
				result.put("ptddls", rs.getString("ptddls"));
				result.put("zfblsh", rs.getString("zfblsh"));
				result.put("ddlx00", rs.getString("ddlx00"));
				result.put("yyjgdm", rs.getObject("yyjgdm"));
				result.put("zdbh00", rs.getString("zdbh00"));
				result.put("cxfs00", rs.getString("cxfs00"));
				result.put("brid00", rs.getString("brid00"));
				result.put("cshsj0", rs.getString("cshsj0"));
			}
		}catch(Exception e){
			log.error("查询聚合支付充值明细出错"+e.getMessage());
			throw new SSTException("查询聚合支付充值明细出错"+e.getMessage());
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
		return result;
	}
	/**
	 * 
	* @Description: 更新聚合支付充值明细为订单成功
	* @param conn
	* @param param
	* @throws SSTException 
	* @throws
	 */
	public void updateJhzf2Success(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String sql = "";
		try{
			sql = "update sst_mzyjj_jhzf d set d.czztbz = ?,d.ddjysj=?,zfblsh=?,cgjysj=? where d.jylsh0 =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String)param.get("czztbz"));
			ps.setString(2, (String)param.get("ddjysj"));
			ps.setString(3, (String)param.get("zfblsh"));
			ps.setString(4, (String)param.get("cgjysj"));
			ps.setString(5, (String)param.get("jylsh0"));
			ps.executeUpdate();
		}catch(Exception e){
			log.error("更新聚合支付充值明细为订单成功出错:"+e.getMessage());
			throw new SSTException("更新聚合支付充值明细为订单成功出错:"+e.getMessage());
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
	
	public void insertWxczSgcz(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String insertSQL = "";
		try{
			param.put("sgczsj", DateUtil.getSystemDateTime());
			param.put("bdcgsj", DateUtil.getSystemDateTime());
			insertSQL = "insert into sst_mzyjj_wxcz_sgcz_log (jylsh0,cxdm00,czje00,zdbh00,yyjgdm,czqzt0," +
					"cshsj0,sgczsj,cxfs00,ptqqls,ptddls,xming0,wxlsh0,ddlx00,bdcgsj) values (?,?,?,?,?," +
					"?,?,?,?,?,?,?,?,?,?)";
			log.info("sql:" + insertSQL);
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, (String)param.get("jylsh0"));
			ps.setString(2, (String)param.get("cxdm00"));
			ps.setString(3, (String)param.get("czje00"));
			ps.setString(4, (String)param.get("zdbh00"));
			ps.setString(5, (String)param.get("yyjgdm"));
			ps.setString(6, (String)param.get("czztbz"));
			ps.setString(7, (String)param.get("cshsj0"));
			ps.setString(8, (String)param.get("sgczsj"));
			ps.setString(9, (String)param.get("cxfs00"));
			ps.setString(10, (String)param.get("ptqqls"));
			ps.setString(11, (String)param.get("ptddls"));
			ps.setString(12, (String)param.get("xming0"));
			ps.setString(13, (String)param.get("wxlsh0"));
			ps.setString(14, (String)param.get("ddlx00"));
			ps.setString(15, (String)param.get("bdcgsj"));		
			ps.executeUpdate();
		}catch(Exception e){
			log.error("插入微信充值冲正表出错:"+e.getMessage());
			throw new SSTException("插入微信充值冲正表出错:"+e.getMessage());
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
	
	public void insertZfbczSgcz(Connection conn,Map param)throws SSTException{
		PreparedStatement ps = null;
		String insertSQL = "";
		try {
			param.put("sgczsj", DateUtil.getSystemDateTime());
			param.put("bdcgsj", DateUtil.getSystemDateTime());
			insertSQL = "insert into sst_mzyjj_zfbcz_sgcz_log (jylsh0,cxdm00,czje00,zdbh00,yyjgdm,czqzt0," +
					"cshsj0,sgczsj,cxfs00,ptqqls,ptddls,xming0,zfblsh,ddlx00,bdcgsj) values (?,?,?,?,?," +
					"?,?,?,?,?,?,?,?,?,?)";
			log.info("sql:" + insertSQL);
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, (String)param.get("jylsh0"));
			ps.setString(2, (String)param.get("cxdm00"));
			ps.setString(3, (String)param.get("czje00"));
			ps.setString(4, (String)param.get("zdbh00"));
			ps.setString(5, (String)param.get("yyjgdm"));
			ps.setString(6, (String)param.get("czztbz"));
			ps.setString(7, (String)param.get("cshsj0"));
			ps.setString(8, (String)param.get("sgczsj"));
			ps.setString(9, (String)param.get("cxfs00"));
			ps.setString(10, (String)param.get("ptqqls"));
			ps.setString(11, (String)param.get("ptddls"));
			ps.setString(12, (String)param.get("xming0"));
			ps.setString(13, (String)param.get("zfblsh"));
			ps.setString(14, (String)param.get("ddlx00"));
			ps.setString(15, (String)param.get("bdcgsj"));		
			ps.executeUpdate();
		}catch(Exception e){
			log.error("插入支付宝充值冲正表出错:"+e.getMessage());
			throw new SSTException("插入支付宝充值冲正表出错:"+e.getMessage());
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
