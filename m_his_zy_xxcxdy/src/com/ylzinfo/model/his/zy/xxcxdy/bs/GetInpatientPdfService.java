package com.ylzinfo.model.his.zy.xxcxdy.bs;

import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.ylzinfo.matrix.model.his.zy.qddy.GetInpatientPdfModelMatrix;

/**
 * @description: ��ȡסԺ�嵥PDF�ļ�
 * @author fanggx
 * @date: 2015-11-11 ����03:44:43
 */
public class GetInpatientPdfService extends GetInpatientPdfModelMatrix {
	
	public Logger logger = null;

	public GetInpatientPdfService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String getInpatientPdf(Map param) throws SSTException {
		return null;
	}
}
