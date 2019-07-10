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
 *         &lt;element name="patientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dtReg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dtEnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "patientId", "patientType", "dtReg", "dtEnd" })
@XmlRootElement(name = "PrintForm")
public class PrintForm {

	protected String patientId;
	protected String patientType;
	protected String dtReg;
	protected String dtEnd;

	/**
	 * Gets the value of the patientId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * Sets the value of the patientId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPatientId(String value) {
		this.patientId = value;
	}

	/**
	 * Gets the value of the patientType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPatientType() {
		return patientType;
	}

	/**
	 * Sets the value of the patientType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPatientType(String value) {
		this.patientType = value;
	}

	/**
	 * Gets the value of the dtReg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDtReg() {
		return dtReg;
	}

	/**
	 * Sets the value of the dtReg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDtReg(String value) {
		this.dtReg = value;
	}

	/**
	 * Gets the value of the dtEnd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDtEnd() {
		return dtEnd;
	}

	/**
	 * Sets the value of the dtEnd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDtEnd(String value) {
		this.dtEnd = value;
	}

}
