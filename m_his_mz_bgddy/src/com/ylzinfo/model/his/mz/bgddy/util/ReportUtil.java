package com.ylzinfo.model.his.mz.bgddy.util;

import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.BundleUtil;

import basesst.SstBaseBundleUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportUtil {
	public void createPdf(List prtList,String[] prtNames,Map paraMap,String temurl,String pdfname) throws SSTException{
		try {
			//BundleContext bundleContext = SstBaseBundleUtil.getBundleContext();
			//Bundle bundle = BundleUtil.getBundle(bundleContext, "m_his_mz_bgddy");
			//Class clientClazz = bundle.loadClass(ReportUtil.class.getName());
			//Thread.currentThread().setContextClassLoader(clientClazz.getClassLoader());
			JRDataSource s1 = new HibernateQueryResultDataSource(prtList,prtNames);
			JasperReport s2 = (JasperReport) JRLoader.loadObject(temurl);
			JasperPrint print = JasperFillManager.fillReport(s2, paraMap, s1);
			JasperExportManager.exportReportToPdfFile(print, pdfname);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSTException("Ìî³ä±¨±í³ö´í"+e.getMessage());
		}
	}
}
