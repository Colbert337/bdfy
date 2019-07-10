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
 * @description ���˻�����ϢDao
 * @author wujf
 * @time 2014-9-29 ����07:21:13
 */
public class GrjbxxDao {
	
	private Logger log;
	
	public GrjbxxDao() {
		log = Logger.getLogger(this.getClass().getName());
	}
	/**
	 * ��ȡ���˻�����Ϣ
	 * @param termid �ն˱��
	 * @param yyjgdm ҽԺ��������
	 * @param cardtype ������ 0 Ժ�ڿ� 1 �籣��
	 * @param cardno ����
	 * @param byrc00 �������
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
			log.info("<<<<<getGrjbxx ��ȡҽԺ���˻�����Ϣ>>>>>>");
			log.info("<<<<<����ҽԺ��Ϣ��洢����>>>>>>");
			conn = DBUtil.getConnection();
			callStmt = conn.prepareCall(sql);
			callStmt.setString(1, termid);
			callStmt.setString(2, yyjgdm);
			callStmt.setString(3, cardtype);
			callStmt.setString(4, cardno);
			callStmt.setString(5, byrc00);
			callStmt.registerOutParameter(6, Types.VARCHAR);// ����
			callStmt.registerOutParameter(7, Types.VARCHAR);// ���
			callStmt.registerOutParameter(8, Types.VARCHAR);// �Ա�
			callStmt.registerOutParameter(9, Types.VARCHAR);// ��������
			callStmt.registerOutParameter(10, Types.VARCHAR);// �����ı��(��ҽ��Ϊ0��ʡҽ��Ϊ1������Ϊ2������Ϊ3���Է�Ϊ4��ũ��Ϊ5������Ϊ6)
			callStmt.registerOutParameter(11, Types.VARCHAR); // ��ϵ�绰����
			callStmt.registerOutParameter(12, Types.VARCHAR);//��ҽͨ���أ�1Ϊ������0Ϊ�رգ�
			callStmt.registerOutParameter(13, Types.VARCHAR);//���￨״̬��1������ҽͨ��2��������أ�3δ�������,4��ҽͨδ������
			callStmt.registerOutParameter(14, Types.VARCHAR);// ���ó���
			callStmt.registerOutParameter(15, Types.VARCHAR);// �����ֶ�
			callStmt.execute();

			resMsg = StringUtil.Null2BlankAndTrim(callStmt.getString(15));

			if (resMsg != null && !resMsg.equals("")) {
				throw new SSTException(SSTErrorCode.DB, resMsg);
			}

			log.info("<<<<<getGrjbxx����...��ҽԺ��Ϣ�⣩>>>>>>");

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
			log.error("��ȡ������Ϣ����:" + e.getMessage());
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
				log.error("��ȡ���˻�����Ϣ,�ر����ݿ����ӳ���:" + e.getMessage());
			}
		}
		return vt;
		
		
	}
	
	/**
	 * ��ȡ�ն�˽������
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
