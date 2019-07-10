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
	 * ���ݸ�����Կ�����㷨������Կ
	 * 
	 * @param algorithm
	 *            ��Կ�㷨
	 * @return ��Կ
	 * @throws RuntimeException
	 *             ��{@link java.security.NoSuchAlgorithmException} ����ʱ
	 */
	private static byte[] getHmacKey(String algorithm) {
		// ��ʼ��KeyGenerator
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
		// ������Կ
		SecretKey secretKey = keyGenerator.generateKey();
		// �����Կ
		return secretKey.getEncoded();
	}

	/**
	 * ��ȡHmaMD5����Կ
	 * 
	 * @return HmaMD5����Կ
	 * @throws RuntimeException
	 *             ��{@link java.security.NoSuchAlgorithmException} ����ʱ
	 */
	public static byte[] getHmaMD5key() {
		return getHmacKey("HmacMD5");
	}

	/**
	 * ��ȡHmaSHA����Կ
	 * 
	 * @return HmaSHA����Կ
	 * @throws RuntimeException
	 *             ��{@link java.security.NoSuchAlgorithmException} ����ʱ
	 */
	public static byte[] getHmaSHAkey() {
		return getHmacKey("HmacSHA1");
	}

	/**
	 * ת����Կ
	 * 
	 * @param key
	 *            ��������Կ
	 * @param algorithm
	 *            ��Կ�㷨
	 * @return ��Կ
	 */
	private static Key toKey(byte[] key, String algorithm) {
		// ������Կ
		return new SecretKeySpec(key, algorithm);
	}

	/**
	 * ʹ��HmacMD5��ϢժҪ�㷨������ϢժҪ
	 * 
	 * @param data
	 *            ����ϢժҪ������
	 * @param key
	 *            ��Կ
	 * @return ��ϢժҪ������Ϊ16���ֽ����飩
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
	 * ʹ��HmacMD5��ϢժҪ�㷨������ϢժҪ
	 * 
	 * @param data
	 *            ����ϢժҪ������
	 * @param key
	 *            ��Կ
	 * @return ��ϢժҪ������Ϊ16���ֽ����飩
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
