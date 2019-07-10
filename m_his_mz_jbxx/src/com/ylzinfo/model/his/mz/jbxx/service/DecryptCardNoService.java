/**
 * 
 */
package com.ylzinfo.model.his.mz.jbxx.service;

import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.SoapUtil;
import com.start.sst.util.StringUtil;
import com.ylzinfo.his.service.CardService;

/**
 * @description: Ω‚√‹ø®∫≈
 * @author wujf
 * @date: 2015-12-29 …œŒÁ10:45:55
 */
public class DecryptCardNoService {
	
	private Logger logger = null;
	
	public DecryptCardNoService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainDecryptCardNo(Map params) throws SSTException{
		String zdbh00 = StringUtil.Null2BlankAndTrim(params.get("zdbh00"));
		String encodeCardNo = StringUtil.Null2BlankAndTrim(params.get("cxdm00"));
		CardService cardService = new CardService();
		Map cardInfo = cardService.decryptCardNo(zdbh00, encodeCardNo);
		Vector vt = new Vector();
		vt.add(cardInfo);
		return SoapUtil.getSoapResponse(vt);
		
	}

}
