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
 *         &lt;element name="ExportTestFormPDF_ByPatientSeqResult" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "exportTestFormPDFByPatientSeqResult" })
@XmlRootElement(name = "ExportTestFormPDF_ByPatientSeqResponse")
public class ExportTestFormPDFByPatientSeqResponse {

	@XmlElement(name = "ExportTestFormPDF_ByPatientSeqResult")
	protected ArrayOfString exportTestFormPDFByPatientSeqResult;

	/**
	 * Gets the value of the exportTestFormPDFByPatientSeqResult property.
	 * 
	 * @return possible object is {@link ArrayOfString }
	 * 
	 */
	public ArrayOfString getExportTestFormPDFByPatientSeqResult() {
		return exportTestFormPDFByPatientSeqResult;
	}

	/**
	 * Sets the value of the exportTestFormPDFByPatientSeqResult property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfString }
	 * 
	 */
	public void setExportTestFormPDFByPatientSeqResult(ArrayOfString value) {
		this.exportTestFormPDFByPatientSeqResult = value;
	}

}
