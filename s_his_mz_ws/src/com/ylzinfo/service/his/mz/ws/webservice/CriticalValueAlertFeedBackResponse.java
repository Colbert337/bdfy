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
 *         &lt;element name="CriticalValueAlertFeedBackResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "criticalValueAlertFeedBackResult" })
@XmlRootElement(name = "CriticalValueAlertFeedBackResponse")
public class CriticalValueAlertFeedBackResponse {

	@XmlElement(name = "CriticalValueAlertFeedBackResult")
	protected int criticalValueAlertFeedBackResult;

	/**
	 * Gets the value of the criticalValueAlertFeedBackResult property.
	 * 
	 */
	public int getCriticalValueAlertFeedBackResult() {
		return criticalValueAlertFeedBackResult;
	}

	/**
	 * Sets the value of the criticalValueAlertFeedBackResult property.
	 * 
	 */
	public void setCriticalValueAlertFeedBackResult(int value) {
		this.criticalValueAlertFeedBackResult = value;
	}

}
