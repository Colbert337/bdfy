/**
 * 
 */
package com.ylzinfo.model.his.mz.yjjcz.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.ProcedureUtil;
import com.start.sst.util.JdbcUtils;
import com.start.sst.util.StringUtil;

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
	public void deleteSstYjjZhcz(Connection conn, String cardno) throws SSTException{
		Map params = new HashMap();
		params.put("cardno", cardno);
		String yql = "delete from SST_YJJ_ZHCZ where cardno=:cardno:";
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
		logger.info("ִ��Ԥ�����ֵ����...��ʼ");
		logger.info("ִ��Ԥ�����ֵ����,��������Ϊ��"+params.toString());
		Vector vt = new Vector();
		ProcedureUtil util = new ProcedureUtil();
		vt = util.executeQuery(conn, "SP_SST_YJJ_ZHCZ", params);
		if(vt==null||vt.size()==0){
			throw new SSTException("���ؿ�");
		}
		Map param = new HashMap();
		param = (Map) vt.get(0);
		String flag = (String)param.get("flag");
		logger.info("���ô�Ԥ�ɽ�洢���̷��صı�־flag��0��ʧ��1���ɹ�����"+flag);
		logger.info("ִ��Ԥ�����ֵ����...����");
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
	public Map querySstYjjZhcz(Connection conn,String cardno) throws SSTException{
		logger.info("���м����ȡԤ�����ֵ������Ϣ...��ʼ"+"���ţ�"+cardno);
		Map params = new HashMap();
		params.put("cardno", cardno);
		String yql = "select yjjye0,cgjysj,pjh000 as xtgzh0 from SST_YJJ_ZHCZ where cardno=:cardno:";
		logger.info("���м����ȡԤ�����ֵ������Ϣ...����");
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
		String yql = null;
		String brid00 = StringUtil.Null2BlankAndTrim(params.get("brid00"));
		if("".equals(brid00)){
			yql = "insert into SST_YJJ_ZHCZ" +
		     "(cardno,jylsh0,zdbh00,xming0,czje00," +         
		     " czqx00,czlb00,jylx00,yhlsh0,zdlsh0," +
		     " yhkh00,jyje00,yhshh0,yhzdh0,jyrq00," +
		     " jysj00,jyckh0,yhsqh0,qsrq00,yhpch0," +
		     " yktlsh,sstlsh,yktye0,fkhdm0,fkhmc0)" +
		     "values" +
		     "(" +
		     " :cxdm00:,:jylsh0:,:zdbh00:,:xming0:,:czje00:," +
		     " :czqx00:,:czlb00:,:jylx00:,:poslsh:,:poslsh:," +
		     " :yhkhao:,:czje00:,:shhao0:,:poszdh:,:posrq0:," +
		     " :possj0:,:zxlsh0:,:possqh:,:qsrq00:,:pospch:," +
		     " :yktlsh:,:ptqqls:,:yktye0:,:fkhdm0:,:fkhdm0:" +
		     ")";
		}
		else{
			yql = "insert into SST_YJJ_ZHCZ" +
		     "(cardno,jylsh0,zdbh00,xming0,czje00," +         
		     " czqx00,czlb00,jylx00,yhlsh0,zdlsh0," +
		     " yhkh00,jyje00,yhshh0,yhzdh0,jyrq00," +
		     " jysj00,jyckh0,yhsqh0,qsrq00,yhpch0," +
		     " yktlsh,sstlsh,yktye0,fkhdm0,fkhmc0,brid00)" +
		     "values" +
		     "(" +
		     " :cxdm00:,:jylsh0:,:zdbh00:,:xming0:,:czje00:," +
		     " :czqx00:,:czlb00:,:jylx00:,:poslsh:,:poslsh:," +
		     " :yhkhao:,:czje00:,:shhao0:,:poszdh:,:posrq0:," +
		     " :possj0:,:zxlsh0:,:possqh:,:qsrq00:,:pospch:," +
		     " :yktlsh:,:ptqqls:,:yktye0:,:fkhdm0:,:fkhdm0:,:brid00:" +
		     ")";
		}
		logger.info("�����ʼ��Ԥ�����ֵ��Ϣ�� sst_yjj_zhcz�м��ʼ");
		String yql2 ="insert into SST_YJJ_ZHCZ" +
	     "(cardno,jylsh0,zdbh00,xming0,czje00," +         
	     " czqx00,czlb00,jylx00,yhlsh0,zdlsh0," +
	     " yhkh00,jyje00,yhshh0,yhzdh0,jyrq00," +
	     " jysj00,jyckh0,yhsqh0,qsrq00,yhpch0," +
	     " yktlsh,sstlsh,yktye0,zyhao0,fkhdm0,fkhmc0)" +
	     "values" +
	     "(" +"'"+params.get("cxdm00")+"'"+","+"'"+params.get("jylsh0")+"'"+","+"'"+params.get("zdbh00")+"'"+","+"'"+params.get("xming0")+"'"+","+"'"+
	     params.get("czje00")+"'"+","+"'"+params.get("czqx00")+"'"+","+"'"+params.get("czlb00")+"'"+","+"'"+params.get("jylx00")+"'"+","+"'"+
	     params.get("poslsh")+"'"+","+"'"+params.get("poslsh")+"'"+","+"'"+params.get("yhkhao")+"'"+","+"'"+params.get("czje00")+"'"+","+"'"+
	     params.get("shhao0")+"'"+","+"'"+params.get("poszdh")+"'"+","+"'"+params.get("posrq0")+"'"+","+"'"+params.get("possj0")+"'"+","+"'"+
	     params.get("zxlsh0")+"'"+","+"'"+params.get("possqh")+"'"+","+"'"+params.get("qsrq00")+"'"+","+"'"+params.get("pospch")+"'"+","+"'"+
	     params.get("yktlsh")+"'"+","+"'"+params.get("ptqqls")+"'"+","+"'"+params.get("yktye0")+"'"+","+"'"+params.get("zyhao0")+"'"+","+"'"+
	     params.get("fkhdm0")+"'"+","+"'"+params.get("fkhdm0")+"'"+")";
		logger.info(yql2);
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
		String sql = "select  NVL(sum(czje00),0) as ynje00 from SST_MZYJJ_YHKCZ  where cxdm00=:cxdm00: and czqx00='1'and jyzt00='4' and bdcgsj like :tjrq00: ";
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
		String sql = "select  NVL(sum(czje00),0) as yktje0 from SST_MZYJJ_YHKCZ  where cxdm00=:cxdm00: and czqx00='2'and jyzt00='4' and bdcgsj like :tjrq00: ";
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
		sql.append("UPDATE SST_MZYJJ_YHKCZ SET ");
		sql.append("jyzt00='3', ");
		sql.append("yhkhao=:yhkhao:,shhao0=:shhao0:,poslsh=:poslsh:,pospch=:pospch:,posrq0=:posrq0:, ");
		sql.append("possj0=:possj0:,zxlsh0=:zxlsh0:,possqh=:possqh:,poszdh=:poszdh:,mess00=:mess00:, ");
		sql.append("cxdm00=:cxdm00: ");
		sql.append("where jylsh0 = :jylsh0: and jyzt00='0'");
		JdbcUtils.executeUpdate(conn, sql.toString(), param);
	}
	
	public Map queryBankMoney(Connection conn,Map params)throws SSTException{
		String sql = "select count(*) cgjybs, sum(t.czje00) cgjyje from sst_mzyjj_yhkcz t where t.cshsj0 > :scsqsj: and t.cshsj0 < :bcsqsj: and t.zdbh00 = :zdbh00: and t.jyzt00 = '4' and t.czje00 != '0'";
		Vector vt=JdbcUtils.executeQuery(conn, sql, params);
		return (Map) vt.get(0);
	}
	
	public Map queryWxMoney(Connection conn,Map params)throws SSTException{
		String sql = "select count(*) cgjybs, sum(t.czje00) cgjyje from sst_mzyjj_wxcz t where t.cshsj0 > :scsqsj: and t.cshsj0 < :bcsqsj: and t.zdbh00 = :zdbh00: and t.czztbz = '6' and t.czje00 != '0'";
		Vector vt=JdbcUtils.executeQuery(conn, sql, params);
		return (Map) vt.get(0);
	}
	
	public Map queryZfbMoney(Connection conn,Map params)throws SSTException{
		String sql = "select count(*) cgjybs, sum(t.czje00) cgjyje from sst_mzyjj_zfbcz t where t.cshsj0 > :scsqsj: and t.cshsj0 < :bcsqsj: and t.zdbh00 = :zdbh00: and t.czztbz = '6' and t.czje00 != '0'";
		Vector vt=JdbcUtils.executeQuery(conn, sql, params);
		return (Map) vt.get(0);
	}
}
