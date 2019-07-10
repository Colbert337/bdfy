package com.ylzinfo.model.his.zy.yjjcz.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import basesst.SstBaseBundleUtil;

import com.start.sst.exception.SSTException;
import com.start.sst.soap.Parser;
import com.start.sst.soap.soapentity.SoapResponse;
import com.start.sst.util.Base64Util;
import com.start.sst.util.DBUtil;
import com.ylzinfo.model.his.zy.yjjcz.config.AlipayConfig;
import com.ylzinfo.model.his.zy.yjjcz.dao.EylzPayDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallDxService;

public class ZfbpayPreOrderService {
	
	private Logger log=null;
	
	public ZfbpayPreOrderService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	/**
	 * ֧������ֵ��ά��ͼƬ����
	 * 
	 * @param picurl
	 *            ����·��
	 * @param picName
	 *            ͼƬ��-������ˮ��.jpg
	 * @return picdlpath �����ͼƬ���ص�ַ
	 * @throws FrameException
	 * @author  
	 */
	private String downloadPicture(String picurl, String picName) throws SSTException{
		String currentpath = SstBaseBundleUtil.getProjectRoot();
		log.info(currentpath);
		
		String picPath = currentpath + "AlipayPic\\" + picName + ".jpg"; // �������ϵ�ͼƬ��ŵ�ַ
		log.info("ͼƬ���λ�ã�"+picPath);
		
		try {
			URL picUrl = new URL(picurl);
			File outFile = new File(picPath);
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = picUrl.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			log.info("֧������ά��ͼƬ���س���ԭ��" + e.getMessage());
			throw new SSTException("֧������ά��ͼƬ���س���ԭ��" + e.getMessage());
		}
		return picPath;
	}
	
	/**
	 * 
	* @Description: ֧����֧��Ԥ�µ�
	* @param para
	* @return
	* @throws SSTException 
	* @throws
	 */
	public String zfbpayPreOrder(Map para)throws SSTException{
		Connection conn = null;
		EylzPayDao dao = new EylzPayDao();
		String jylsh0 = "";
		String ptqqls = "";
		String ptddls = "";
		String cshsj0 = "";
		try{
			conn = DBUtil.getConnection();
			ptqqls = dao.getPtqqls(conn) + "BDFY";
			jylsh0 = dao.getZfbJylsh(conn);
			log.info("====================["+jylsh0+"]֧����Ԥ�µ���ʼ=====================");
			para.put("jylsh0", jylsh0);
			para.put("ptqqls", ptqqls);
			
			para.put("jylx00", "ZFB");
			CallDxService ws = new CallDxService();
			Map map = ws.RealOneFacePayPre(para);
			
			ptddls = ptqqls;
			para.put("czztbz", "0");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cshsj0 = sf.format(new Date());
			para.put("cshsj0", cshsj0);
			para.put("ptddls", ptddls);
			para.put("ddlx00", "01");
			dao.insertYjjZfbcz(conn, para);
			
			Map res = new HashMap();
			Vector vt = new Vector();
			res.put("jylsh0", jylsh0);
			res.put("cshsj0", cshsj0);
			res.put("zfcode", (String)map.get("payurl"));
	        res.put("zfqqls", ptddls);
	        vt.add(res);
	        HashMap<String, Object> evtBody = new HashMap<String, Object>();
	        SoapResponse evtResponse = new SoapResponse();
	        evtBody.put("retrieve", vt);
	        evtResponse.setBody(evtBody);
	        evtResponse.setMsg("OK");
	        evtResponse.setSucessFlag(true);
	        log.info("====================["+jylsh0+"]֧����Ԥ�µ�����=====================");
	        Parser parser = new Parser();
	        return  parser.formatSoap(evtResponse);
		}catch (Exception e) {
			log.error("["+jylsh0+"]֧����Ԥ�µ�ʧ��"+e.getMessage());
			throw new SSTException("["+jylsh0+"]֧����Ԥ�µ�ʧ��"+e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
