package com.ylzinfo.model.his.mz.jbxx.dao;

import java.sql.Connection;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.osgi.util.ProcedureUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.JdbcUtils;
import com.start.sst.util.StringUtil;
import com.ylzinfo.util.HisDBUtil;
/**
 * 
 * @description: ������Ϣ
 * @author baisj
 * @date: Sep 9, 2015 11:10:41 AM
 */
public class JbxxDao {
	
     private Logger log=null;
     private String errmsg=null;
     
     public JbxxDao(){
    	 log=Logger.getLogger(this.getClass().getName());
     }
     /**
      * 
      * @Description: ��ȡ���˻�����Ϣ
      * @param conn
      * @param para
      * @return
      * @throws SSTException:
      */
     public Vector callSpSstGetgrjbxx(Connection conn,Map para)throws SSTException{
    	 Vector vt=null;
    	 ProcedureUtil pu=new ProcedureUtil();
    	 try {
			long startTime=Long.parseLong(DateUtil.getNowTime());
			log.info("***********����callSpSstGetgrjbxx��ʼ*****************");
			vt=pu.executeQuery(conn, "SP_SST_GETYYGRJBXX", para);
			long endTime=Long.parseLong(DateUtil.getNowTime());
			for(int i=0;i<vt.size();i++){
				Map mp=(Map)vt.get(i);
				mp.put("xming0", StringUtil.Null2BlankAndTrim(mp.get("xming0")));// ����
				mp.put("ynzhye", StringUtil.Null2BlankAndTrim(mp.get("ynzhye")));// Ժ��Ԥ����
				mp.put("xbie00", StringUtil.Null2BlankAndTrim(mp.get("xbie00")));// �Ա�
				mp.put("csrq00", StringUtil.Null2BlankAndTrim(mp.get("csrq00")));// ��������
				mp.put("fzxbh0", StringUtil.Null2BlankAndTrim(mp.get("fzxbh0")));// �����ı��
				mp.put("lxdh00", StringUtil.Null2BlankAndTrim(mp.get("lxdh00")));// ��ϵ�绰
				mp.put("bycc00", StringUtil.Null2BlankAndTrim(mp.get("bycc00")));// ���ó���
			}
			return vt;
		} catch (Exception e) {
			errmsg="����SP_SST_GETYYGRJBXX�洢���̳���:";
			log.error(errmsg+e.getMessage());
			throw new SSTException(errmsg+e.getMessage(),e);
		}
     }
     
     public Map callSpSstGetgrjbxxOldFrame(Map para)throws SSTException{
    	 Map retMp = null;
    	 ProcedureUtil pu=new ProcedureUtil();
    	 Connection conn = null;
    	 try {
    		conn = HisDBUtil.getConnection();
			long startTime=Long.parseLong(DateUtil.getNowTime());
			log.info("***********����callSpSstGetgrjbxx��ʼ*****************");
			retMp=pu.executeQueryComplex(conn, "SP_SST_GETYYGRJBXX", para);
			long endTime=Long.parseLong(DateUtil.getNowTime());			
			return retMp;
		} catch (Exception e) {
			errmsg="����SP_SST_GETYYGRJBXX�洢���̳���:";
			log.error(errmsg+e.getMessage());
			throw new SSTException(errmsg+e.getMessage(),e);
		}finally{
			HisDBUtil.closeConnection(conn);
		}
     }
}
