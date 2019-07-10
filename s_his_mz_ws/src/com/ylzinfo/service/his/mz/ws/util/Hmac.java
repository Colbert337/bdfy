package com.ylzinfo.service.his.mz.ws.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @classname Hmac
 * @author Lan
 * @version  V1.0.1
 * @date 2013-7-7
 */
public class Hmac {

	/**
	 * 根据给定密钥生成算法创建密钥
	 * 
	 * @param algorithm
	 *            密钥算法
	 * @return 密钥
	 * @throws RuntimeException
	 *             当{@link java.security.NoSuchAlgorithmException} 发生时
	 */
	private static byte[] getHmacKey(String algorithm) {
		// 初始化KeyGenerator
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
		// 产生密钥
		SecretKey secretKey = keyGenerator.generateKey();
		// 获得密钥
		return secretKey.getEncoded();
	}

	/**
	 * 获取HmaMD5的密钥
	 * 
	 * @return HmaMD5的密钥
	 * @throws RuntimeException
	 *             当{@link java.security.NoSuchAlgorithmException} 发生时
	 */
	public static byte[] getHmaMD5key() {
		return getHmacKey("HmacMD5");
	}

	/**
	 * 获取HmaSHA的密钥
	 * 
	 * @return HmaSHA的密钥
	 * @throws RuntimeException
	 *             当{@link java.security.NoSuchAlgorithmException} 发生时
	 */
	public static byte[] getHmaSHAkey() {
		return getHmacKey("HmacSHA1");
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密钥
	 * @param algorithm
	 *            密钥算法
	 * @return 密钥
	 */
	private static Key toKey(byte[] key, String algorithm) {
		// 生成密钥
		return new SecretKeySpec(key, algorithm);
	}

	/**
	 * 使用HmacMD5消息摘要算法计算消息摘要
	 * 
	 * @param data
	 *            做消息摘要的数据
	 * @param key
	 *            密钥
	 * @return 消息摘要（长度为16的字节数组）
	 */
	public static byte[] encodeHmacMD5(byte[] data, Key key) {
		Mac mac = null;
		try {
			mac = Mac.getInstance("HmacMD5");
			mac.init(key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			return new byte[0];
		}
		return mac.doFinal(data);
	}

	/**
	 * 使用HmacMD5消息摘要算法计算消息摘要
	 * 
	 * @param data
	 *            做消息摘要的数据
	 * @param key
	 *            密钥
	 * @return 消息摘要（长度为16的字节数组）
	 */
	public static byte[] encodeHmacMD5(byte[] data, byte[] key) {
		Key k = toKey(key, "HmacMD5");
		return encodeHmacMD5(data, k);
	}

	@SuppressWarnings("unused")
	private static String showByteArray(byte[] data) {
		if (null == data) {
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for (byte b : data) {
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("D:/test.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String str = null;
		StringBuilder sb = new StringBuilder();
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}

		String KEY = "11111111111111111111111111111111";
		
		String signatrue = getSignature(sb.toString(), KEY);
		System.out.println(signatrue);
		System.out.println(signatrue.length());
	}
	
	public static String getSignature(String str,String key){
		String signature = "";
		try {
			byte[] encodeData = encodeHmacMD5(str.getBytes(), key.getBytes());
			signature = Hex.encodeHexStr(encodeData, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signature;
	}
}
