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
				throw new SSTException("图片文件不存在");
			}
			
			Document document = new Document(PageSize.A4,0,0,10,0);
			FileOutputStream fos = new FileOutputStream(pdfFilePath);
			PdfWriter.getInstance(document, fos);
			
			// 添加PDF文档的某些信息，比如作者，主题等等
			document.addAuthor("自助机");
			document.addSubject("报告打印");
			
			// 打开文档
			document.open();
			
			// 读取一个图片
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
			
			// 插入一个图片
			document.add(image);
			
			document.close();
			fos.flush();
			fos.close();
		} catch (Exception e) {
			throw new SSTException(e.getMessage());
		}
	}
}