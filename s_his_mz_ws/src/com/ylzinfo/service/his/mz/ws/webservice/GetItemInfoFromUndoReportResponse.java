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
 *         &lt;element name="GetItemInfoFromUndoReportResult" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getItemInfoFromUndoReportResult" })
@XmlRootElement(name = "GetItemInfoFromUndoReportResponse")
public class GetItemInfoFromUndoReportResponse {

	@XmlElement(name = "GetItemInfoFromUndoReportResult")
	protected ArrayOfString getItemInfoFromUndoReportResult;

	/**
	 * Gets the value of the getItemInfoFromUndoReportResult property.
	 * 
	 * @return possible object is {@link ArrayOfString }
	 * 
	 */
	public ArrayOfString getGetItemInfoFromUndoReportResult() {
		return getItemInfoFromUndoReportResult;
	}

	/**
	 * Sets the value of the getItemInfoFromUndoReportResult property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfString }
	 * 
	 */
	public void setGetItemInfoFromUndoReportResult(ArrayOfString value) {
		this.getItemInfoFromUndoReportResult = value;
	}

}
