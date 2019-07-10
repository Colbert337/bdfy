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
 * @Description: �����������ݲ�
 * @author huangjj
 * @date 2014-11-7 ����2:13:35
 *
 */
@SuppressWarnings({ "unchecked" })
public class ZzfkDao {
	
	private static Logger logger = null;
	private static String errmsg = "";
	public static String JDCSH = "0";// ������ʼ��
	public static String TKSBQBTC = "1";// �¿�ʧ��,Ǯ���³�
	public static String TKCGYCSB = "2";// �¿��ɹ�,ѹ��ʧ��
	public static String CZCGHISZCSB = "3";// ��ֵ�ɹ�,����ʧ��
	public static String HISSUCCESS = "4";// ��ֵ�ɹ�,HIS�����ɹ�
	public static String KGSUCCESS = "5";// ���ܳɹ�
	public static String KGFAIL = "6";// ����ʧ��
	public static String YKTSUCCESS = "7";// ����ͨʧ��
	public static String YKTFAIL = "8";// ����ͨ�ɹ�
	public static String ALLSUCCESS = "9";// �������
	
	public ZzfkDao() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 
	 * @Description:��ȡ������ˮ��
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
	 * @Description: ����Ԥ�����ֵ��ϸ
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
	 * @Description: ��ȡ������ˮ��
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
	 * @Description:���뵥��Ԥ�����ֵ��ϸ
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
	 * @Description: ��ȡ������ˮ��
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
	 * @Description: ������ʼ�����������ݵ�������ʼ�����У�
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
	 * @Description: ͨ��������ˮ�Ż�ȡ������Ϣ
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
	 * @Description:�޸ĵ��ʳ�ֵ��ϸ�ĳ�ֵ״̬
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
	 * @Description:�����ۼ�
	 * @param conn
	 * @param jylsh0
	 * @throws SSTException
	 */
	public void updateSstYjjZhczmx(Connection conn, String jylsh0) throws SSTException {
		Map para=new HashMap();
		para.put("jylsh0", jylsh0); //������ˮ��
		String sql="update Sst_MZYjj_Zhczmx t set t.czje00=(select sum(to_number(s.czje00)) from sst_mzyjj_dbczmx s "+
					"where s.jylsh0 = :jylsh0:  and s.cpxrzt ='2') where t.jylsh0 = :jylsh0:";
		JdbcUtils.executeUpdate(conn, sql, para);
	}
	
	/**
	 * 
	 * @Description: ���³�ֵ״̬��ʶ
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
	 * @Description: ���½���״̬
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
	 * @Description: ���³�ֵ״̬��־Ϊ�ɹ�
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
	 * @Description: ���½���״̬
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
	 * @Description: �������֤�Ż�ȡ���ŵ绰����
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
			logger.info("***********����callSpSstZzfkHqkhdh��ʼ*****************");
			// ����sp_sst_zzfk_hqkhdh�洢����
			vt = pu.executeQuery(conn, "sp_sst_zzfk_hqkhdh", para);
			long endTime = Long.parseLong(DateUtil.getNowTime());
			logger.info("�������֤�Ż�ȡ���ŵ绰����洢���̷��ص�����Ϊ��" + vt.get(0));
			logger.info("***********����callSpSstZzfkHqkhdh����*****************");
			logger.info("sp_sst_zzfk_hqkhdh�ı�������ʱ��Ϊ��" + DateUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception e) {
			errmsg = "����SP_SST_ZZFK_HQKHDH�洢���̳���:";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description: �жϿ����Ƿ񽨹���
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
			logger.info("***********����callSpSstZzfkKhsfcz��ʼ*****************");
			// ����SP_SST_ZZFK_KHSFCZ�洢����
			vt = pu.executeQuery(conn, "SP_SST_ZZFK_KHSFCZ", para);
			long endTime = Long.parseLong(DateUtil.getNowTime());
			logger.info("�жϿ����Ƿ񽨵��洢���̷��ص�����Ϊ��" + SoapUtil.getSoapResponse(vt));
			logger.info("***********����callSpSstZzfkHqkhdh����*****************");
			logger.info("SP_SST_ZZFK_KHSFCZ�ı�������ʱ��Ϊ��" + DateUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception e) {
			errmsg = "����SP_SST_ZZFK_KHSFCZ�洢���̳���:";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description: ����hisȷ�Ͻ����洢����
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
			logger.info("***********����callSpSstDaxxdj��ʼ*****************");
			// ����SP_SST_ZZFK_DAXXDJ�洢����
			vt = pu.executeQuery(conn, "SP_SST_ZZFK_DAXXDJ", para);
			long endTime = Long.parseLong(DateUtil.getNowTime());
			logger.info("����HISȷ�Ͻ������ص�����Ϊ��" + (Map) vt.get(0));
			logger.info("***********����callSpSstDaxxdj����*****************");
			logger.info("callSpSstDaxxdj�ı�������ʱ��Ϊ��" + DateUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception e) {
			errmsg = "����SP_SST_ZZFK_DAXXDJ�洢���̳���:";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage(), e);
		}
	}
}
