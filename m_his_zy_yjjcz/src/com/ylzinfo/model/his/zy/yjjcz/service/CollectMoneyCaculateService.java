package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.ylzinfo.matrix.model.his.zy.yjjcz.CollectMoneyCaculateModelMatrix;
import com.ylzinfo.matrix.model.his.zy.yjjcz.dao.ScsqsjDao;

public class CollectMoneyCaculateService extends CollectMoneyCaculateModelMatrix {
	
	private Logger log = null;
	
	public CollectMoneyCaculateService() {
		log = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public  Vector collectMoneyCaculate(Map param) throws SSTException{
		Connection conn = null;
		Map sqmx = new HashMap();
		String zdbh00 = "";// 终端编号
		String zdmc00 = "";// 终端名称
		String scsqsj = "";// 上次收钱时间
		String bcsqsj = "";// 本次收钱时间
		String cgjybs = "0";// 汇总成功交易笔数
		String cgjyje = "0";// 汇总成功交易金额
		String hzsbbs = "0";// 汇总失败交易笔数
		String hzsbje = "0";// 汇总失败交易金额
		String dbsbbs = "0";// 单笔失败笔数
		String dbsbje = "0";// 单笔失败金额
		String dbzybs = "0";// 单笔争议笔数
		String dbzyje = "0";// 单笔争议金额
		String message = "";
		try{
			conn = DBUtil.getConnection();
			ScsqsjDao dao = new ScsqsjDao();
			Vector vt = dao.collectMoney(conn, param);
			if(vt==null||vt.size()<=0){
				throw new SSTException("未找到收钱统计记录");
			}
			sqmx = (Map)vt.get(0);
			scsqsj = (String)sqmx.get("scsqsj");
			bcsqsj = (String)sqmx.get("bcsqsj");
			zdbh00 = (String)sqmx.get("zdbh00");
			zdmc00 = (String)sqmx.get("zdmc00");
			cgjybs = (String)sqmx.get("cgjybs");
			cgjyje = (String)sqmx.get("cgjyje");
			hzsbbs = (String)sqmx.get("hzsbbs");
			hzsbje = (String)sqmx.get("hzsbje");
			dbsbbs = (String)sqmx.get("dbsbbs");
			dbsbje = (String)sqmx.get("dbsbje");
			dbzybs = (String)sqmx.get("dbzybs");
			dbzyje = (String)sqmx.get("dbzyje");
			
			message = "\\n\n终端编号：" + zdbh00 + "\\n\n" + "终端名称：" + zdmc00 + "\\n\n"
			+ "上次收钱时间：" + scsqsj + "\\n\n" + "本次收钱时间：" + bcsqsj + "\\n\n" 
			+ "成功交易笔数：" + cgjybs + "\\n\n"+ "成功交易金额：" + cgjyje + "\\n\n"
			+ "汇总待冲正笔数：" + hzsbbs + "\\n\n"+ "汇总待冲正金额：" + hzsbje + "\\n\n" 
			+ "单笔待冲正笔数：" + dbsbbs + "\\n\n"+ "单笔待冲正金额：" + dbsbje + "\\n\n" 
			+ "单笔有争议笔数：" + dbzybs + "\\n\n"+ "单笔有争议金额：" + dbzyje + "\\n\n\\n\n\\n\n\\n\n";
			try{
				Map map = new HashMap();
				map.put("zdbh00", zdbh00);
				map.put("scsqsj", scsqsj);
				map.put("bcsqsj", bcsqsj);
				map.put("message", message);
				dao.doSqtjLog(conn, map);
			}catch(Exception e){
				log.info("记录收钱统计日志失败！");
			}
			
			Vector evtBody = new Vector();
			Map mp = new HashMap();
			mp.put("message", message);
			evtBody.add(mp);
			return evtBody;
		}catch (Exception e) {
			log.error("本次收钱统计失败:"+e.getMessage());
			throw new SSTException("本次收钱统计失败:"+e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
