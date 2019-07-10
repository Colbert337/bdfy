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
 * @description: 预交金充值Dao
 * @author wujf
 * @date: 2015-11-3 下午08:40:16
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
	 * @Description: 删除预交金充值中间表
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
	 * @Description: 执行预交金充值过程
	 * @param conn
	 * @param params
	 * @throws SSTException:
	 */
	public void callSpSstYjjZhcz(Connection conn, Map params) throws SSTException{
		Vector vt = new Vector();
		ProcedureUtil util = new ProcedureUtil();
		logger.info("*****************调用his充值过程入参："+params.toString());
		vt = util.executeQuery(conn, "SP_SST_ZY_YJJ_ZHCZ", params);
		if(vt==null||vt.size()==0){
			throw new SSTException("*****his充值结果返回为空****");
		}
		Map param = new HashMap();
		param = (Map) vt.get(0);
		String flag = (String)param.get("flag");
		logger.info("调用存预缴金存储过程返回的标志flag（0，失败1，成功）："+flag);
		if("0".equals(flag)){
			throw new SSTException("执行预缴金充值存储过程出错："+param.get("errmsg"));
		}
	}
	/**
	 * 
	 * @Description: 获取预交金充值反馈信息
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
	 * @Description: 初始化预交金充值信息
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
		logger.info("插入初始化预交金充值信息到 SST_YJJ_ZHCZ中间表开始");
		JdbcUtils.executeUpdate(conn, yql, params);
		logger.info("插入初始化预交金充值信息到 SST_YJJ_ZHCZ中间表结束");
	}
	/**
	 * 
	 * @Description: 统计银行卡充值当天累积金额--院内
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
	 * @Description: 统计银行卡充值当天累积金额--健康通
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
	 * 更新交易状态待冲正
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
