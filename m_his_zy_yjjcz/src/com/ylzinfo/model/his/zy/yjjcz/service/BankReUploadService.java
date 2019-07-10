package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.SoapUtil;
import com.start.sst.util.StringUtil;
import com.ylzinfo.model.his.zy.yjjcz.dao.YjjczDao;

public class BankReUploadService {

private Logger logger=null;
	
	public BankReUploadService() {
		logger=Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 银行卡充值重上传，包括银行卡窜号充值重上传
	 * @param param
	 * @return
	 * @throws SSTException
	 */
	public String doBankReUpload(Map param)throws SSTException{
		Connection conn=null;
		Vector<HashMap<String, String>> vt = null;
		String zdbh00=StringUtil.Null2BlankAndTrim(param.get("zdbh00"));
		String cxdm00=StringUtil.Null2BlankAndTrim(param.get("cxdm00"));
		YjjczDao dao = new YjjczDao();
		String logMsg = "终端编号：" + zdbh00 + " 卡号：" + cxdm00 + "--";
		try{
			logger.info(logMsg+"1.更新银行充值信息到数据库中，并把状态写为待冲正状态...");			
			conn=DBUtil.getConnection();
			dao.updateJyztToHisFail(conn, param);
			vt=new Vector<HashMap<String, String>>();
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("OK", "交易成功！");
			vt.add(hm);
			logger.info(logMsg + "***************doBankReUpload预交金银行卡充值信息重上传结束*************");
			return SoapUtil.getSoapResponse(vt);
		}catch(Exception e){
			logger.info("银行信息重上传失败:"+e.getMessage());
			throw new SSTException(e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
