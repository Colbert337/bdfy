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
 *         &lt;element name="GetReportLockDoubtResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getReportLockDoubtResult" })
@XmlRootElement(name = "GetReportLockDoubtResponse")
public class GetReportLockDoubtResponse {

	@XmlElement(name = "GetReportLockDoubtResult")
	protected String getReportLockDoubtResult;

	/**
	 * Gets the value of the getReportLockDoubtResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGetReportLockDoubtResult() {
		return getReportLockDoubtResult;
	}

	/**
	 * Sets the value of the getReportLockDoubtResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGetReportLockDoubtResult(String value) {
		this.getReportLockDoubtResult = value;
	}

}
