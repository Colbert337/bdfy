package com.ylzinfo.service.his.mz.ws.webservice;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import basesst.SstBaseBundleUtil;

import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.BundleUtil;
import com.start.sst.util.Base64Util;

public class CallLisService {
	private static Logger log;
	private PrintServiceSoap soap = null;
	private static final String pictureurl = "http://10.10.100.14:8080/JPG/";
	
	public CallLisService() throws SSTException {
		log = Logger.getLogger(this.getClass().getName());
		try {
			BundleContext bundleContext = SstBaseBundleUtil.getBundleContext();
			Bundle bundle = BundleUtil.getBundle(bundleContext, "s_his_mz_ws");
			Class clientClazz = bundle.loadClass(CallLisService.class.getName());
			Thread.currentThread().setContextClassLoader(clientClazz.getClassLoader());
			PrintService service = new PrintService();
			soap = service.getPrintServiceSoap();
		} catch (Exception e) {
			log.error("��ʼ��webservices���ӳ���" + e.getMessage());
			throw new SSTException("��ʼ��webservices���ӳ���" + e.getMessage());
		}
	}
	
	public Vector getTestForm(Map para) throws SSTException {
		Vector<HashMap<String,String>> vt = new Vector<HashMap<String,String>>();
		try {
			String brid00 = (String)para.get("brid00");
			String brlx00 = (String)para.get("brlx00");
			String qssj00 = (String)para.get("qssj00");
			String jzzj00 = (String)para.get("jzzj00");
			log.info("������:"+brid00+" �������ͣ�"+brlx00+" ��ʼʱ�䣺"+qssj00+" ��ֹʱ�䣺"+jzzj00);
			
			ArrayOfString array = soap.getTestForm(brid00, brlx00, qssj00, jzzj00);
			List<String> list = array.getString();
			for(int i=0;i<list.size();i++) {
				String row = list.get(i);
				String temp[] = row.split(",");
				String str = temp[13];
				if(str.substring(3,4).equals("1")) {
					continue;
				}
				HashMap<String,String> hm = new HashMap<String,String>();
				String bgzt00 = temp[0];
				hm.put("bgzt00", bgzt00);
				if(bgzt00.equals("0")) {
					hm.put("bgztmc", "����");
					hm.put("sfkdy0", "���ɴ�ӡ");
				} else if(bgzt00.equals("2")) {
					hm.put("bgztmc", "����");
					hm.put("sfkdy0", "���ɴ�ӡ");
				} else if(bgzt00.equals("5")) {
					hm.put("bgztmc", "���");
					hm.put("sfkdy0", "�ɴ�ӡ");
				} else if(bgzt00.equals("9")) {
					hm.put("bgztmc", "����");
					hm.put("sfkdy0", "���ɴ�ӡ");
				} else {
					hm.put("bgztmc", "δ֪");
					hm.put("sfkdy0", "���ɴ�ӡ");
				}
				hm.put("bgdlsh", temp[1]);
				hm.put("yqdm00", temp[2]);
				hm.put("ybh000", temp[3]);
				hm.put("bgrq00", temp[4]);
				hm.put("shrbh0", temp[5]);
				hm.put("hsrbh0", temp[6]);
				hm.put("xbiemc", temp[7]);
				hm.put("brnl00", temp[8]);
				hm.put("yblx00", temp[9]);
				hm.put("jyxmbh", temp[10]);
				hm.put("jyxmmc", temp[11]);
				vt.add(hm);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SSTException(e.getMessage());
		}
		return vt;
	}
	
	public Vector printForm(Map para) throws SSTException {
		Vector<HashMap<String,String>> vt = new Vector<HashMap<String,String>>();
		try {
			String brid00 = (String)para.get("brid00");
			String brlx00 = (String)para.get("brlx00");
			String qssj00 = (String)para.get("qssj00");
			String jzzj00 = (String)para.get("jzzj00");
			log.info("������:"+brid00+" �������ͣ�"+brlx00+" ��ʼʱ�䣺"+qssj00+" ��ֹʱ�䣺"+jzzj00);
			
			ArrayOfString array = soap.printForm(brid00, brlx00, qssj00, jzzj00);
			List<String> list = array.getString();
			for(int i=0;i<list.size();i++) {
				HashMap<String,String> hm = new HashMap<String,String>();
				String name = list.get(i);
				if(name == null || name.equals("")) {
					continue;
				}
				hm.put("bgwjmc", name);
				vt.add(hm);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SSTException(e.getMessage());
		}
		return vt;
	}
	
	public String getPdf(Map para) throws SSTException {
		String pdfstr = "";
		try {
			String bdwjmc = (String)para.get("bgwjmc");
			log.info("���浥ͼƬ�ļ�:"+bdwjmc);
			
			String url = pictureurl + bdwjmc;
			
			String pdfurl = (String)para.get("pdfurl");
			String path = pdfurl + bdwjmc;
			
			log.info("����ͼƬ��ʼ");
			
			DownloadPicFromURL.downloadPicture(url, path);
			
			log.info("����ͼƬ����");
			
			String zdbh00 = (String)para.get("zdbh00");
			String pdfname = pdfurl + zdbh00 + ".pdf";
			
			log.info("����PDF��ʼ");
			
			Image2pdf.imgToPdf(path, pdfname);
			
			log.info("����PDF����");
			
			pdfstr = Base64Util.encodeBase64File(pdfname);
			pdfstr.replace("\n", "");
			pdfstr.replace("\r", "");
			
			File file = new File(path);
			file.delete();
			
			File file2 = new File(pdfname);
			file2.delete();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SSTException(e.getMessage());
		}
		return pdfstr;
	}
	
	public void EditPrinted(Map para) throws SSTException {
		try {
			String yqdm00 = (String)para.get("yqdm00");
			String ybh000 = (String)para.get("ybh000");
			String bgrq00 = (String)para.get("bgrq00");
			log.info("��������:"+yqdm00+" ������:"+ybh000+" ��������:"+bgrq00);
			
			soap.editPrinted(yqdm00, ybh000, bgrq00);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SSTException(e.getMessage());
		}
	}
}
