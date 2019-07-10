package com.ylzinfo.service.his.mz.ws.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="barcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="answername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="answercon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="answerid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "barcode", "itemid", "answername",
		"answercon", "answerid" })
@XmlRootElement(name = "CriticalValueAlertFeedBack")
public class CriticalValueAlertFeedBack {

	protected String barcode;
	protected String itemid;
	protected String answername;
	protected String answercon;
	protected String answerid;

	/**
	 * Gets the value of the barcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Sets the value of the barcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBarcode(String value) {
		this.barcode = value;
	}

	/**
	 * Gets the value of the itemid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getItemid() {
		return itemid;
	}

	/**
	 * Sets the value of the itemid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setItemid(String value) {
		this.itemid = value;
	}

	/**
	 * Gets the value of the answername property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAnswername() {
		return answername;
	}

	/**
	 * Sets the value of the answername property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAnswername(String value) {
		this.answername = value;
	}

	/**
	 * Gets the value of the answercon property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAnswercon() {
		return answercon;
	}

	/**
	 * Sets the value of the answercon property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAnswercon(String value) {
		this.answercon = value;
	}

	/**
	 * Gets the value of the answerid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAnswerid() {
		return answerid;
	}

	/**
	 * Sets the value of the answerid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAnswerid(String value) {
		this.answerid = value;
	}

}
