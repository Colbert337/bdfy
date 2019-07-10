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
* @Description: ���±��浥״̬Ϊ�Ѵ�ӡҵ���
* @author huangjj
* @date 2015-10-21 ����10:44:11 
*
 */
public class UpdateFlagAfterPrintService {
	
	public Logger logger = null;
	
	public UpdateFlagAfterPrintService(){
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 
	* @Description:�ϴ����浥״̬Ϊ�Ѵ�ӡ
	* @param param
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String updateFlagAfterPrint(Map param) throws SSTException{
		String errMsg = "���±��浥״̬Ϊ�Ѵ�ӡ";
		try{
			logger.info("************"+errMsg+"��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(errMsg+"��ʼʱ�䣺" + startTime);
			
			CallLisService ws = new CallLisService();
			ws.EditPrinted(param);
			
			long endTime = System.currentTimeMillis();
			logger.info(errMsg+"����ʱ�䣺" + endTime);
			logger.info("************"+errMsg+"����************");
			
			Vector vt = new Vector();
			HashMap hm = new HashMap();
			hm.put("mess00", "���±��浥��ӡ״̬Ϊ�Ѵ�ӡ�ɹ�");
			vt.add(hm);
			return SoapUtil.getSoapResponse(vt);
		}catch(Exception e){
			logger.error("���±��浥״̬Ϊ�Ѵ�ӡʧ��:"+e.getMessage());
			throw new SSTException("���±��浥״̬Ϊ�Ѵ�ӡʧ��:"+e.getMessage());
		}
	}
}