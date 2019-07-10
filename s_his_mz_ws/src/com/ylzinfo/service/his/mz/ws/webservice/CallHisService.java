package com.ylzinfo.service.his.mz.ws.webservice;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.BundleConfigUtil;
import com.start.sst.osgi.util.TransferUtil;

public class CallHisService {
	private static Logger log;
	private String methodName;
	private Client client;
	private String method;
	
	public CallHisService(String MethodName) throws SSTException {
		Thread.currentThread().setContextClassLoader(null);
		log = Logger.getLogger(this.getClass().getName());
		methodName = MethodName;
		try {
			String his_wsUrl = BundleConfigUtil.getConstantValue("s_his_mz_ws","his_wsUrl");
			method = BundleConfigUtil.getConstantValue("s_his_mz_ws","his_wsOperation");
			
			client = new Client(new URL(his_wsUrl));
		} catch (Exception e) {
			log.error("初始化webservices链接出错：" + e.getMessage());
			throw new SSTException("初始化webservices链接出错：" + e.getMessage());
		}
	}
	
	public String getReqXml(Map para, String methodName) throws SSTException {
		String parentKey = "";
		String reqXml = "<?xml version=\"1.0\" encoding=\"utf-16\"?>";
		try {
			if(methodName.equals("062")) {
				reqXml = reqXml + "<RequestGetRegFee>";
			} else if(methodName.equals("048")) {
				reqXml = reqXml + "<RequestGetRegFee>";
			} else if(methodName.equals("002")) {
				reqXml = reqXml + "<RequestRegMark>";
			} else if(methodName.equals("001")) {
				reqXml = reqXml + "<RequestMarkInfo>";
			} else if(methodName.equals("004")) {
				reqXml = reqXml + "<RequestAccountInfo>";
			} else if(methodName.equals("006")) {
				reqXml = reqXml + "<RequestRegDept>";
			} else if(methodName.equals("007")) {
				reqXml = reqXml + "<RequestRegDoct>";
			} else if(methodName.equals("080")) {
				reqXml = reqXml + "<RequestQueryTimePoint>";
			} else if(methodName.equals("010")) {
				reqXml = reqXml + "<RequestReg>";
			} else if(methodName.equals("005")) {
				reqXml = reqXml + "<RequestPrePay>";
			} else if(methodName.equals("011")) {
				reqXml = reqXml + "<RequestFeeReg>";
			} else if(methodName.equals("012")) {
				reqXml = reqXml + "<RequestCharge>";
			} else if(methodName.equals("013")) {
				reqXml = reqXml + "<RequestChargeFeeItem>";
			} else if(methodName.equals("022")) {
				reqXml = reqXml + "<RequestReg>";
			}else if(methodName.equals("063")) {
				reqXml = reqXml + "<RequestReg>";
			}else if(methodName.equals("009")) {
				reqXml = reqXml + "<RequestGetRegFee>";
			}else if(methodName.equals("041")) {
				reqXml = reqXml + "<LockSignalsource>";
			}else if(methodName.equals("019")) {
				reqXml = reqXml + "<RequestInPatientInfo>";
			}else if(methodName.equals("028")) {
				reqXml = reqXml + "<RequestInpatientPrePay>";
			}else if(methodName.equals("064")) {
				reqXml = reqXml + "<RequestInPatientOutFee>";
			}
			
			if(methodName.equals("013")) {
				Map reqItem = TransferUtil.getTransRequest("s_his_mz_ws", methodName);
				Iterator entries = reqItem.entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry entry = (Map.Entry) entries.next();
					String sstkey = (String) entry.getKey();
					String hiskey = (String) entry.getValue();
					
					if(hiskey.contains("-")) {
						continue;
					}
					
					if (para.containsKey(sstkey)) {
						reqXml = reqXml + "<" + hiskey + ">" + para.get(sstkey).toString() + "</" + hiskey + ">";
					} else {
						reqXml = reqXml + "<" + hiskey + "></" + hiskey + ">";
					}
				}
				
				Vector<HashMap<String, String>> fymx = (Vector<HashMap<String, String>>)para.get("fymx");
				for(int i=0;i<fymx.size();i++) {
					reqXml = reqXml + "<FeeItems>";
					Iterator entries2 = reqItem.entrySet().iterator();
					while (entries2.hasNext()) {
						Map.Entry entry = (Map.Entry) entries2.next();
						String sstkey = (String) entry.getKey();
						String hiskey = (String) entry.getValue();
						
						if(!hiskey.contains("-")) {
							continue;
						} else {
							String temp[] = hiskey.split("-");
							if(!temp[0].equals("FeeItems")) {
								continue;
							}
							hiskey = temp[1];
							
							if(fymx.get(i).containsKey(sstkey)) {
								reqXml = reqXml + "<" + hiskey + ">" + fymx.get(i).get(sstkey).toString() + "</" + hiskey + ">";
							} else {
								reqXml = reqXml + "<" + hiskey + "></" + hiskey + ">";
							}
						}
					}
					reqXml = reqXml + "</FeeItems>";
				}
				
				Iterator entries3 = reqItem.entrySet().iterator();
				while (entries3.hasNext()) {
					Map.Entry entry = (Map.Entry) entries3.next();
					String sstkey = (String) entry.getKey();
					String hiskey = (String) entry.getValue();
					
					if(hiskey.contains("-")) {
						String temp[] = hiskey.split("-");
						if(temp[0].equals("FeeItems")) {
							continue;
						}
						if(parentKey.equals("")) {
							reqXml = reqXml + "<" + temp[0] + ">";
							parentKey = temp[0];
						}
						hiskey = temp[1];
					} else {
						continue;
					}
					
					if (para.containsKey(sstkey)) {
						reqXml = reqXml + "<" + hiskey + ">" + para.get(sstkey).toString() + "</" + hiskey + ">";
					} else {
						reqXml = reqXml + "<" + hiskey + "></" + hiskey + ">";
					}
				}
				
				if(!parentKey.equals("")) {
					reqXml = reqXml + "</" + parentKey + ">";
					parentKey = "";
				}
			} else {
				Map reqItem = TransferUtil.getTransRequest("s_his_mz_ws", methodName);
				Iterator entries = reqItem.entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry entry = (Map.Entry) entries.next();
					String sstkey = (String) entry.getKey();
					String hiskey = (String) entry.getValue();
						
					if(hiskey.contains("-")) {
						String temp[] = hiskey.split("-");
						if(parentKey.equals("")) {
							reqXml = reqXml + "<" + temp[0] + ">";
							parentKey = temp[0];
						} else if(!temp[0].equals(parentKey)) {
							reqXml = reqXml + "</" + parentKey + ">";
							reqXml = reqXml + "<" + temp[0] + ">";
							parentKey = temp[0];
						}
						hiskey = temp[1];
					} else {
						if(!parentKey.equals("")) {
							reqXml = reqXml + "</" + parentKey + ">";
							parentKey = "";
						}
					}
					
					if (para.containsKey(sstkey)) {
						reqXml = reqXml + "<" + hiskey + ">" + para.get(sstkey).toString() + "</" + hiskey + ">";
					} else {
						reqXml = reqXml + "<" + hiskey + "></" + hiskey + ">";
					}
				}
				
				if(!parentKey.equals("")) {
					reqXml = reqXml + "</" + parentKey + ">";
					parentKey = "";
				}
			}
			
			if(methodName.equals("062")) {
				reqXml = reqXml + "</RequestGetRegFee>";
			} else if(methodName.equals("048")) {
				reqXml = reqXml + "</RequestGetRegFee>";
			} else if(methodName.equals("002")) {
				reqXml = reqXml + "</RequestRegMark>";
			} else if(methodName.equals("001")) {
				reqXml = reqXml + "</RequestMarkInfo>";
			} else if(methodName.equals("004")) {
				reqXml = reqXml + "</RequestAccountInfo>";
			} else if(methodName.equals("006")) {
				reqXml = reqXml + "</RequestRegDept>";
			} else if(methodName.equals("007")) {
				reqXml = reqXml + "</RequestRegDoct>";
			} else if(methodName.equals("080")) {
				reqXml = reqXml + "</RequestQueryTimePoint>";
			} else if(methodName.equals("010")) {
				reqXml = reqXml + "</RequestReg>";
			} else if(methodName.equals("005")) {
				reqXml = reqXml + "</RequestPrePay>";
			} else if(methodName.equals("011")) {
				reqXml = reqXml + "</RequestFeeReg>";
			} else if(methodName.equals("012")) {
				reqXml = reqXml + "</RequestCharge>";
			} else if(methodName.equals("013")) {
				reqXml = reqXml + "</RequestChargeFeeItem>";
			}  else if(methodName.equals("022")) {
//				reqXml = reqXml + "</RequestBooking>";
				reqXml = reqXml + "</RequestReg>";
			}else if(methodName.equals("063")) {
				reqXml = reqXml + "</RequestReg>";
			}else if(methodName.equals("009")) {
				reqXml = reqXml + "</RequestGetRegFee>";
			}else if(methodName.equals("041")) {
				reqXml = reqXml + "</LockSignalsource>";
			}else if(methodName.equals("019")) {
				reqXml = reqXml + "</RequestInPatientInfo>";
			}else if(methodName.equals("028")) {
				reqXml = reqXml + "</RequestInpatientPrePay>";
			}else if(methodName.equals("064")) {
				reqXml = reqXml + "</RequestInPatientOutFee>";
			}
		} catch (Exception e) {
			throw new SSTException(e.getMessage());
		}
		return reqXml;
	}
	
	public Map parseXml(String xml, String methodName) throws SSTException {
		Map retMap = new HashMap();
		Vector<HashMap<String, String>> vt = new Vector<HashMap<String, String>>();
		try {
			Map reqItem = TransferUtil.getTransResponse("s_his_mz_ws", methodName);
			Document doc = DocumentHelper.parseText(xml);
			Element response = doc.getRootElement();
			List<Element> datalist = response.elements();
			for(Element data : datalist) {
				String name = data.getName();
				
				if(name.equals("PatientInfo") || name.equals("Dept") || name.equals("Doctor") || name.equals("Register") || name.equals("FeeItems") || name.equals("DeptInfo")
				   || name.equals("OutPatientInvoice") || name.equals("OutPatientFeeList") || name.equals("AppPatient") || name.equals("InPatientInfo") || name.equals("InPatientFee")) {
					HashMap<String, String> hm = new HashMap<String, String>();
					List<Element> keys = data.elements();
					for(Element key : keys) {
						if(reqItem.get(key.getName()) != null) {
							String newkey = reqItem.get(key.getName()).toString();
							hm.put(newkey, key.getTextTrim());
						}
					}
					vt.add(hm);
				} else if(name.equals("TimePoint")) {
					HashMap<String, String> hm = new HashMap<String, String>();
					if(reqItem.get(name) != null) {
						String newkey = reqItem.get(name).toString();
						hm.put(newkey, data.getTextTrim());
					}
					vt.add(hm);
				} else {
					if(reqItem.get(name) != null) {
						String newkey = reqItem.get(name).toString();
						retMap.put(newkey, data.getTextTrim());
					}
				}
			}
			if(vt.size() > 0) {
				retMap.put("vt", vt);
			}
		} catch (Exception e) {
			throw new SSTException(e.getMessage());
		}
		return retMap;
	}
	
	public Map callWs(Map para) throws SSTException {
		Map retMap = new HashMap();
		para.put("funcid", methodName);
		String zdbh00 = (String)para.get("zdbh00");
		para.put("jqbh00", "ZZJ" + zdbh00.substring(10,12));
		try {
			String reqXml = getReqXml(para, methodName);
			log.info("请求报文：" + reqXml);
			Object[] results = client.invoke(method, new Object[] { methodName, reqXml });
			String responseXml = (String) results[0];
			log.info("返回报文：" + responseXml);
			retMap = parseXml(responseXml, methodName);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SSTException(e.getMessage());
		}
		return retMap;
	}
}
