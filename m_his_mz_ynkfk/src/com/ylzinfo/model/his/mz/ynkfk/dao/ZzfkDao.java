package com.ylzinfo.model.his.mz.ynkfk.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.ProcedureUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.JdbcUtils;
import com.start.sst.util.MapUtil;
import com.start.sst.util.SoapUtil;

/**
 * 
 * @ClassName: ZzfaDao
 * @Description: 自助发卡数据层
 * @author huangjj
 * @date 2014-11-7 下午2:13:35
 *
 */
@SuppressWarnings({ "unchecked" })
public class ZzfkDao {
	
	private static Logger logger = null;
	private static String errmsg = "";
	public static String JDCSH = "0";// 建档初始化
	public static String TKSBQBTC = "1";// 吐卡失败,钱币吐出
	public static String TKCGYCSB = "2";// 吐卡成功,压钞失败
	public static String CZCGHISZCSB = "3";// 充值成功,建档失败
	public static String HISSUCCESS = "4";// 充值成功,HIS建档成功
	public static String KGSUCCESS = "5";// 卡管成功
	public static String KGFAIL = "6";// 卡管失败
	public static String YKTSUCCESS = "7";// 健康通失败
	public static String YKTFAIL = "8";// 健康通成功
	public static String ALLSUCCESS = "9";// 流程完成
	
	public ZzfkDao() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 
	 * @Description:获取交易流水号
	 * @param conn
	 * @return
	 * @throws SSTException
	 */
	public String getJylsh(Connection conn) throws SSTException {
		String sql = "select to_char(sysdate,'YYYYMMDD')||lpad(SEQ_SST_MZYJJ_ZHCZ.nextval,5,'0')||'T' jylsh0 from dual";
		Vector vec = JdbcUtils.executeQuery(conn, sql, null);
		return ((Map) vec.get(0)).get("jylsh0").toString();
	}
	
	/**
	 * 
	 * @Description: 插入预交金充值明细
	 * @param conn
	 * @param param
	 * @throws SSTException
	 */
	public void insertSstYjjZhczmx(Connection conn, Map param) throws SSTException {
		String sql = "insert into sst_mzyjj_zhczmx "
			+ "(JYLSH0, CXFS00,CXDM00, CZJE00,ZDBH00, "
			+ "YYJGDM, CZZTBZ, CSHSJ0,BRID00,YCMKLX,"
			+ "CZQX00"
			+ ") " 
			+ "values " 
			+ "(:jylsh0:,:cxfs00:,:cxdm00:,'0',:zdbh00:,"
			+ ":yyjgdm:, '0',:cshsj0:,:brid00:,:ycmklx:, "
			+ ":czqx00:"
			+ ") ";
		JdbcUtils.executeUpdate(conn, sql, param);
	}
	
	/**
	 * 
	 * @Description: 获取单笔流水号
	 * @param conn
	 * @return 
	 * @throws SSTException
	 */
	public static String getDblsh0(Connection conn) throws SSTException {
		String sql = "select to_char(sysdate,'YYYYMMDD')||lpad(SEQ_SST_MZYJJ_DBCZ.nextval,5,'0')||'D' dblsh0 from dual";
		Vector vt = JdbcUtils.executeQuery(conn, sql, null);
		return ((Map) vt.get(0)).get("dblsh0").toString();
	}
	
	/**
	 * 
	 * @Description:插入单笔预交金充值明细
	 * @param conn
	 * @param param
	 * @throws SSTException
	 */
	public void insertSstYjjDbczmx(Connection conn, Map param) throws SSTException {
		String sql = "insert into SST_MZYJJ_DBCZMX(DBLSH0,JYLSH0,YYJGDM,CXFS00,CXDM00,CZJE00,CPXRSJ,ZDBH00,CPXRZT,YCMKLX,ZZS000,DZJEC0,BRID00"
			+ ") values(:dblsh0:,:jylsh0:,:yyjgdm:,:cxfs00:,:cxdm00:,:czje00:,:cpxrsj:,:zdbh00:,'0',:ycmklx:,:zzs000:,:dzjec0:,:brid00: )";
		JdbcUtils.executeUpdate(conn, sql, param);
	}
	
	/**
	 * 
	 * @Description: 获取建档流水号
	 * @param conn
	 * @return
	 * @throws SSTException
	 */
	public String getJDLSH(Connection conn) throws SSTException {
		String sql = "select to_char(sysdate,'YYYYMMDD')||lpad(SEQ_SST_FKJ_JDLSH.nextval,6,'0') jdlsh0 from dual";
		Vector vec = JdbcUtils.executeQuery(conn, sql, null);
		return ((Map) vec.get(0)).get("jdlsh0").toString();
	}
	
	/**
	 * 
	 * @Description: 建档初始化（插入数据到建档初始化表中）
	 * @param conn
	 * @param param
	 * @throws SSTException
	 */
	public void doJdcsh(Connection conn, Map param) throws SSTException {
		String sql = "INSERT INTO sst_zzfk_jdcsh(jdlsh0, cardno, yyjgdm, zdbh00, xming0, xbie00, sfzhao, "
			+ "csrq00, lxdz00, lxdh00, zcycje, gbfei0, jdzt00, jylsh0, jdkssj, minzu0, ybcdno, ybgrbh, ybdwbh, ybrylb, ybklx0, brid00) "
			+ "VALUES (:jdlsh0:,:cxdm00:,:yyjgdm:,:zdbh00:,:xming0:,:xbie00:,:sfzhao:,:csrq00:,"
			+ ":lxdz00:,:lxdh00:,:zcycje:,:gbfei0:,0,:jylsh0:,:jdkssj:,:minzu0:,:ybcdno:,:ybgrbh:,:ybdwbh:,:ybrylb:,:ybklx0:,:brid00:)";
		JdbcUtils.executeUpdate(conn, sql, param);
	}
	
	/**
	 * 
	 * @Description: 通过建档流水号获取建档信息
	 * @param conn
	 * @param param
	 * @return
	 * @throws SSTException
	 */
	public Map getJdcshxx(Connection conn, Map param) throws SSTException {
		String sql = "SELECT * FROM SST_ZZFK_JDCSH WHERE jdlsh0=:jdlsh0:";
		Vector vt = JdbcUtils.executeQuery(conn, sql, param);
		return (Map)vt.get(0);
	}
	
	/**
	 * 
	 * @Description:修改单笔充值明细的充值状态
	 * @param conn
	 * @param dblsh0
	 * @throws SSTException:
	 */
	public void updateSstYjjDbczmx(Connection conn, String dblsh0) throws SSTException {
		Map params = new HashMap();
		params.put("dblsh0", dblsh0);
		params.put("cpxrzt", "2");
		String sql="update SST_MZYJJ_DBCZMX d set d.cpxrzt = :cpxrzt: where d.dblsh0 = :dblsh0:";
		JdbcUtils.executeUpdate(conn, sql, params);
	}
	
	/**
	 * 
	 * @Description:单笔累加
	 * @param conn
	 * @param jylsh0
	 * @throws SSTException
	 */
	public void updateSstYjjZhczmx(Connection conn, String jylsh0) throws SSTException {
		Map para=new HashMap();
		para.put("jylsh0", jylsh0); //交易流水号
		String sql="update Sst_MZYjj_Zhczmx t set t.czje00=(select sum(to_number(s.czje00)) from sst_mzyjj_dbczmx s "+
					"where s.jylsh0 = :jylsh0:  and s.cpxrzt ='2') where t.jylsh0 = :jylsh0:";
		JdbcUtils.executeUpdate(conn, sql, para);
	}
	
	/**
	 * 
	 * @Description: 更新充值状态标识
	 * @param conn
	 * @param jylsh0
	 * @param czztbz
	 * @throws SSTException
	 */
	public void updateCzztbz(Connection conn, String jylsh0, String czztbz) throws SSTException {
		Map param = new HashMap();
		param.put("jylsh0", jylsh0);
		param.put("czztbz", czztbz);
		String sql = "UPDATE sst_mzyjj_zhczmx " + "SET CZZTBZ = :czztbz: " + " WHERE JYLSH0 =:jylsh0:";
		JdbcUtils.executeUpdate(conn, sql, param);
	}
	
	/**
	 * 
	 * @Description: 更新建档状态
	 * @param conn
	 * @param jdlsh0
	 * @param jdzt00
	 * @throws SSTException
	 */
	public void updateJdcshJdzt(Connection conn, String jdlsh0, String jdzt00) throws SSTException {
		Map param = new HashMap();
		param.put("jdzt00", jdzt00);
		param.put("jdlsh0", jdlsh0);
		String sql = "UPDATE SST_ZZFK_JDCSH SET jdzt00=:jdzt00: WHERE jdlsh0=:jdlsh0:";
		JdbcUtils.executeUpdate(conn, sql, param);
	}
	
	/**
	 * 
	 * @Description: 更新充值状态标志为成功
	 * @param conn
	 * @param jylsh0
	 * @param xtgzh0
	 * @param cgjysj
	 * @throws SSTException
	 */
	public void updateCzztbzToSuccess(Connection conn, String jylsh0,String xtgzh0,String cgjysj,String cxdm00) throws SSTException {
		Map param = new HashMap();
		param.put("jylsh0",jylsh0);
		param.put("xtgzh0",xtgzh0);
		param.put("cgjysj",cgjysj);
		param.put("bdcgsj",DateUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		param.put("cxdm00",cxdm00);
		String sql = "UPDATE sst_mzyjj_zhczmx SET CZZTBZ = '2', XTGZH0 = :xtgzh0:, CGJYSJ = :cgjysj:,bdcgsj=:bdcgsj:,cxdm00=:cxdm00: "
			+ "WHERE JYLSH0 = :jylsh0:";
		JdbcUtils.executeUpdate(conn, sql, param);
	}
	
	/**
	 * 
	 * @Description: 更新建档状态
	 * @param conn
	 * @param jdlsh0
	 * @param jdjssj
	 * @throws SSTException
	 */
	public void finishJdcsh(Connection conn, String jdlsh0, String jdjssj, String cardno) throws SSTException {
		Map param = new HashMap();
		param.put("jdzt00", this.ALLSUCCESS);
		param.put("jdlsh0", jdlsh0);
		param.put("jdjssj", jdjssj);
		param.put("cardno", cardno);
		String sql = "UPDATE SST_ZZFK_JDCSH SET jdzt00=:jdzt00:,jdjssj=:jdjssj:,cardno=:cardno: WHERE jdlsh0=:jdlsh0:";
		JdbcUtils.executeUpdate(conn, sql, param);
	}
	
	/**
	 * 
	 * @Description: 根据身份证号获取卡号电话号码
	 * @param conn
	 * @param para
	 * @return
	 * @throws SSTException:
	 */
	public Vector callSpSstZzfkHqkhdh(Connection conn, Map para) throws SSTException {
		Vector vt = null;
		ProcedureUtil pu = new ProcedureUtil();
		try {
			long startTime = Long.parseLong(DateUtil.getNowTime());
			logger.info("***********调用callSpSstZzfkHqkhdh开始*****************");
			// 调用sp_sst_zzfk_hqkhdh存储过程
			vt = pu.executeQuery(conn, "sp_sst_zzfk_hqkhdh", para);
			long endTime = Long.parseLong(DateUtil.getNowTime());
			logger.info("根据身份证号获取卡号电话号码存储过程返回的数据为：" + vt.get(0));
			logger.info("***********调用callSpSstZzfkHqkhdh结束*****************");
			logger.info("sp_sst_zzfk_hqkhdh的本次运行时间为：" + DateUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception e) {
			errmsg = "调用SP_SST_ZZFK_HQKHDH存储过程出错:";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description: 判断卡号是否建过档
	 * @param conn
	 * @param para
	 * @return
	 * @throws SSTException:
	 */
	public Vector callSpSstZzfkKhsfcz(Connection conn, Map para) throws SSTException {
		Vector vt = null;
		ProcedureUtil pu = new ProcedureUtil();
		try {
			long startTime = Long.parseLong(DateUtil.getNowTime());
			logger.info("***********调用callSpSstZzfkKhsfcz开始*****************");
			// 调用SP_SST_ZZFK_KHSFCZ存储过程
			vt = pu.executeQuery(conn, "SP_SST_ZZFK_KHSFCZ", para);
			long endTime = Long.parseLong(DateUtil.getNowTime());
			logger.info("判断卡号是否建档存储过程返回的数据为：" + SoapUtil.getSoapResponse(vt));
			logger.info("***********调用callSpSstZzfkHqkhdh结束*****************");
			logger.info("SP_SST_ZZFK_KHSFCZ的本次运行时间为：" + DateUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception e) {
			errmsg = "调用SP_SST_ZZFK_KHSFCZ存储过程出错:";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description: 调用his确认建档存储过程
	 * @param conn
	 * @param para
	 * @return
	 * @throws SSTException:
	 */
	public Vector callSpSstDaxxdj(Connection conn, Map para) throws SSTException {
		Vector vt = null;
		ProcedureUtil pu = new ProcedureUtil();
		try {
			long startTime = Long.parseLong(DateUtil.getNowTime());
			logger.info("***********调用callSpSstDaxxdj开始*****************");
			// 调用SP_SST_ZZFK_DAXXDJ存储过程
			vt = pu.executeQuery(conn, "SP_SST_ZZFK_DAXXDJ", para);
			long endTime = Long.parseLong(DateUtil.getNowTime());
			logger.info("调用HIS确认建档返回的数据为：" + (Map) vt.get(0));
			logger.info("***********调用callSpSstDaxxdj结束*****************");
			logger.info("callSpSstDaxxdj的本次运行时间为：" + DateUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception e) {
			errmsg = "调用SP_SST_ZZFK_DAXXDJ存储过程出错:";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage(), e);
		}
	}
}
