/**
 * 
 */
package com.ylzinfo.model.his.zy.jbxx.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.JdbcUtils;
import com.start.sst.util.SSTErrorCode;
import com.start.sst.util.StringUtil;

/**
 * @description 个人基本信息Dao
 * @author wujf
 * @time 2014-9-29 下午07:21:13
 */
public class GrjbxxDao {
	
	private Logger log;
	
	public GrjbxxDao() {
		log = Logger.getLogger(this.getClass().getName());
	}
	/**
	 * 获取个人基本信息
	 * @param termid 终端编号
	 * @param yyjgdm 医院机构代码
	 * @param cardtype 卡类型 0 院内卡 1 社保卡
	 * @param cardno 卡号
	 * @param byrc00 备用入参
	 * @return
	 * @throws SSTException
	 */
	public Vector<HashMap<String, String>> getGrjbxx(String termid,
			String yyjgdm, String cardtype, String cardno, String byrc00) throws SSTException{
		String resMsg = null;
		Connection conn = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		Vector<HashMap<String, String>> vt = null;
		String sql = "{Call SP_SST_GETYYGRJBXX(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try{
			log.info("<<<<<getGrjbxx 获取医院个人基本信息>>>>>>");
			log.info("<<<<<访问医院信息库存储过程>>>>>>");
			conn = DBUtil.getConnection();
			callStmt = conn.prepareCall(sql);
			callStmt.setString(1, termid);
			callStmt.setString(2, yyjgdm);
			callStmt.setString(3, cardtype);
			callStmt.setString(4, cardno);
			callStmt.setString(5, byrc00);
			callStmt.registerOutParameter(6, Types.VARCHAR);// 姓名
			callStmt.registerOutParameter(7, Types.VARCHAR);// 余额
			callStmt.registerOutParameter(8, Types.VARCHAR);// 性别
			callStmt.registerOutParameter(9, Types.VARCHAR);// 出生日期
			callStmt.registerOutParameter(10, Types.VARCHAR);// 分中心编号(市医保为0，省医保为1，保健为2，两费为3，自费为4，农保为5，铁保为6)
			callStmt.registerOutParameter(11, Types.VARCHAR); // 联系电话号码
			callStmt.registerOutParameter(12, Types.VARCHAR);//银医通开关（1为开启，0为关闭）
			callStmt.registerOutParameter(13, Types.VARCHAR);//就诊卡状态（1不走银医通，2已余额下载，3未余额下载,4银医通未开户）
			callStmt.registerOutParameter(14, Types.VARCHAR);// 备用出参
			callStmt.registerOutParameter(15, Types.VARCHAR);// 出错字段
			callStmt.execute();

			resMsg = StringUtil.Null2BlankAndTrim(callStmt.getString(15));

			if (resMsg != null && !resMsg.equals("")) {
				throw new SSTException(SSTErrorCode.DB, resMsg);
			}

			log.info("<<<<<getGrjbxx结束...（医院信息库）>>>>>>");

			vt = new Vector<HashMap<String, String>>();
			HashMap<String, String> mp = new HashMap<String, String>();
			String name = "";
			name = callStmt.getString(6);
			mp.put("xming0", StringUtil.Null2BlankAndTrim(name));
			mp.put("yjjye0", StringUtil.Null2BlankAndTrim(callStmt.getString(7)));
			mp.put("xbie00", StringUtil.Null2BlankAndTrim(callStmt.getString(8)));
			mp.put("csrq00", StringUtil.Null2BlankAndTrim(callStmt.getString(9)));
			mp.put("fzxbh0", StringUtil.Null2BlankAndTrim(callStmt.getString(10)));
			mp.put("lxdh00", StringUtil.Null2BlankAndTrim(callStmt.getString(11)));
			mp.put("yytkg0", StringUtil.Null2BlankAndTrim(callStmt.getString(12)));
			mp.put("jzkzt0", StringUtil.Null2BlankAndTrim(callStmt.getString(13)));
			mp.put("bycc00", StringUtil.Null2BlankAndTrim(callStmt.getString(14)));
			mp.put("errmsg", StringUtil.Null2BlankAndTrim(callStmt.getString(15)));
			vt.add(mp);
			
		}catch (Exception e) {
			log.error("获取个人信息出错:" + e.getMessage());
			throw new SSTException(SSTErrorCode.DB,e.getMessage());
		}finally{
			try {
				if (rs != null) {
					rs.close();
				}
				if (callStmt != null) {
					callStmt.close();
				}
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				log.error("获取个人基本信息,关闭数据库连接出错:" + e.getMessage());
			}
		}
		return vt;
		
		
	}
	
	/**
	 * 获取终端私有属性
	 * @param conn
	 * @param zdbh00
	 * @return
	 * @throws SSTException
	 */
	public Vector getPrivateAttributes(Connection conn,String zdbh00) throws SSTException{
		Map param = new HashMap();
		param.put("zdbh00", zdbh00);
		String yql = "select * from sst_terminal_private where zdbh00=:zdbh00:";
		return JdbcUtils.executeQuery(conn, yql, param);
	}

}
