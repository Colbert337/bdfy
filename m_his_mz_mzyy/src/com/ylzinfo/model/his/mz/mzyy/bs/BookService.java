package com.ylzinfo.model.his.mz.mzyy.bs;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.ylzinfo.matrix.model.his.mz.yygh.BookModelMatrix;
import com.ylzinfo.model.his.mz.mzyy.dao.MzyyDao;
import com.ylzinfo.model.his.mz.mzyy.util.StringUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @describe :ԤԼ�Һ�ȷ��
 * @classname:BookService
 * @author   :Lan
 * @date     :2018-3-31
 */
public class BookService extends BookModelMatrix{
    
	private Logger logger = null;
	public BookService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * ԤԼ�Һ�ȷ��
	 * @param para
	 * @return
	 * @throws SSTException
	 */
	@Override
	public Vector book(Map para) throws SSTException {
		Connection conn = null;
		String funcTitle = "[ԤԼ�Һ�ȷ��]";
		String className = this.getClass().getName();
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+funcTitle+"��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(funcTitle+"��ʼʱ�䣺" + startTime);
			
			conn = DBUtil.getConnection();
			
//			para.put("ly0000", "������");
//			
//			para.put("lymx00", "7");
//			
//			para.put("cfzbz0", "1");
//			
//			para.put("jysj00", DateUtil.getSystemDateTime());
			
			para.put("jylsh0", DateUtil.getNowTime());
			para.put("brfb00", "1");
			para.put("type00", "1");
			
			String ysbh00 = (String)para.get("ysbh00");
			if(ysbh00.equals("None")) {
				para.put("ysbh00", "");
				para.put("ysxm00", "");
			}
			
			CallHisService ws = new CallHisService("022");
			Map map = ws.callWs(para);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "δ֪����";
				}
				throw new SSTException(errmsg);
			}
			vt.add(map);
			
			MzyyDao dao = new MzyyDao();
			dao.insertSstLogYyghForMzyy(conn, para);
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle+"����ʱ�䣺" + endTime);
			logger.info("************"+funcTitle+"����************");
			logger.info(funcTitle+"���ʱ:"+ StringUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (Exception pe) {
			pe.printStackTrace();
			logger.error(funcTitle+"����,ԭ��"+ pe.getMessage());
			throw new SSTException(funcTitle+"����,ԭ��"+ pe.getMessage(), pe);
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SSTException e) {
				e.printStackTrace();
			}
		}
	}
}
