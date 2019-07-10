package com.ylzinfo.model.his.zy.xxcxdy.dao;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.ProcedureUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.JdbcUtils;
/**
 * 
* @Project: m_his_zy_xxcxdy
* @ClassName: XxcxdyDao 
* @Description: 住院信息查询数据层 
* @author bsj
* @date Apr 2, 2015 10:45:34 AM 
*
 */
public class XxcxdyDao {
	private Logger logger = null;
	public String errmsg = "";

	public XxcxdyDao() {
		logger = Logger.getLogger(this.getClass().getName());
	}

	/**
     * 
    * @Description: 删除住院清单中间表缓存信息
    * @param conn
    * @param param 自助机发送给前置的soap报文参数
    * @return
    * @throws SSTException 
    * @throws
     */
	public void deleteSST_ZY_ZYQDCX(Connection conn, Map param)
			throws SSTException {
		try {
			String sql = "delete from SST_ZY_ZYQDCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00:";
			JdbcUtils.executeUpdate(conn, sql, param);
		} catch (Exception e) {
			errmsg = "删除中间表数据出错[查询代码：" + param.get("cxdm00") + "查询方式："
					+ param.get("cxfs00") + "]：";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}

	}
      /**
       * 
      * @Description: 调用存储过程获取住院清单信息缓存到中间表
      * @param conn
      * @param param 自助机发送给前置的soap报文参数
      * @return
      * @throws SSTException 
      * @throws
       */
	public Map callSpSstZyZyqd(Connection conn, Map param)
			throws SSTException {
		String zgjls0 = "";
		Vector<HashMap<String, String>> vt = null;
		try {
			long start = Long.parseLong(DateUtil.getNowTime());
			vt = new Vector<HashMap<String, String>>();
			ProcedureUtil pu = new ProcedureUtil();
			vt = pu.executeQuery(conn, "SP_SST_ZY_ZYQDCX", param);
			if (vt != null && vt.size() > 0) {
				Map map = new HashMap();
				map = vt.get(0);
				zgjls0 = (String) map.get("zgjls0");
			}
			long end = Long.parseLong(DateUtil.getNowTime());
			logger.info("SP_SST_ZY_ZYQDCX的本次运行时间为："
					+ DateUtil.consumeTime2String(start, end));

		} catch (Exception e) {
			errmsg = "获取SP_SST_ZY_ZYQDCX住院清单信息出错";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}
		return vt.get(0);
	}
   /**
    * 
   * @Description: 查询中间表获取住院清单信息
   * @param conn  
   * @param param 自助机发送给前置的soap报文参数
   * @return
   * @throws SSTException 
   * @throws
    */
	public Vector queryZyqd(Connection conn, Map param) throws SSTException {
		Vector vt = null;
//		String sql = "select * from SST_ZY_ZYQDCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00: order by sfrq00 desc";
		String sql = "select cxfs00,cxdm00,sfrq00,sfsj00,xmbh00,xmmc00,xmgg00,xmdw00,xmdj00,xmsl00,xmje00,zfbl00,zxks00 from" +
				" SST_ZY_ZYQDCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00: order by sfrq00 desc";
		vt = new Vector<HashMap<String, String>>();
		vt = JdbcUtils.executeQuery(conn, sql, param);
//		for(int i=0;i<vt.size();i++){
//			HashMap<String, String> map=vt.get(i);
//			map.put("xmdj00", Double.valueOf((String)map.get("xmdj00")).toString());
//			map.put("xmje00", Double.valueOf((String)map.get("xmje00")).toString());
//			map.put("zfbl00", Double.valueOf((String)map.get("zfbl00")).toString());
//			vt.add(i, map);
//		}
		return vt;
	}

	
	/**
	    * 
	   * @Description: 清除住院项目信息中间表的数据
	   * @param conn  
	   * @param param 自助机发送给前置的soap报文参数
	   * @return
	   * @throws SSTException 
	   * @throws
	    */
	public void deleteSST_ZY_ZYXMCX(Connection conn, Map param)throws SSTException {
		try {
			String sql = "delete from SST_ZY_ZYXMCX where zdbh00=:zdbh00:";
			JdbcUtils.executeUpdate(conn, sql, param);
		} catch (Exception e) {
			errmsg = "删除中间表数据出错";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}

	}
	/**
	    * 
	   * @Description: 调用存储过程获取住院项目信息缓存到中间表
	   * @param conn  
	   * @param param 自助机发送给前置的soap报文参数
	   * @return
	   * @throws SSTException 
	   * @throws
	    */
	public String callSpSstZyxm(Connection conn, Map param)throws SSTException {
		String zgjls0 = "";
		Vector<HashMap<String, String>> vt = null;
		try {
			long start = Long.parseLong(DateUtil.getNowTime());
			vt = new Vector<HashMap<String, String>>();
			ProcedureUtil pu = new ProcedureUtil();
			vt = pu.executeQuery(conn, "SP_SST_ZY_ZYXMCX", param);
			if (vt != null && vt.size() > 0) {
				Map map = new HashMap();
				map = vt.get(0);
				zgjls0 = (String) map.get("zgjls0");
			}
			long end = Long.parseLong(DateUtil.getNowTime());

		} catch (Exception e) {
			errmsg = "获取住院项目信息出错";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}
		return zgjls0;
	}
  
	/**
	    * 
	   * @Description: 查询中间表获取住院项目信息
	   * @param conn  
	   * @param param 自助机发送给前置的soap报文参数
	   * @return
	   * @throws SSTException 
	   * @throws
	    */
	public Vector queryZyxm(Connection conn, Map param) throws SSTException {
		Vector vt = null;
		String sql = "select xmbh00,xmmc00,xmlb00,xmgg00,xmdw00,xmdj00 from SST_ZY_ZYXMCX where zdbh00=:zdbh00:";
		vt = new Vector<HashMap<String, String>>();
		vt = JdbcUtils.executeQuery(conn, sql, param);
		return vt;
	}
	
	/**
	    * 
	   * @Description: 清除诊疗信息中间表的数据
	   * @param conn  
	   * @param param 自助机发送给前置的soap报文参数
	   * @return
	   * @throws SSTException 
	   * @throws
	    */

	public void deleteSST_ZY_ZLXXCX(Connection conn, Map param)throws SSTException {
		try {
			String sql = "delete from SST_ZY_ZLXXCX where cxfs00=:cxfs00: and cxdm00 =:cxdm00:";
			JdbcUtils.executeUpdate(conn, sql, param);
		} catch (Exception e) {
			errmsg = "删除中间表数据出错[查询代码：" + param.get("cxdm00") + "查询方式："+ param.get("cxfs00") + "]：";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}

	}

	/**
	    * 
	   * @Description: 调用存储过程获取诊疗信息缓存到中间表
	   * @param conn  
	   * @param param 自助机发送给前置的soap报文参数
	   * @return
	   * @throws SSTException 
	   * @throws
	    */
	public String callSpSstZyZlxx(Connection conn, Map param)throws SSTException {
		String zgjls0 = "";
		Vector<HashMap<String, String>> vt = null;
		try {
			long start = Long.parseLong(DateUtil.getNowTime());
			vt = new Vector<HashMap<String, String>>();
			ProcedureUtil pu = new ProcedureUtil();
			vt = pu.executeQuery(conn, "SP_SST_ZY_ZLXXCX", param);
			if (vt != null && vt.size() > 0) {
				Map map = new HashMap();
				map = vt.get(0);
				zgjls0 = (String) map.get("zgjls0");
			}
			long end = Long.parseLong(DateUtil.getNowTime());
		} catch (Exception e) {
			errmsg = "获取诊疗信息出错";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}
		return zgjls0;
	}
	/**
	    * 
	   * @Description: 查询中间表获取诊疗信息
	   * @param conn  
	   * @param param 自助机发送给前置的soap报文参数
	   * @return
	   * @throws SSTException 
	   * @throws
	    */
	public Vector queryZlxx(Connection conn, Map param) throws SSTException {
		Vector vt = null;
		String sql = "select * from SST_ZY_ZLXXCX where cxfs00=:cxfs00: and cxdm00=:cxdm00:";
		vt = new Vector<HashMap<String, String>>();
		vt = JdbcUtils.executeQuery(conn, sql, param);
		return vt;
	}
	/**
     * 
    * @Description: 删除住院清单汇总中间表缓存信息
    * @param conn
    * @param param 自助机发送给前置的soap报文参数
    * @return
    * @throws SSTException 
    * @throws
     */
	public void deleteSST_ZY_ZYQDHZ(Connection conn, Map param)throws SSTException {
		try {
			String sql = "delete from SST_ZY_ZYQDHZ where cxfs00 =:cxfs00: and cxdm00=:cxdm00:";
			JdbcUtils.executeUpdate(conn, sql, param);
		} catch (Exception e) {
			errmsg = "删除中间表数据出错[查询代码：" + param.get("cxdm00") + "查询方式："+ param.get("cxfs00") + "]：";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}

	}
      /**
       * 
      * @Description: 调用存储过程获取住院清单汇总信息缓存到中间表
      * @param conn
      * @param param 自助机发送给前置的soap报文参数
      * @return
      * @throws SSTException 
      * @throws
       */
	public Map callSpSstZyZyqdhz(Connection conn, Map param)throws SSTException {
		String zgjls0 = "";
		Vector<HashMap<String, String>> vt = null;
		try {
			long start = Long.parseLong(DateUtil.getNowTime());
			vt = new Vector<HashMap<String, String>>();
			ProcedureUtil pu = new ProcedureUtil();
			vt = pu.executeQuery(conn, "SP_SST_ZY_ZYQDHZ", param);
			if (vt != null && vt.size() > 0) {
				Map map = new HashMap();
				map = vt.get(0);
				zgjls0 = (String) map.get("zgjls0");
			}
			long end = Long.parseLong(DateUtil.getNowTime());
			logger.info("SP_SST_ZY_ZYQDHZ的本次运行时间为："+ DateUtil.consumeTime2String(start, end));
		} catch (Exception e) {
			errmsg = "获取住院清单汇总信息出错";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}
		return vt.get(0);
	}
   /**
    * 
   * @Description: 查询中间表获取住院清单汇总信息
   * @param conn  
   * @param param 自助机发送给前置的soap报文参数
   * @return
   * @throws SSTException 
   * @throws
    */
	public Vector queryZyqdhz(Connection conn, Map param) throws SSTException {
		Vector vt = null;
//		String sql = "select * from SST_ZY_ZYQDHZ where cxfs00 =:cxfs00: and cxdm00=:cxdm00:";
		String sql = "select cxfs00,cxdm00,xmbh00,xmmc00,xmgg00,xmdw00,xmdj00,xmsl00,xmje00,zfbl00 from" +
				" SST_ZY_ZYQDHZ where cxfs00 =:cxfs00: and cxdm00=:cxdm00:";
		vt = new Vector<HashMap<String, String>>();
		vt = JdbcUtils.executeQuery(conn, sql, param);
		return vt;
	}	
	/**
     * 
    * @Description: 删除住院预交金中间表缓存信息
    * @param conn
    * @param param 自助机发送给前置的soap报文参数
    * @return
    * @throws SSTException 
    * @throws
     */
	public void deleteSST_ZY_YJJCX(Connection conn, Map param)throws SSTException {
		try {
			String sql = "delete from SST_ZY_YJJCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00:";
			JdbcUtils.executeUpdate(conn, sql, param);
		} catch (Exception e) {
			errmsg = "删除中间表数据出错[查询代码：" + param.get("cxdm00") + "查询方式："+ param.get("cxfs00") + "]：";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}

	}
      /**
       * 
      * @Description: 调用存储过程获取住院预交金信息缓存到中间表
      * @param conn
      * @param param 自助机发送给前置的soap报文参数
      * @return
      * @throws SSTException 
      * @throws
       */
	public void callSpSstZyZyyjj(Connection conn, Map param)throws SSTException {
		Vector<HashMap<String, String>> vt = null;
		try {
			long start = Long.parseLong(DateUtil.getNowTime());
			vt = new Vector<HashMap<String, String>>();
			ProcedureUtil pu = new ProcedureUtil();
			vt = pu.executeQuery(conn, "SP_SST_ZY_YJJCX", param);
			if (vt != null && vt.size() > 0) {
				Map map = new HashMap();
				map = vt.get(0);
			}
			long end = Long.parseLong(DateUtil.getNowTime());
			logger.info("SP_SST_ZY_YJJCX的本次运行时间为："+ DateUtil.consumeTime2String(start, end));
		} catch (Exception e) {
			errmsg = "获取住院预交金信息出错";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}
	}
   /**
    * 
   * @Description: 查询中间表获取住院预交金信息
   * @param conn  
   * @param param 自助机发送给前置的soap报文参数
   * @return
   * @throws SSTException 
   * @throws
    */
	public Vector queryZyyjj(Connection conn, Map param) throws SSTException {
		Vector vt = null;
//		String sql = "select * from SST_ZY_YJJCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00: order by jyrq00,jysj00 desc";
		String sql = "select cxfs00,cxdm00,czlxmc,fyje00,jyrq00,jysj00 from" +
				" SST_ZY_YJJCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00: order by jyrq00,jysj00 desc";
		vt = new Vector<HashMap<String, String>>();
		vt = JdbcUtils.executeQuery(conn, sql, param);
		return vt;
	}
	
	 /**
	    * 
	   * @Description: 查询中间表获取住院预交金本次费用总额
	   * @param conn  
	   * @param param 自助机发送给前置的soap报文参数
	   * @return
	   * @throws SSTException 
	   * @throws
	    */
		public String getZyyjjbcfyze(Connection conn, Map param) throws SSTException {
//			Vector<HashMap<String, String>> vt = null;
			Map map=null;
//			String sql = "select * from SST_ZY_YJJCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00: order by jyrq00,jysj00 desc";
			String sql = "select sum(fyje00) bcfyze from" +
					" SST_ZY_YJJCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00:";
			map = JdbcUtils.get(conn, sql, param);
			if(map==null){
				logger.error("map为空");	
				return "null";
			}else{
			return map.get("bcfyze").toString();
			}
		}
	
	
	
	
	/**
     * 
    * @Description: 删除住院待扣费项目中间表缓存信息
    * @param conn
    * @param param 自助机发送给前置的soap报文参数
    * @return
    * @throws SSTException 
    * @throws
     */
	public void deleteSST_ZY_DKFXMCX(Connection conn, Map param)
			throws SSTException {
		try {
			String sql = "delete from SST_ZY_DKFXMCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00:";
			JdbcUtils.executeUpdate(conn, sql, param);
		} catch (Exception e) {
			errmsg = "删除中间表数据出错[查询代码：" + param.get("cxdm00") + "查询方式："
					+ param.get("cxfs00") + "]：";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}

	}
      /**
       * 
      * @Description: 调用存储过程获取住院待扣费项目信息缓存到中间表
      * @param conn
      * @param param 自助机发送给前置的soap报文参数
      * @return
      * @throws SSTException 
      * @throws
       */
	public String callSpSstZydkfxm(Connection conn, Map param)
			throws SSTException {
		Vector<HashMap<String, String>> vt = null;
		try {
			long start = Long.parseLong(DateUtil.getNowTime());
			vt = new Vector<HashMap<String, String>>();
			ProcedureUtil pu = new ProcedureUtil();
			vt = pu.executeQuery(conn, "SP_SST_ZY_DKFXMCX", param);
			if (vt != null && vt.size() > 0) {
				Map map = new HashMap();
				map = vt.get(0);
			}
			long end = Long.parseLong(DateUtil.getNowTime());
			logger.info("SP_SST_ZY_DKFXMCX的本次运行时间为："
					+ DateUtil.consumeTime2String(start, end));

		} catch (Exception e) {
			errmsg = "获取住院待扣费项目信息出错";
			logger.error(errmsg + e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		}
		return vt.get(0).get("bcfyze");
	}
   /**
    * 
   * @Description: 查询中间表获取住院待扣费项目信息
   * @param conn  
   * @param param 自助机发送给前置的soap报文参数
   * @return
   * @throws SSTException 
   * @throws
    */
	public Vector queryZydkfxm(Connection conn, Map param) throws SSTException {
		Vector vt = null;
		String sql = "select cxfs00,cxdm00,zxks00,xmmc00,xmdw00,xmdj00,xmsl00,xmje00,kdrq00 from" +
				" SST_ZY_DKFXMCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00: order by kdrq00 desc";
		vt = new Vector<HashMap<String, String>>();
		vt = JdbcUtils.executeQuery(conn, sql, param);
		return vt;
	}
	
	public String getZydkfxmze(Connection conn, Map param) throws SSTException {
		String sql = "select sum(xmje00) as xmjeze from  SST_ZY_DKFXMCX where cxfs00 =:cxfs00: and cxdm00=:cxdm00:";
		Map map=JdbcUtils.get(conn, sql, param);
		String xmze00=map.get("xmjeze").toString();	
		return xmze00;
	}
	
	
	
	
	
	/**
	 * 
	 * @Description: 获取住院清单打印信息
	 * @param conn
	 * @param param
	 * @return
	 * @throws SSTException 
	 * @throws SSTException:
	 */
	public Vector getZyqddyxx(Connection conn,Map param) throws SSTException{
		Vector vt = new Vector();
		String sql = "select * from SST_ZYQDDY_DYXX where zyh000=:zyh000: and cqrq00=:cqrq00: and fyze00=:fyze00: and qdjls0=:qdjls0:";
		logger.info("查询住院清单是否已打印 SQL: " + sql);
		return JdbcUtils.executeQuery(conn, sql, param);	
	}
	
	/**
	 * 
	 * @Description: 住院清单打印初始化（往住院清单打印信息表中插入打印数据）
	 * @param conn
	 * @param param
	 * @throws SSTException:
	 */
	public void insertZyqddyInit(Connection conn,Map param) throws SSTException{
		Vector vt = this.getZyqddyxx(conn, param);
		String sql = "insert into SST_ZYQDDY_DYXX(zyh000,cqrq00,fyze00,qdjls0,begnum,endnum,dyzt00) values(:zyh000:,:cqrq00:,:fyze00:,:qdjls0:,:ksxh00:,:jsxh00:,:dyzt00:)";
		logger.info("往住院清单打印信息表中插入打印数据 SQL: " + sql + param);
		try {
			if(!(vt.size() > 0)){
			JdbcUtils.executeUpdate(conn, sql, param);
			}
		} catch (SSTException e) {
			logger.error("住院清单打印信息记录失败[住院号：" + param.get("zyh000") + "]：" + e.getMessage());
			throw new SSTException("住院清单打印信息记录失败[卡号：" + param.get("zyh000") + "]：" + e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @Description: 删除已打印清单详细数据
	 * @param conn
	 * @param param:
	 * @throws SSTException 
	 */
	public void deleteZyqddyxx(Connection conn,Map param) throws SSTException{
		String sql = "delete SST_ZYQDDY_DYXX where zyh000=:zyh000: and cqrq00=:cqrq00: and fyze00=:fyze00: and qdjls0=:qdjls0:";
		logger.info("删除已打印清单详细数据SQL: " + sql);
		try {
			JdbcUtils.executeUpdate(conn, sql, param);
		} catch (SSTException e) {
			logger.error("删除已打印清单详细数据失败[住院号：" + param.get("zyh000") + "]：" + e.getMessage());
			throw new SSTException("删除已打印清单详细数据失败[住院号：" + param.get("zyh000") + "]：" + e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @Description:插入住院清单打印汇总信息
	 * @param conn
	 * @param param:
	 * @throws SSTException 
	 */
	public void insertZyqddyAll(Connection conn,Map param) throws SSTException{
		//String sql = "insert into SST_ZYQDDY_DYXX(zyh000,cqrq00,fyze00,qdjls0,dymb00,begnum,endnum,dyzt00,rqdrq0)"
		//				+" values(:zyh000:,:cqrq00:,:fyze00:,:qdjls0:,:dymb00:,:ksxh00:,:jsxh00:,:dyzt00:,:ksrq00:)";
		//logger.info("插入住院清单打印汇总信息SQL: " + sql);
		
		//param.put("dymb00", "FJSL_ZYFYQD");
		//param.put("dymb00", "ZYHZQD");
		//param.put("ksxh00", "0");
		//param.put("jsxh00", "0");
		//param.put("dyzt00", "1");
		String sql = "insert into SST_ZYQDDY_DYXX(zyh000,rqdrq0,dysj00,zdbh00)values(:zyhao0:,:rqdrq0:,:dysj00:,:zdbh0:)";
		try {
			JdbcUtils.executeUpdate(conn, sql, param);
		} catch (SSTException e) {
			logger.error("插入住院清单打印汇总信息失败[住院号：" + param.get("zyh000") + "]：" + e.getMessage());
			throw new SSTException(
					"插入住院清单打印汇总信息失败[卡号：" + param.get("zyh000") + "]：" + e.getMessage(), e);
		}
	}
	
	
	/**
	 * 查询汇日清单是否已经打印过
	 * @param conn
	 * @param param
	 * @return
	 * @throws FrameException
	 */
	public HashMap getMzqddyxxByZyh(Connection conn,Map param) throws SSTException{
		HashMap hm = new HashMap();
		String sql = "select rqdrq0 from SST_ZYQDDY_DYXX where zyh000=:zyhao0:";
		Vector vt = JdbcUtils.executeQuery(conn, sql, param);	
		for(int i=0;i<vt.size();i++){
			hm.put(((Map)vt.get(i)).get("rqdrq0").toString() , "1");
		}
		return hm;
	}
	
	
	/**
	 * 查询汇总清单是否已经打印过
	 * @param conn
	 * @param param
	 * @return
	 * @throws FrameException
	 */
	public HashMap<String, String> queryHzqdByZyh(Connection conn,Map param) throws SSTException{
		HashMap<String, String> hm = new HashMap<String, String>();
		String sql = "select rqdrq0,dysj00 from SST_ZYQDDY_DYXX where zyh000=:zyhao0: and rqdrq0=:rqdrq0:";
		Vector vt = JdbcUtils.executeQuery(conn, sql, param);	
		for(int i=0;i<vt.size();i++){
			hm.put("dysj00" , ((Map)vt.get(i)).get("dysj00").toString());
		}
		return hm;
	}
	
	 /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws Exception    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
      
/** 
*字符串的日期格式的计算 
*/  
    public static int daysBetween(String smdate,String bdate) throws Exception{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  
	
	
    public static void main(String[] args) {
    	try {
			System.out.println(daysBetween("2012-09-08","2012-09-15"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
}
