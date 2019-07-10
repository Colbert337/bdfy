package com.ylzinfo.model.his.zy.jbxx.dao;

import java.sql.Connection;
import java.util.Map;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.JdbcUtils;
/**
 * 
* @Project: m_his_mz_jbxx
* @ClassName: ZdpzDao 
* @Description: �ն�������Ϣ���ݲ�
* @author huangjj
* @date 2015-3-25 ����10:20:27 
*
 */
public class ZdpzDao {
	
	public Map getById(Map param) throws SSTException{
		Connection conn = DBUtil.getConnection();
		try{
			String sql = "select * from sst_zdpz where ylzzdbh=:zdbh00:";
			Map result = JdbcUtils.get(conn, sql, param);
			return result;
		}catch(Exception e){
			throw new SSTException("��ȡ�ն�������Ϣʧ��"+e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
