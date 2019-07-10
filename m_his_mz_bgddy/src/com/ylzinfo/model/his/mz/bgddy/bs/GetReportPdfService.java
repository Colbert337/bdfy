package com.ylzinfo.model.his.mz.bgddy.bs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.BundleConfigUtil;
import com.start.sst.util.Base64Util;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.bgddy.util.ReportUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;
import com.ylzinfo.service.his.mz.ws.webservice.CallLisService;

/**
 * 
* @Project: m_his_mz_bgddy
* @ClassName: GetReportPdfService 
* @Description: 获取报告单打印业务层
* @author huangjj
* @date 2015-10-20 上午10:55:31 
*
 */
public class GetReportPdfService {
	
	private Logger logger = null;

	public GetReportPdfService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * 
	* @Description:获取报告单打印pdf
	* @param param
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String getReportPdf(Map param)throws SSTException{
		String errMsg = "获取报告单打印pdf";
		try{
			logger.info("************"+errMsg+"开始 ************");
			long startTime = System.currentTimeMillis();
			logger.info(errMsg+"开始时间：" + startTime);
			
			String pdfurl = BundleConfigUtil.getConstantValue("m_his_mz_bgddy", "pdfurl");
			param.put("pdfurl", pdfurl);
			
			CallLisService ws = new CallLisService();
			String pdfstr = ws.getPdf(param);
			
			long endTime = System.currentTimeMillis();
			logger.info(errMsg+"结束时间：" + endTime);
			logger.info("************"+errMsg+"结束************");
			
			Vector<HashMap<String, String>> vt = new Vector<HashMap<String, String>>();
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("pdfstr", pdfstr);
			vt.add(hm);
			
			return SoapUtil.getSoapResponse(vt);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("获取报告单pdf失败"+e.getMessage());
			throw new SSTException("获取报告单pdf失败"+e.getMessage());
		}
	}
	
	private String createPdf(Vector<HashMap<String,String>> vt, Map hm) throws Exception {
		String pdfstr = "";
		try {
			logger.info("***************createPdf生成PDF开始***************");
			
			String bgdlsh = (String)hm.get("bgdlsh");
			
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("xming0", hm.get("xming0"));
			paraMap.put("zybl00", hm.get("zybl00"));
			paraMap.put("bch000", hm.get("bch000"));
			paraMap.put("jyxmmc", hm.get("jyxmmc"));
			paraMap.put("kbmc00", hm.get("kbmc00"));
			paraMap.put("xbie00", hm.get("xbie00"));
			paraMap.put("sjyb00", hm.get("sjyb00"));
			paraMap.put("ysmc00", hm.get("ysmc00"));
			paraMap.put("lsh000", hm.get("lsh000"));
			paraMap.put("brnl00", hm.get("brnl00"));
			paraMap.put("jssj00", hm.get("jssj00"));
			paraMap.put("cysj00", hm.get("cysj00"));
			paraMap.put("bz0000", hm.get("bz0000"));
			paraMap.put("ybh000", hm.get("ybh000"));
			paraMap.put("bgsj00", hm.get("bgsj00"));
			paraMap.put("jysj00", hm.get("jysj00"));
			paraMap.put("jyzmc0", hm.get("jyzmc0"));
			paraMap.put("hdzmc0", hm.get("hdzmc0"));
			
			List<String[]> prtList  =  new ArrayList<String[]>();
			for(int i=0;i<vt.size();i++) {
				HashMap<String,String> data = vt.get(i);
				String[] tt = new String[5];
				tt[0] = String.valueOf(i+1);
				tt[1] = data.get("xmmc00");
				tt[2] = data.get("xmjg00");
				tt[3] = data.get("xmdw00");
				tt[4] = data.get("ckz000");
				prtList.add(tt);
			}
			
			String[] prtNames = new String[] { "id", "xmmc00", "xmjg00", "xmdw00", "ckz000" };
			
			logger.info("开始生成PDF...   " + System.currentTimeMillis());
			
			String pdfurl = BundleConfigUtil.getConstantValue("m_his_mz_bgddy", "pdfurl");
			
			String temurl = "";
			if(prtList.size() <= 20) {
				temurl = pdfurl + "PDFTemplate.jasper";
			} else {
				temurl = pdfurl + "PDFTemplate2.jasper";
			}
			
			String pdfname = pdfurl + bgdlsh + ".pdf";
			
			ReportUtil util = new ReportUtil();
			util.createPdf(prtList, prtNames, paraMap, temurl, pdfname);
			
			logger.info("生成PDF结束...   " + System.currentTimeMillis());
			
			pdfstr = Base64Util.encodeBase64File(pdfname);
			pdfstr.replace("\n", "");
			pdfstr.replace("\r", "");
			
			//File file = new File(pdfname);
			//file.delete();
			
			logger.info("***************createPdf生成PDF结束***************");
			
			return pdfstr;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSTException("PDF生成失败", e);
		}
	}
}