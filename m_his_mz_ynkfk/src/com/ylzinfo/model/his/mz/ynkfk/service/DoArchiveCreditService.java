package com.ylzinfo.model.his.mz.ynkfk.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.MapUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ynkfk.dao.ZzfkDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @description: 建档
 * @author wujf
 * @date: 2015-9-9 上午09:55:54
 */
public class DoArchiveCreditService {
	
	private Logger logger = null;
	
	private String errmsg="";
	
	public DoArchiveCreditService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainDoArchiveCredit(Map params) throws  SSTException{
		Connection conn = null;
		ZzfkDao dao = new ZzfkDao();
		String jdlsh0 = (String)params.get("jdlsh0");
		String jylsh0 = (String)params.get("jylsh0");
		String dblsh0 = (String)params.get("dblsh0");
		Map map = null;
		try {
			logger.info("***************建档确认开始[建档流水号:"+jdlsh0+"]***************");
			
			conn = DBUtil.getConnection();
			
			logger.info("获取建档初始化信息（建档流水号：" + jdlsh0 + "）...");
			Map cshxx = dao.getJdcshxx(conn, params);
			
			logger.info("更新单笔充值记录状态为吸币成功");
			dao.updateSstYjjDbczmx(conn, dblsh0);
			logger.info("单笔金额累加到汇总中...");
			dao.updateSstYjjZhczmx(conn,jylsh0);
			
			String jdzt00 = (String)MapUtil.getValue(cshxx, "jdzt00", "建档状态", false);//建档状态
			if (ZzfkDao.ALLSUCCESS.equals(jdzt00)) {// 流程完成
				throw new SSTException("建档成功，无需继续建档！");
			} else if (jdzt00.equals(ZzfkDao.TKCGYCSB)) { // 吐卡成功，压钞失败
				throw new SSTException("钱币已经吐出，建档失败，无需继续建档！");
			} else {// 建档初始化
				try {
					logger.info("进行HIS建档（建档流水号：" +  jdlsh0 + "）...");
					
					cshxx.put("cxfs00", "1");
					
					String xbie00 = (String)cshxx.get("xbie00");
					if(xbie00.equals("1")) {
						cshxx.put("xbie00", "M");
					} else {
						cshxx.put("xbie00", "F");
					}
					
					String csrq00 = (String)cshxx.get("csrq00");
					cshxx.put("csrq00", csrq00.substring(0, 4) + "-" + csrq00.substring(4, 6) + "-" + csrq00.substring(6, 8));
					
					cshxx.put("zy0000","");
					
					cshxx.put("hyzt00","");
					
					cshxx.put("czlx00","CA");
					
					cshxx.put("type00","1");
					cshxx.put("minzu0", (String)params.get("mzubm0"));
					
					CallHisService ws = new CallHisService("002");
					map = ws.callWs(cshxx);
					String result = (String)map.get("result");
					if(!result.equals("1")) {
						String errmsg = (String)map.get("errmsg");
						if(errmsg == null || errmsg.equals("")) {
							errmsg = "未知错误";
						}
						throw new SSTException(errmsg);
					}
				} catch (Exception e) {
					logger.info("HIS建档失败，修改建档状态为：充值成功，HIS建档失败");
					dao.updateCzztbz(conn, jylsh0, "1");
					dao.updateJdcshJdzt(conn, jdlsh0,ZzfkDao.CZCGHISZCSB);
					throw new SSTException(e.getMessage());
				}
				logger.info("预交金充值成功，HIS建档成功，修改预交金充值状态：充值成功，HIS更新成功");
				String cgjysj = DateUtil.getSystemDateTime();
				dao.updateCzztbzToSuccess(conn, jylsh0, jylsh0, cgjysj, (String)map.get("cxdm00"));
				logger.info("HIS建档成功，修改建档状态");
				dao.updateJdcshJdzt(conn, jdlsh0, ZzfkDao.HISSUCCESS);
			}
			//提交本地事务
			conn.commit();
			logger.info("更新建档初始化状态状态为完成...");
			String now = DateUtil.getSystemDateTime();
			dao.finishJdcsh(conn, jdlsh0, now, (String)map.get("cxdm00"));
			
			logger.info("返回个人基本信息（封装数据）...");
			Vector<HashMap<String, String>> vt = new Vector<HashMap<String, String>>();
			HashMap<String, String> rtn = new HashMap<String, String>();
			rtn.put("yjjye0", (String)map.get("yjjye0"));
			rtn.put("jdsj00", now);
			rtn.put("gbf000", "0");
			rtn.put("xtgzh0", "");
			rtn.put("brid00", (String)map.get("brid00"));
			rtn.put("cxdm00", (String)map.get("cxdm00"));
			vt.addElement(rtn);
			// 封装返回信息
			logger.info("***************建档确认结束[建档流水号:"+jdlsh0+"]***************");
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception e) {
			e.printStackTrace();
			errmsg = "发卡机建档失败：";
			logger.error(errmsg+e.getMessage());
			throw new SSTException(errmsg + e.getMessage());
		} finally {
			logger.info("关闭本地数据库连接");
			DBUtil.closeConnection(conn);
		}
	}
}
