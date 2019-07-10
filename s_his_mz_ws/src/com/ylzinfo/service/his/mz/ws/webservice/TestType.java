package com.ylzinfo.service.his.mz.ws.webservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for TestType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="TestType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RT"/>
 *     &lt;enumeration value="GM"/>
 *     &lt;enumeration value="MB"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TestType")
@XmlEnum
public enum TestType {

	RT, GM, MB;

	public String value() {
		return name();
	}

	public static TestType fromValue(String v) {
		return valueOf(v);
	}

}
