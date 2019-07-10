package com.ylzinfo.model.his.mz.mzyy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * �����������Ҫ����ȡ�κ���֤����
 * 
 * @version 1.0
 * @author ���ٸ�
 * @update 2011-6-10 ����11:43:24
 */
public class StringUtil {



	/**
	 * ��ǰʱ���Ƿ�����ָ��ʱ��
	 * 
	 */
	public static boolean nowIsEarly(String time, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(formatter.parse(time));
			c2.setTime(formatter.parse(getCurrTime(pattern)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2);
		if (result > 0 || result == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �������е�ʱ���ȡϵͳ��ǰʱ�� ,ָ����ʽ
	 * 
	 * @return
	 */
	public static String getCurrTime(String pattern) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat(pattern);
		String datetime;
		datetime = df.format(now);
		return datetime;
	}

	/**
	 * �Ƿ�ǰʱ��
	 * 
	 */
	public static boolean isCurrentPeriod(String time, String pattern) {
		try {
			GregorianCalendar gcalendar = new GregorianCalendar();

			int nowampm = gcalendar.get(GregorianCalendar.AM_PM);
			DateFormat df = new SimpleDateFormat(pattern);// ���ڸ�ʽ����

			Date giventime = df.parse(time);
			gcalendar.setTime(giventime);
			int timeampm = gcalendar.get(GregorianCalendar.AM_PM);
			if (nowampm == timeampm) {
				System.out.println("ָ��ʱ���ǵ�ǰʱ�Σ�");
				return true;
			} else {
				System.out.println("ָ��ʱ��ǵ�ǰʱ�Σ�");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * �ַ������ڵĸ�ʽת��
	 * 
	 * @param dateStr
	 *            ��Ҫת���������ַ���
	 * @param pattern0
	 *            ���ڵ�ԭ����ʽ
	 * @param pattern1
	 *            ���ڵ�Ҫת���ĸ�ʽ
	 * @return
	 * @throws ParseException
	 */
	public static String dateFormat2String(String dateStr, String pattern0,
			String pattern1) throws ParseException {
		SimpleDateFormat ymdFormatter0 = new SimpleDateFormat(pattern0);
		SimpleDateFormat ymdFormatter1 = new SimpleDateFormat(pattern1);
		Date date = ymdFormatter0.parse(dateStr);
		return ymdFormatter1.format(date);
	}

	/**
	 * getSystemDateTime ��ȡϵͳ����ʱ��
	 * 
	 * @param dateStr
	 * @param pattern0
	 * @param pattern1
	 * @return
	 * @throws ParseException
	 */
	/**
	 * ��ȡ��ǰ���ں�ʱ�� ��ʽ"yyyy-MM-dd HH:mm:ss"
	 */
	public static String getSystemDateTime() {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime;
		datetime = df.format(now);
		// System.out.print(datetime);
		return datetime;
	}

	/**
	 * �ַ������ڵĸ�ʽת��
	 * 
	 * @param dateStr
	 *            ��Ҫת���������ַ���
	 * @param pattern0
	 *            ���ڵ�ԭ����ʽ
	 * @param pattern1
	 *            ���ڵ�Ҫת���ĸ�ʽ
	 * @return
	 * @throws ParseException
	 */
	public static String currentTimeMillis2String(long time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new java.util.Date(time));

	}

	/**
	 * ��ʱ�����루�о�ȷ�����룩
	 * 
	 */
	public static String consumeTime2String(long starttime, long endtime) {

		return (endtime - starttime) / 1000.00 + "��";
	}

	/**
	 * ��ʽ������,�����ʽ,����String��,���ɻ�ȡ�̶����ڽ��и�ʽ���������,Ҳ�ɻ�ȡϵͳ��ǰ���ڸ�ʽ��������� ���ڸ�ʽyyyy-MM-dd
	 * HH:mm:ss
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws FrameException
	 * @throws ParseException
	 */
	public static String DateToString(Date date, String format) {
		if (format == null || format == "")
			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date == null)
			return sdf.format(new java.util.Date());
		else
			return sdf.format(date);
	}
	
	/**
	 * ��ȡ��������ڣ�YYYY-MM-DD��
	 * 
	 * @return
	 */
	public static String getBeforeday(int days, String format) {

		Calendar calendar = Calendar.getInstance();// ��ʱ��ӡ����ȡ����ϵͳ��ǰʱ��
		calendar.add(Calendar.DATE, -days); // �õ�ǰһ��
		String beforeday = new SimpleDateFormat(format)
				.format(calendar.getTime());
		// System.out.println(yestedayDate);
		return beforeday;
	}
	
	public static String getTomorrowday(int days, String format) {

		Calendar calendar = Calendar.getInstance();// ��ʱ��ӡ����ȡ����ϵͳ��ǰʱ��
		calendar.add(Calendar.DATE, days); // ǰһ��
		String beforeday = new SimpleDateFormat(format)
				.format(calendar.getTime());
		// System.out.println(yestedayDate);
		return beforeday;
	}

	/**
	 * ��ȡ��ǰ���� ��ʽ"yyyyMMdd"
	 */
	public static String getDqrq() {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String datetime;
		datetime = df.format(now);
		System.out.print(datetime);
		return datetime;
	}

	/**
	 * ��ȡ��ǰ���� ��ʽ"yyyy-MM-dd"
	 */
	public static String getDqrq2() {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String datetime;
		datetime = df.format(now);
		// System.out.print(datetime);
		return datetime;
	}

	/**
	 * ��ȡ��ǰʱ�� ��ʽ"HH:mm:ss"
	 */
	public static String getDqsj() {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String datetime;
		datetime = df.format(now);
		// System.out.print(datetime);
		return datetime;
	}

	/**
	 * �������е�ʱ���ȡϵͳ��ǰʱ�䣨��ȷ�����룩
	 * 
	 * @return
	 */
	public static String getNowTime() {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String datetime;
		datetime = df.format(now);
		return datetime;
	}

	/**
	 * ��ȡ��������ڣ�YYYY-MM-DD��
	 * 
	 * @return
	 */
	public static String getYesteday() {

		Calendar calendar = Calendar.getInstance();// ��ʱ��ӡ����ȡ����ϵͳ��ǰʱ��
		calendar.add(Calendar.DATE, -1); // �õ�ǰһ��
		String yestedayDate = new SimpleDateFormat("yyyy-MM-dd")
				.format(calendar.getTime());
		// System.out.println(yestedayDate);
		return yestedayDate;
	}
	/**
	 * HIS��Ʊ��Ŀ���->ҽ����Ŀ���(����)
	 * 
	 * @param fpxmmc
	 * @return
	 * @author linjf
	 * @version V1.0.1
	 * @date 2015-4-16
	 */
	public static String convertFpxmbh(String his_fpxmbh, String flag) {
	
		String value = "", yb_fpxmbh = "", yb_fpxmmc = "";
		if (his_fpxmbh.equals("20")) {//20
			yb_fpxmbh = "01";//��ҩ��
			yb_fpxmmc = "��ҩ��";
		} 
		else if (his_fpxmbh.equals("19")) {//19
			yb_fpxmbh = "02";//��ҩ��
			yb_fpxmmc = "��ҩ��";
		} 
		else if (his_fpxmbh.equals("21")) {//21
			yb_fpxmbh = "03";//��ҩ��
			yb_fpxmmc = "��ҩ��";
		} 
		else if (his_fpxmbh.equals("18")) {//18
			yb_fpxmbh = "04";//�Һŷ�
			yb_fpxmmc = "�Һŷ�";
		} 
		else if (his_fpxmbh.equals("10")) {//10
			yb_fpxmbh = "05";//��λ��
			yb_fpxmmc = "��λ��";
		} 
		else if (his_fpxmbh.equals("12")) {//12
			yb_fpxmbh = "06";//����
			yb_fpxmmc = "����";
		} 
		else if (his_fpxmbh.equals("06")) {//06
			yb_fpxmbh = "07";//�����
			yb_fpxmmc = "�����";
		} 
		else if (his_fpxmbh.equals("04")) {//04
			yb_fpxmbh = "08";//����
			yb_fpxmmc = "����";
		} 
		else if (his_fpxmbh.equals("01")) {//01
			yb_fpxmbh = "09";//�����
			yb_fpxmmc = "�����";
		} 
		else if (his_fpxmbh.equals("02")) {//02
			yb_fpxmbh = "10";//���Ʒ�
			yb_fpxmmc = "���Ʒ�";
		} 
		else if (his_fpxmbh.equals("11")) {//11
			yb_fpxmbh = "11";//������
			yb_fpxmmc = "������";
		} 
		else if (his_fpxmbh.equals("16")) {//16
			yb_fpxmbh = "12";//�Ȼ�����
			yb_fpxmmc = "�Ȼ�����";
		} 
		else if (his_fpxmbh.equals("13")) {//13
			yb_fpxmbh = "13";//������
			yb_fpxmmc = "������";
		} 
		else if (his_fpxmbh.equals("05")) {//05
			yb_fpxmbh = "14";//MRI��
			yb_fpxmmc = "MRI��";
		} 
		else if (his_fpxmbh.equals("15")) {//15
			yb_fpxmbh = "15";//CT��
			yb_fpxmmc = "CT��";
		} 
		else if (his_fpxmbh.equals("17")) {//17
			yb_fpxmbh = "16";//�ʳ���
			yb_fpxmmc = "�ʳ���";
		} 
		else if (his_fpxmbh.equals("14")) {//14
			yb_fpxmbh = "17";//������
			yb_fpxmmc = "������";
		} 
		else if (his_fpxmbh.equals("07")) {//07
			yb_fpxmbh = "18";//��Ѫ��
			yb_fpxmmc = "��Ѫ��";
		} 
		else if (his_fpxmbh.equals("08")) {//08
			yb_fpxmbh = "19";//�����
			yb_fpxmmc = "�����";
		} 
		else if (his_fpxmbh.equals("09")) {//09
			yb_fpxmbh = "20";//���������Ŀ��
			yb_fpxmmc = "���������Ŀ��";
		} 
		else if (his_fpxmbh.equals("03")) {//03
			yb_fpxmbh = "21";//����ҽ�Ʒ�
			yb_fpxmmc = "����ҽ�Ʒ�";
		} 
		else
		{
			yb_fpxmbh = "00";
			yb_fpxmmc = "��";
		}

		if(flag.equals("N"))//N=>��Ʊ��Ŀ���
			value = yb_fpxmbh;
		else if(flag.equals("C"))//C=>��Ʊ��Ŀ����
			value = yb_fpxmmc;
		
		return value;
	}

	/**
	 * ��Ʊ��Ŀ����ת��Ʊ��Ŀ���
	 * 
	 * @param fpxmmc
	 * @return
	 * @author zhou
	 * @version V1.0.1
	 * @date 2012-6-16
	 */
	public static String fpxmmcTOfpxmbh(String fpxmmc) {
		String fpxmbh = "";
		if (fpxmmc.equalsIgnoreCase("��ҩ��")) {
			fpxmbh = "01";
		} else if (fpxmmc.equalsIgnoreCase("��ҩ��")) {
			fpxmbh = "02";
		} else if (fpxmmc.equalsIgnoreCase("��ҩ��")) {
			fpxmbh = "03";
		} else if (fpxmmc.equalsIgnoreCase("�Һŷ�")) {
			fpxmbh = "04";
		} else if (fpxmmc.equalsIgnoreCase("��λ��")) {
			fpxmbh = "05";
		} else if (fpxmmc.equalsIgnoreCase("����")) {
			fpxmbh = "06";
		} else if (fpxmmc.equalsIgnoreCase("���Ʒ�")) {
			fpxmbh = "06";
		}else if (fpxmmc.equalsIgnoreCase("�����")) {
			fpxmbh = "07";
		} else if (fpxmmc.equalsIgnoreCase("����")) {
			fpxmbh = "08";
		} else if (fpxmmc.equalsIgnoreCase("�����")) {
			fpxmbh = "09";
		} else if (fpxmmc.equalsIgnoreCase("���Ʒ�")) {
			fpxmbh = "10";
		} else if (fpxmmc.equalsIgnoreCase("������")) {
			fpxmbh = "11";
		} else if (fpxmmc.equalsIgnoreCase("�Ȼ�����")) {
			fpxmbh = "12";
		} else if (fpxmmc.equalsIgnoreCase("������")) {
			fpxmbh = "13";
		} else if (fpxmmc.equalsIgnoreCase("MRI��")) {
			fpxmbh = "14";
		} else if (fpxmmc.equalsIgnoreCase("CT��")) {
			fpxmbh = "15";
		} else if (fpxmmc.equalsIgnoreCase("�ʳ���")) {
			fpxmbh = "16";
		} else if (fpxmmc.equalsIgnoreCase("������")) {
			fpxmbh = "17";
		} else if (fpxmmc.equalsIgnoreCase("��Ѫ��")) {
			fpxmbh = "18";
		} else if (fpxmmc.equalsIgnoreCase("�����")) {
			fpxmbh = "19";
		} else if (fpxmmc.equalsIgnoreCase("���������Ŀ��")) {
			fpxmbh = "20";
		} else if (fpxmmc.equalsIgnoreCase("����ҽ�Ʒ�")) {
			fpxmbh = "21";
		} else
			fpxmbh = fpxmmc;

		return fpxmbh;
	}

	public static void saveToImgFile(String src, String output) {
		if (src == null || src.length() == 0) {
			return;
		}
		try {
			FileOutputStream out = new FileOutputStream(new File(output));
			byte[] bytes = src.getBytes();
			for (int i = 0; i < bytes.length; i += 2) {
				out.write(charToInt(bytes[i]) * 16 + charToInt(bytes[i + 1]));
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int charToInt(byte ch) {
		int val = 0;
		if (ch >= 0x30 && ch <= 0x39) {
			val = ch - 0x30;
		} else if (ch >= 0x41 && ch <= 0x46) {
			val = ch - 0x41 + 10;
		}
		return val;
	}

	/**
	 * �����亯��
	 * 
	 * @param b
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String lpad(byte[] b, int len, char ch) {
		if (len > b.length) {
			StringBuffer sb = new StringBuffer(len);
			for (int i = 0; i < len - b.length; i++) {
				sb.append(ch);
			}
			sb.append(new String(b));
			return sb.toString();
		}
		return new String(b);
	}

	/**
	 * �����亯��
	 * 
	 * @param str
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String lpad(String str, int len, char ch) {
		return lpad(str.getBytes(), len, ch);
	}

	/**
	 * �����亯��
	 * 
	 * @param i
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String lpad(int i, int len, char ch) {
		return lpad(String.valueOf(i), len, ch);
	}

	/**
	 * �ұ���亯��
	 * 
	 * @param b
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String rpad(byte[] b, int len, char ch) {
		if (len > b.length) {
			StringBuffer sb = new StringBuffer(len);
			sb.append(new String(b));
			for (int i = 0; i < len - b.length; i++) {
				sb.append(ch);
			}
			return sb.toString();
		}
		return new String(b);
	}

	/**
	 * �ұ���亯��
	 * 
	 * @param str
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String rpad(String str, int len, char ch) {
		return rpad(str.getBytes(), len, ch);
	}

	/**
	 * �ұ���亯��
	 * 
	 * @param i
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String rpad(int i, int len, char ch) {
		return rpad(String.valueOf(i), len, ch);
	}
	
	/**
	 * ���ݳ������ڻ�ȡ����
	 * 
	 * @param csrq00
	 * @return
	 * @throws ParseException 
	 */
	public static String getAgeByBirthday(String csrq00) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date birthday = null;
		try {
			birthday = sdf.parse(csrq00);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return String.valueOf(age);
	}
}
