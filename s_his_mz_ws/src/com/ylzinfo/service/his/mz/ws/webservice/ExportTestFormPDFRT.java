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
 *         &lt;element name="machineId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sampleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "machineId", "sampleId", "testdate" })
@XmlRootElement(name = "ExportTestFormPDF_RT")
public class ExportTestFormPDFRT {

	protected String machineId;
	protected String sampleId;
	protected String testdate;

	/**
	 * Gets the value of the machineId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMachineId() {
		return machineId;
	}

	/**
	 * Sets the value of the machineId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMachineId(String value) {
		this.machineId = value;
	}

	/**
	 * Gets the value of the sampleId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSampleId() {
		return sampleId;
	}

	/**
	 * Sets the value of the sampleId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSampleId(String value) {
		this.sampleId = value;
	}

	/**
	 * Gets the value of the testdate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTestdate() {
		return testdate;
	}

	/**
	 * Sets the value of the testdate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTestdate(String value) {
		this.testdate = value;
	}

}
