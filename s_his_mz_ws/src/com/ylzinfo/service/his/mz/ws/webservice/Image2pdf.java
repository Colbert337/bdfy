package com.ylzinfo.service.his.mz.ws.webservice;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.start.sst.exception.SSTException;

public class Image2pdf {
	
	public static void imgToPdf(String imgFilePath, String pdfFilePath) throws SSTException {
		try {
			File file = new File(imgFilePath);
			if(!file.exists()) {
				throw new SSTException("ͼƬ�ļ�������");
			}
			
			Document document = new Document(PageSize.A4,0,0,10,0);
			FileOutputStream fos = new FileOutputStream(pdfFilePath);
			PdfWriter.getInstance(document, fos);
			
			// ���PDF�ĵ���ĳЩ��Ϣ���������ߣ�����ȵ�
			document.addAuthor("������");
			document.addSubject("�����ӡ");
			
			// ���ĵ�
			document.open();
			
			// ��ȡһ��ͼƬ
			Image image = Image.getInstance(imgFilePath);
			float imageHeight=image.getScaledHeight();
			float imageWidth=image.getScaledWidth();
			
			int i=0;
			while(imageWidth>595){
				image.scalePercent(100-i);
				i++;
				imageHeight=image.getScaledHeight();
				imageWidth=image.getScaledWidth();
			}
			
			image.setAlignment(Image.ALIGN_CENTER); 
			
			// ����һ��ͼƬ
			document.add(image);
			
			document.close();
			fos.flush();
			fos.close();
		} catch (Exception e) {
			throw new SSTException(e.getMessage());
		}
	}
}