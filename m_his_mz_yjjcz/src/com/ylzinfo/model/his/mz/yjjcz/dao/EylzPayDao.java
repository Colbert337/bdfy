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
* @Description: ֧�����ݲ�
* @author huangjj
* @date 2017-2-14 ����7:59:43 
*
 */
public class EylzPayDao {
	private static LogHelper log = new LogHelper(EylzPayDao.class.getName());
	/**
	 * 
	* @Description: ��ȡ΢�Ž�����ˮ��
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
			log.error("��ȡ���г���"+e.getMessage());
			throw new SSTException("��ȡ���г���"+e.getMessage());
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
	* @Description: ��ȡ�ۺ�֧��������ˮ��
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
			log.error("��ȡ���г���"+e.getMessage());
			throw new SSTException("��ȡ���г���"+e.getMessage());
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
	* @Description: ��ȡƽ̨������ˮ
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
			log.error("��ȡ���г���"+e.getMessage());
			throw new SSTException("��ȡ���г���"+e.getMessage());
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
	* @Description:��ʼ��΢��Ԥ�����ֵ��
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
			log.error("��ʼ������΢�ų�ֵ�����:"+e.getMessage());
			throw new SSTException("��ʼ������΢�ų�ֵ�����:"+e.getMessage());
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
	* @Description:��ʼ���ۺ�֧��Ԥ�����ֵ��
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
			log.error("��ʼ������ۺ�֧����ֵ�����:"+e.getMessage());
			throw new SSTException("��ʼ������ۺ�֧����ֵ�����:"+e.getMessage());
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
	* @Description: ��ѯ΢�Ŷ�����ϸ
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
			log.error("��ѯ΢�ų�ֵ��ϸ����"+e.getMessage());
			throw new SSTException("��ѯ΢�ų�ֵ��ϸ����"+e.getMessage());
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
	* @Description: ��ѯ΢�Ŷ�����ϸ
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
			log.error("��ѯ΢�ų�ֵ��ϸ����"+e.getMessage());
			throw new SSTException("��ѯ΢�ų�ֵ��ϸ����"+e.getMessage());
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
	* @Description: ����΢�ų�ֵ��ϸΪ�����ɹ�
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
			log.error("����΢�ų�ֵ��ϸΪ�����ɹ�����:"+e.getMessage());
			throw new SSTException("����΢�ų�ֵ��ϸΪ�����ɹ�����:"+e.getMessage());
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
	* @Description: ����΢�ų�ֵ��ϸΪHis�ɹ�
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
			log.error("����΢�ų�ֵ��ϸΪHis�ɹ�����:"+e.getMessage());
			throw new SSTException("����΢�ų�ֵ��ϸΪHis�ɹ�����:"+e.getMessage());
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
	* @Description: ����΢�ų�ֵ��ϸΪHisʧ��
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
			log.error("����΢�ų�ֵ��ϸΪHisʧ�ܳ���:"+e.getMessage());
			throw new SSTException("����΢�ų�ֵ��ϸΪHisʧ�ܳ���:"+e.getMessage());
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
	* @Description: ����΢�ų�ֵ��ϸ����״̬
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
			log.error("����΢�ų�ֵ��ϸ����״̬����:"+e.getMessage());
			throw new SSTException("����΢�ų�ֵ��ϸ����״̬����:"+e.getMessage());
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
	* @Description: ��ȡ֧����������ˮ��
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
			log.error("��ȡ���г���"+e.getMessage());
			throw new SSTException("��ȡ���г���"+e.getMessage());
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
	* @Description: ��ȡ֧����������ˮ��
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
			log.error("��ȡ���г���"+e.getMessage());
			throw new SSTException("��ȡ���г���"+e.getMessage());
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
	* @Description:��ʼ��֧����Ԥ�����ֵ��
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
			log.error("��ʼ������֧������ֵ�����:"+e.getMessage());
			throw new SSTException("��ʼ������֧������ֵ�����:"+e.getMessage());
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
	* @Description: ��ѯ֧����������ϸ
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
			log.error("��ѯ֧������ֵ��ϸ����"+e.getMessage());
			throw new SSTException("��ѯ֧������ֵ��ϸ����"+e.getMessage());
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
	* @Description: ��ѯסԺ֧����������ϸ
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
			log.error("��ѯסԺ֧������ֵ��ϸ����"+e.getMessage());
			throw new SSTException("��ѯסԺ֧������ֵ��ϸ����"+e.getMessage());
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
	* @Description: ����֧������ֵ��ϸΪ�����ɹ�
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
			log.error("����֧������ֵ��ϸΪ�����ɹ�����:"+e.getMessage());
			throw new SSTException("����֧������ֵ��ϸΪ�����ɹ�����:"+e.getMessage());
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
	* @Description: ����֧������ֵ��ϸΪhis�ɹ�
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
			log.error("����֧������ֵ��ϸΪhis�ɹ�����:"+e.getMessage());
			throw new SSTException("����֧������ֵ��ϸΪhis�ɹ�����:"+e.getMessage());
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
	* @Description: ����֧������ֵ��ϸΪhis�ɹ�
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
			log.error("����֧������ֵ��ϸΪhisʧ�ܳ���:"+e.getMessage());
			throw new SSTException("����֧������ֵ��ϸΪhisʧ�ܳ���:"+e.getMessage());
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
	* @Description: ����֧������ֵ��ϸ����״̬
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
			log.error("����֧������ֵ��ϸ����״̬����:"+e.getMessage());
			throw new SSTException("����֧������ֵ��ϸ����״̬����:"+e.getMessage());
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
	* @Description: ����֧������ֵ��ϸ����״̬
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
			log.error("����֧�����˿���ˮ�ų���:"+e.getMessage());
			throw new SSTException("����֧�����˿���ˮ�ų���:"+e.getMessage());
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
	* @Description: ����΢�ų�ֵ��ϸ����״̬
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
			log.error("����΢���˿���ˮ�ų���:"+e.getMessage());
			throw new SSTException("����΢���˿���ˮ�ų���:"+e.getMessage());
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
	 * ΢�ų�ֵԤ�����˿�
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
			log.error("��ʼ������΢��Ԥ�����˿����:"+e.getMessage());
			throw new SSTException("��ʼ������΢��Ԥ�����˿����:"+e.getMessage());
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
	 * ֧������ֵԤ�����˿�
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
			log.error("��ʼ������֧����Ԥ�����˿����:"+e.getMessage());
			throw new SSTException("��ʼ������֧����Ԥ�����˿����:"+e.getMessage());
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
	 * ΢�ų�ֵԤ�����˿�
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
			log.error("����΢�ų�ֵԤ�����˿�״̬����:"+e.getMessage());
			throw new SSTException("����΢�ų�ֵԤ�����˿�״̬����:"+e.getMessage());
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
	 * ֧������ֵԤ�����˿�
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
			log.error("����֧������ֵԤ�����˿�״̬����:"+e.getMessage());
			throw new SSTException("����֧������ֵԤ�����˿�״̬����:"+e.getMessage());
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
	* @Description: ��ѯ֧����������ϸ
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
			log.error("��ѯ�ۺ�֧����ֵ��ϸ����"+e.getMessage());
			throw new SSTException("��ѯ�ۺ�֧����ֵ��ϸ����"+e.getMessage());
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
	* @Description: ���¾ۺ�֧����ֵ��ϸΪ�����ɹ�
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
			log.error("���¾ۺ�֧����ֵ��ϸΪ�����ɹ�����:"+e.getMessage());
			throw new SSTException("���¾ۺ�֧����ֵ��ϸΪ�����ɹ�����:"+e.getMessage());
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
			log.error("����΢�ų�ֵ���������:"+e.getMessage());
			throw new SSTException("����΢�ų�ֵ���������:"+e.getMessage());
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
			log.error("����֧������ֵ���������:"+e.getMessage());
			throw new SSTException("����֧������ֵ���������:"+e.getMessage());
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
