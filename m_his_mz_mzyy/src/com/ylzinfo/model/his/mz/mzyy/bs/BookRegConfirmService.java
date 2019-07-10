package com.ylzinfo.model.his.mz.mzyy.bs;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.ylzinfo.matrix.model.his.mz.yygh.BookRegConfirmModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.dao.MzyyDao;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @describe :预约取号确认
 * @classname:BookRegConfirmService
 * @author   :Lan
 * @date     :2018-3-31
 */
public class BookRegConfirmService extends BookRegConfirmModelMatrix{
	
	private Logger logger = null;
	public BookRegConfirmService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 预约取号确认
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public Vector bookRegConfirm(Map para) throws SSTException {
		Connection conn = null;
		String funcTitle = "[预约取号确认]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"开始 ************para="+para);
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"开始时间：" + startTime);
			
			conn = DBUtil.getConnection();
			
			para.put("jysj00", DateUtil.getSystemDateTime());
			
			MzyyDao dao = new MzyyDao();
			dao.insertSstLogYyghForYyqh(conn, para);
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle+"结束时间：" + endTime);
			logger.info("************"+funcTitle+"结束************");
			logger.info(funcTitle+"额耗时:"+ StringUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception pe) {
			pe.printStackTrace();
			logger.error(funcTitle+"出错,原因："+ pe.getMessage());
			throw new SSTException(funcTitle+"出错,原因："+ pe.getMessage(), pe);
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SSTException e) {
				e.printStackTrace();
			}
		}
	}
}
