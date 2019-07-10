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
 * 这个工具类主要用来取参和验证参数
 * 
 * @version 1.0
 * @author 刘荣福
 * @update 2011-6-10 上午11:43:24
 */
public class StringUtil {



	/**
	 * 当前时间是否早于指定时间
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
	 * 用于运行的时候获取系统当前时间 ,指定格式
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
	 * 是否当前时段
	 * 
	 */
	public static boolean isCurrentPeriod(String time, String pattern) {
		try {
			GregorianCalendar gcalendar = new GregorianCalendar();

			int nowampm = gcalendar.get(GregorianCalendar.AM_PM);
			DateFormat df = new SimpleDateFormat(pattern);// 日期格式控制

			Date giventime = df.parse(time);
			gcalendar.setTime(giventime);
			int timeampm = gcalendar.get(GregorianCalendar.AM_PM);
			if (nowampm == timeampm) {
				System.out.println("指定时间是当前时段！");
				return true;
			} else {
				System.out.println("指定时间非当前时段！");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 字符串日期的格式转化
	 * 
	 * @param dateStr
	 *            需要转换的日期字符串
	 * @param pattern0
	 *            日期的原本格式
	 * @param pattern1
	 *            日期的要转换的格式
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
	 * getSystemDateTime 获取系统日期时间
	 * 
	 * @param dateStr
	 * @param pattern0
	 * @param pattern1
	 * @return
	 * @throws ParseException
	 */
	/**
	 * 获取当前日期和时间 格式"yyyy-MM-dd HH:mm:ss"
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
	 * 字符串日期的格式转化
	 * 
	 * @param dateStr
	 *            需要转换的日期字符串
	 * @param pattern0
	 *            日期的原本格式
	 * @param pattern1
	 *            日期的要转换的格式
	 * @return
	 * @throws ParseException
	 */
	public static String currentTimeMillis2String(long time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new java.util.Date(time));

	}

	/**
	 * 耗时多少秒（有精确到毫秒）
	 * 
	 */
	public static String consumeTime2String(long starttime, long endtime) {

		return (endtime - starttime) / 1000.00 + "秒";
	}

	/**
	 * 格式化日期,任意格式,返回String型,即可获取固定日期进行格式化后的日期,也可获取系统当前日期格式化后的日期 日期格式yyyy-MM-dd
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
	 * 获取昨天的日期（YYYY-MM-DD）
	 * 
	 * @return
	 */
	public static String getBeforeday(int days, String format) {

		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, -days); // 得到前一天
		String beforeday = new SimpleDateFormat(format)
				.format(calendar.getTime());
		// System.out.println(yestedayDate);
		return beforeday;
	}
	
	public static String getTomorrowday(int days, String format) {

		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, days); // 前一天
		String beforeday = new SimpleDateFormat(format)
				.format(calendar.getTime());
		// System.out.println(yestedayDate);
		return beforeday;
	}

	/**
	 * 获取当前日期 格式"yyyyMMdd"
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
	 * 获取当前日期 格式"yyyy-MM-dd"
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
	 * 获取当前时间 格式"HH:mm:ss"
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
	 * 用于运行的时候获取系统当前时间（精确到毫秒）
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
	 * 获取昨天的日期（YYYY-MM-DD）
	 * 
	 * @return
	 */
	public static String getYesteday() {

		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, -1); // 得到前一天
		String yestedayDate = new SimpleDateFormat("yyyy-MM-dd")
				.format(calendar.getTime());
		// System.out.println(yestedayDate);
		return yestedayDate;
	}
	/**
	 * HIS发票项目编号->医保项目编号(弘扬)
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
			yb_fpxmbh = "01";//西药费
			yb_fpxmmc = "西药费";
		} 
		else if (his_fpxmbh.equals("19")) {//19
			yb_fpxmbh = "02";//成药费
			yb_fpxmmc = "成药费";
		} 
		else if (his_fpxmbh.equals("21")) {//21
			yb_fpxmbh = "03";//草药费
			yb_fpxmmc = "草药费";
		} 
		else if (his_fpxmbh.equals("18")) {//18
			yb_fpxmbh = "04";//挂号费
			yb_fpxmmc = "挂号费";
		} 
		else if (his_fpxmbh.equals("10")) {//10
			yb_fpxmbh = "05";//床位费
			yb_fpxmmc = "床位费";
		} 
		else if (his_fpxmbh.equals("12")) {//12
			yb_fpxmbh = "06";//诊察费
			yb_fpxmmc = "诊察费";
		} 
		else if (his_fpxmbh.equals("06")) {//06
			yb_fpxmbh = "07";//护理费
			yb_fpxmmc = "护理费";
		} 
		else if (his_fpxmbh.equals("04")) {//04
			yb_fpxmbh = "08";//检查费
			yb_fpxmmc = "检查费";
		} 
		else if (his_fpxmbh.equals("01")) {//01
			yb_fpxmbh = "09";//化验费
			yb_fpxmmc = "化验费";
		} 
		else if (his_fpxmbh.equals("02")) {//02
			yb_fpxmbh = "10";//治疗费
			yb_fpxmmc = "治疗费";
		} 
		else if (his_fpxmbh.equals("11")) {//11
			yb_fpxmbh = "11";//手术费
			yb_fpxmmc = "手术费";
		} 
		else if (his_fpxmbh.equals("16")) {//16
			yb_fpxmbh = "12";//救护车费
			yb_fpxmmc = "救护车费";
		} 
		else if (his_fpxmbh.equals("13")) {//13
			yb_fpxmbh = "13";//其他费
			yb_fpxmmc = "其他费";
		} 
		else if (his_fpxmbh.equals("05")) {//05
			yb_fpxmbh = "14";//MRI费
			yb_fpxmmc = "MRI费";
		} 
		else if (his_fpxmbh.equals("15")) {//15
			yb_fpxmbh = "15";//CT费
			yb_fpxmmc = "CT费";
		} 
		else if (his_fpxmbh.equals("17")) {//17
			yb_fpxmbh = "16";//彩超费
			yb_fpxmmc = "彩超费";
		} 
		else if (his_fpxmbh.equals("14")) {//14
			yb_fpxmbh = "17";//输氧费
			yb_fpxmmc = "输氧费";
		} 
		else if (his_fpxmbh.equals("07")) {//07
			yb_fpxmbh = "18";//输血费
			yb_fpxmmc = "输血费";
		} 
		else if (his_fpxmbh.equals("08")) {//08
			yb_fpxmbh = "19";//麻醉费
			yb_fpxmmc = "麻醉费";
		} 
		else if (his_fpxmbh.equals("09")) {//09
			yb_fpxmbh = "20";//麻醉相关项目费
			yb_fpxmmc = "麻醉相关项目费";
		} 
		else if (his_fpxmbh.equals("03")) {//03
			yb_fpxmbh = "21";//其他医疗费
			yb_fpxmmc = "其他医疗费";
		} 
		else
		{
			yb_fpxmbh = "00";
			yb_fpxmmc = "无";
		}

		if(flag.equals("N"))//N=>发票项目编号
			value = yb_fpxmbh;
		else if(flag.equals("C"))//C=>发票项目名称
			value = yb_fpxmmc;
		
		return value;
	}

	/**
	 * 发票项目名称转发票项目编号
	 * 
	 * @param fpxmmc
	 * @return
	 * @author zhou
	 * @version V1.0.1
	 * @date 2012-6-16
	 */
	public static String fpxmmcTOfpxmbh(String fpxmmc) {
		String fpxmbh = "";
		if (fpxmmc.equalsIgnoreCase("西药费")) {
			fpxmbh = "01";
		} else if (fpxmmc.equalsIgnoreCase("成药费")) {
			fpxmbh = "02";
		} else if (fpxmmc.equalsIgnoreCase("草药费")) {
			fpxmbh = "03";
		} else if (fpxmmc.equalsIgnoreCase("挂号费")) {
			fpxmbh = "04";
		} else if (fpxmmc.equalsIgnoreCase("床位费")) {
			fpxmbh = "05";
		} else if (fpxmmc.equalsIgnoreCase("诊察费")) {
			fpxmbh = "06";
		} else if (fpxmmc.equalsIgnoreCase("诊疗费")) {
			fpxmbh = "06";
		}else if (fpxmmc.equalsIgnoreCase("护理费")) {
			fpxmbh = "07";
		} else if (fpxmmc.equalsIgnoreCase("检查费")) {
			fpxmbh = "08";
		} else if (fpxmmc.equalsIgnoreCase("化验费")) {
			fpxmbh = "09";
		} else if (fpxmmc.equalsIgnoreCase("治疗费")) {
			fpxmbh = "10";
		} else if (fpxmmc.equalsIgnoreCase("手术费")) {
			fpxmbh = "11";
		} else if (fpxmmc.equalsIgnoreCase("救护车费")) {
			fpxmbh = "12";
		} else if (fpxmmc.equalsIgnoreCase("其他费")) {
			fpxmbh = "13";
		} else if (fpxmmc.equalsIgnoreCase("MRI费")) {
			fpxmbh = "14";
		} else if (fpxmmc.equalsIgnoreCase("CT费")) {
			fpxmbh = "15";
		} else if (fpxmmc.equalsIgnoreCase("彩超费")) {
			fpxmbh = "16";
		} else if (fpxmmc.equalsIgnoreCase("输氧费")) {
			fpxmbh = "17";
		} else if (fpxmmc.equalsIgnoreCase("输血费")) {
			fpxmbh = "18";
		} else if (fpxmmc.equalsIgnoreCase("麻醉费")) {
			fpxmbh = "19";
		} else if (fpxmmc.equalsIgnoreCase("麻醉相关项目费")) {
			fpxmbh = "20";
		} else if (fpxmmc.equalsIgnoreCase("其他医疗费")) {
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
	 * 左边填充函数
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
	 * 左边填充函数
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
	 * 左边填充函数
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
	 * 右边填充函数
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
	 * 右边填充函数
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
	 * 右边填充函数
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
	 * 根据出生日期获取年龄
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
