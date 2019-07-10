package com.ylzinfo.service.his.mz.ws.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExportTestFormPDF_RTResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "exportTestFormPDFRTResult" })
@XmlRootElement(name = "ExportTestFormPDF_RTResponse")
public class ExportTestFormPDFRTResponse {

	@XmlElement(name = "ExportTestFormPDF_RTResult")
	protected byte[] exportTestFormPDFRTResult;

	/**
	 * Gets the value of the exportTestFormPDFRTResult property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getExportTestFormPDFRTResult() {
		return exportTestFormPDFRTResult;
	}

	/**
	 * Sets the value of the exportTestFormPDFRTResult property.
	 * 
	 * @param value
	 *            allowed object is byte[]
	 */
	public void setExportTestFormPDFRTResult(byte[] value) {
		this.exportTestFormPDFRTResult = ((byte[]) value);
	}

}
