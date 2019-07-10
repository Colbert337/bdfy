package com.ylzinfo.model.his.zy.jbxx.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.jbxx.util.MyCardUtil;
/**
 * 
* @Project: m_his_zy_jbxx
* @ClassName: GetCardNoService 
* @Description: 解密卡号业务层
* @author huangjj
* @date 2015-4-3 下午2:16:30 
*
 */
public class GetCardNoService {
	private Logger logger = null;
	
	public GetCardNoService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	public String readCardNo(String encodeCardNo,String zdbh00) throws SSTException{
		String return_cardno = "";
		try {
			for(int i = 0; i<21; i++)
			{
				return_cardno =  MyCardUtil.ICReadCardNo(encodeCardNo); 
				logger.info("解密返回终端ID："+return_cardno.substring(1, 13).trim());
				logger.info("前台传入终端ID："+zdbh00);
				if(return_cardno.substring(1, 13).trim().equals(zdbh00.trim()))
				{
					logger.info("终端ID相符,"+"循环次数："+i);
					break;
				}				
			}  
			Vector vt = new Vector();
			Map param = new HashMap();
			param.put("cxdm00", return_cardno.substring(13));
			param.put("zdbh00", return_cardno.substring(1,13));
			vt.add(param);
			return SoapUtil.getSoapResponse(vt);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw new SSTException("获取卡号失败");
		}
	}
}
