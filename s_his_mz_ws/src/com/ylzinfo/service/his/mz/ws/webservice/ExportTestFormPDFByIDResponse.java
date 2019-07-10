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
 *         &lt;element name="ExportTestFormPDF_byIDResult" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "exportTestFormPDFByIDResult" })
@XmlRootElement(name = "ExportTestFormPDF_byIDResponse")
public class ExportTestFormPDFByIDResponse {

	@XmlElement(name = "ExportTestFormPDF_byIDResult")
	protected ArrayOfString exportTestFormPDFByIDResult;

	/**
	 * Gets the value of the exportTestFormPDFByIDResult property.
	 * 
	 * @return possible object is {@link ArrayOfString }
	 * 
	 */
	public ArrayOfString getExportTestFormPDFByIDResult() {
		return exportTestFormPDFByIDResult;
	}

	/**
	 * Sets the value of the exportTestFormPDFByIDResult property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfString }
	 * 
	 */
	public void setExportTestFormPDFByIDResult(ArrayOfString value) {
		this.exportTestFormPDFByIDResult = value;
	}

}
