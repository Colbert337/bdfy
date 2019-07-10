package com.ylzinfo.model.his.mz.bgddy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallLisService;

/**
 * 
* @Project: m_his_mz_bgddy
* @ClassName: UpdateFlagAfterPrintService 
* @Description: 更新报告单状态为已打印业务层
* @author huangjj
* @date 2015-10-21 上午10:44:11 
*
 */
public class UpdateFlagAfterPrintService {
	
	public Logger logger = null;
	
	public UpdateFlagAfterPrintService(){
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 
	* @Description:上传报告单状态为已打印
	* @param param
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String updateFlagAfterPrint(Map param) throws SSTException{
		String errMsg = "更新报告单状态为已打印";
		try{
			logger.info("************"+errMsg+"开始 ************");
			long startTime = System.currentTimeMillis();
			logger.info(errMsg+"开始时间：" + startTime);
			
			CallLisService ws = new CallLisService();
			ws.EditPrinted(param);
			
			long endTime = System.currentTimeMillis();
			logger.info(errMsg+"结束时间：" + endTime);
			logger.info("************"+errMsg+"结束************");
			
			Vector vt = new Vector();
			HashMap hm = new HashMap();
			hm.put("mess00", "更新报告单打印状态为已打印成功");
			vt.add(hm);
			return SoapUtil.getSoapResponse(vt);
		}catch(Exception e){
			logger.error("更新报告单状态为已打印失败:"+e.getMessage());
			throw new SSTException("更新报告单状态为已打印失败:"+e.getMessage());
		}
	}
}