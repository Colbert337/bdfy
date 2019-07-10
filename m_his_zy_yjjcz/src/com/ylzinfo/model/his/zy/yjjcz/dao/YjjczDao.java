/**
 * 
 */
package com.ylzinfo.model.his.zy.yjjcz.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.ProcedureUtil;
import com.start.sst.util.JdbcUtils;

/**
 * @description: Ԥ�����ֵDao
 * @author wujf
 * @date: 2015-11-3 ����08:40:16
 */
public class YjjczDao {
	private Logger logger=null;
	
	public YjjczDao() {
		logger=Logger.getLogger(this.getClass().getName());
	}
	public String getYktJylsh0(Connection conn) throws SSTException{
		String sql = "select to_char(sysdate,'YYMMDD')||lpad(SEQ_SST_YKT_JYLSH0.nextval,7,'0')||'D' jylsh0 from dual";
		Vector vt = JdbcUtils.executeQuery(conn, sql, null);
		Map map = (Map) vt.get(0);
		return map.get("jylsh0").toString();
	}
	
	/**
	 * 
	 * @Description: ɾ��Ԥ�����ֵ�м��
	 * @param conn
	 * @param cardno
	 * @throws SSTException:
	 */
	public void deleteSstYjjZhcz(Connection conn, String zyhao0) throws SSTException{
		Map params = new HashMap();
		params.put("zyhao0", zyhao0);
		String yql = "delete from SST_YJJ_ZHCZ where zyhao0=:zyhao0:";
		JdbcUtils.executeUpdate(conn, yql, params);
	}
	/**
	 * 
	 * @Description: ִ��Ԥ�����ֵ����
	 * @param conn
	 * @param params
	 * @throws SSTException:
	 */
	public void callSpSstYjjZhcz(Connection conn, Map params) throws SSTException{
		Vector vt = new Vector();
		ProcedureUtil util = new ProcedureUtil();
		logger.info("*****************����his��ֵ������Σ�"+params.toString());
		vt = util.executeQuery(conn, "SP_SST_ZY_YJJ_ZHCZ", params);
		if(vt==null||vt.size()==0){
			throw new SSTException("*****his��ֵ�������Ϊ��****");
		}
		Map param = new HashMap();
		param = (Map) vt.get(0);
		String flag = (String)param.get("flag");
		logger.info("���ô�Ԥ�ɽ�洢���̷��صı�־flag��0��ʧ��1���ɹ�����"+flag);
		if("0".equals(flag)){
			throw new SSTException("ִ��Ԥ�ɽ��ֵ�洢���̳���"+param.get("errmsg"));
		}
	}
	/**
	 * 
	 * @Description: ��ȡԤ�����ֵ������Ϣ
	 * @param conn
	 * @param cardno
	 * @return
	 * @throws SSTException:
	 */
	public Map querySstYjjZhcz(Connection conn,String zyhao0) throws SSTException{
		Map params = new HashMap();
		params.put("zyhao0", zyhao0);
		String yql = "select yjjye0,cgjysj,pjh000 as xtgzh0 from SST_YJJ_ZHCZ where zyhao0=:zyhao0:";
		return JdbcUtils.get(conn, yql, params);
	}
	/**
	 * 
	 * @Description: ��ʼ��Ԥ�����ֵ��Ϣ
	 * @param conn
	 * @param params
	 * @throws SSTException:
	 */
	public void insertSstYjjZhcz(Connection conn,Map params) throws SSTException{
		String yql = "insert into SST_YJJ_ZHCZ" +
				     "(cardno,jylsh0,zdbh00,xming0,czje00," +         
				     " czqx00,czlb00,jylx00,yhlsh0,zdlsh0," +
				     " yhkh00,jyje00,yhshh0,yhzdh0,jyrq00," +
				     " jysj00,jyckh0,yhsqh0,qsrq00,yhpch0," +
				     " yktlsh,sstlsh,yktye0,zyhao0,fkhdm0,fkhmc0)" +
				     "values" +
				     "(" +
				     " :cxdm00:,:jylsh0:,:zdbh00:,:xming0:,:czje00:," +
				     " :czqx00:,:czlb00:,:jylx00:,:poslsh:,:poslsh:," +
				     " :yhkhao:,:czje00:,:shhao0:,:poszdh:,:posrq0:," +
				     " :possj0:,:zxlsh0:,:possqh:,:qsrq00:,:pospch:," +
				     " :yktlsh:,:ptqqls:,:yktye0:,:zyhao0:,:fkhdm0:,:fkhdm0:" +
				     ")";
		logger.info("�����ʼ��Ԥ�����ֵ��Ϣ�� SST_YJJ_ZHCZ�м��ʼ");
		JdbcUtils.executeUpdate(conn, yql, params);
		logger.info("�����ʼ��Ԥ�����ֵ��Ϣ�� SST_YJJ_ZHCZ�м�����");
	}
	/**
	 * 
	 * @Description: ͳ�����п���ֵ�����ۻ����--Ժ��
	 * @param conn
	 * @param params
	 * @return
	 * @throws SSTException:
	 */
	public Map queryBankYnCzje(Connection conn,Map params)throws SSTException{
		String sql = "select  NVL(sum(czje00),0) as ynje00 from SST_ZYYJJ_YHKCZ  where cxdm00=:cxdm00: and czqx00='1'and jyzt00='4' and bdcgsj like :tjrq00:";
		Vector vt=JdbcUtils.executeQuery(conn, sql, params);
		return (Map) vt.get(0);
	}
	/**
	 *  
	 * @Description: ͳ�����п���ֵ�����ۻ����--����ͨ
	 * @param conn
	 * @param params
	 * @return
	 * @throws SSTException:
	 */
	public Map queryBankYktCzje(Connection conn,Map params)throws SSTException{
		String sql = "select  NVL(sum(czje00),0) as yktje0 from SST_ZYYJJ_YHKCZ where cxdm00=:cxdm00: and czqx00='2'and jyzt00='4' and bdcgsj like :tjrq00:";
		Vector vt=JdbcUtils.executeQuery(conn, sql, params);
		return (Map) vt.get(0);
	}
	
	/**
	 * ���½���״̬������
	 * @param conn
	 * @param jylsh0
	 * @throws SSTException
	 */
	public void updateJyztToHisFail(Connection conn,Map param) throws SSTException{
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE SST_ZYYJJ_YHKCZ SET ");
		sql.append("jyzt00='3', ");
		sql.append("yhkhao=:yhkhao:,shhao0=:shhao0:,poslsh=:poslsh:,pospch=:pospch:,posrq0=:posrq0:, ");
		sql.append("possj0=:possj0:,zxlsh0=:zxlsh0:,possqh=:possqh:,poszdh=:poszdh:,mess00=:mess00: ");
		sql.append("where jylsh0 = :jylsh0: and jyzt00='0' and cxdm00 = :cxdm00:");
		JdbcUtils.executeUpdate(conn, sql.toString(), param);
	}
}
