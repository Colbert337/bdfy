package com.ylzinfo.model.his.mz.ybjk.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ybjk.dao.YbjkDao;

public class UpdateKC22ckc125Service {
	
	private Logger log=null;
	
	public UpdateKC22ckc125Service() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public String UpdateKC22ckc125(Map para) throws SSTException {
		Connection conn = null;
		YbjkDao dao = new YbjkDao();
		HashMap<String, String> hm = new HashMap<String, String>();
		try {
			conn = DBUtil.getConnection();
			log.info("����KC22���ϱ�־��ʼ...");
			long startTime = System.currentTimeMillis();
			log.info("��ȡҽ���������ݿ�ʼʱ�䣺" + startTime);
			
			dao.updateKc22(conn, para);
			
			long endTime = System.currentTimeMillis();
			log.info("����KC22���ϱ�־����...");
			log.info("����KC22���ϱ�־��ʱ:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			hm.put("result", "1");
			return SoapUtil.getSoapResponse(hm);
		}catch (Exception e) {
			log.info("����KC22���ϱ�־����:" + e.getMessage());
			throw new SSTException("����KC22���ϱ�־����:" + e.getMessage());
		}finally{
			log.info("�رձ������ݿ�����");
			DBUtil.closeConnection(conn);
		}
	}
}
