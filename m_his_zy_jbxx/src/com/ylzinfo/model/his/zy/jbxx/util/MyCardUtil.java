package com.ylzinfo.model.his.zy.jbxx.util;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

import com.start.sst.exception.SSTException;

public class MyCardUtil {
	
	public static String ICReadCardNo(String jiamiCardno) throws SSTException{
		JNative nativeMethod = null;
		Pointer cardno = null;
		String decodeCardNo = "";
		try {
			nativeMethod = new JNative("IC_read.dll","ICReadCardNo");
			cardno = new Pointer(MemoryBlockFactory.createMemoryBlock(128));
			nativeMethod.setRetVal(Type.INT);
			nativeMethod.setParameter(0, jiamiCardno);
			nativeMethod.setParameter(1, cardno);
			nativeMethod.invoke();
			decodeCardNo = cardno.getAsString();
			String ret=nativeMethod.getRetVal();
			if(!"0".equals(ret)){
				throw new SSTException("ªÒ»°ø®∫≈ ß∞‹");
			}
		
			return decodeCardNo;
		} 
		catch(Exception e){
			e.printStackTrace();
			throw new SSTException("ªÒ»°ø®∫≈ ß∞‹");
		}finally {
			try {
				if(cardno != null){
					cardno.dispose();
				}
			} catch (NativeException e) {
				throw new SSTException("ªÒ»°ø®∫≈ ß∞‹");
			}
		}
	}
  
}