package com.smart.comm.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;

import com.smart.entity.User;
import com.smart.framework.security.SecurityContext;
import com.smart.utils.DateUtils;
import com.smart.utils.UniqId;

public class StringUtilsc {

	
	public static String getDBID() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		String str = sdf.format(date);
		String strId = str + ("" + Math.random()).substring(2, 12);
		return strId;
	}

	
	public static String getMasch(String reg, String sourse) {
		Pattern pat = Pattern.compile(reg);
		Matcher math = pat.matcher(sourse);
		if (math.find()) {
			return math.group();
		}
		return "";
	}

	public static int getLength(String str) {
		return str.replaceAll("[^\\x00-\\xff]", "**").length();
	}

	public static String getByteString(String str, int length, int... s) {
		if (str == null)
			return "";
		int i = 0;
		int charlength = 2;
		if (s.length == 1)
			charlength = s[0];
		StringBuffer sbf = new StringBuffer();
		for (int t = 0; t < str.length(); t++) {
			char ca = str.charAt(t);
			if (ca < 0xff && ca > 0) {
				i++;
			} else {
				i += charlength;
			}
			if (i <= length) {
				sbf.append(ca);
			}
		}
		return sbf.toString();
	}

	public static String getString(Object obj) {
		return obj != null ? obj.toString() : null;
	}

	public static Integer getInteger(Object obj) {
		return obj != null ? Integer.parseInt(obj.toString()) : null;
	}

	public static String getRandId() {
		return UniqId.getInstance().getStrId()
				+ RandomStringUtils.random(5, false, true);
	}

	public static int compter(String str1, String str2) {
		Date date1 = DateUtils.parseDate(str1);
		Date date2 = DateUtils.parseDate(str2);
		return DateUtils.countTime(date1, date2) / 60 / 24;
	}

	/**
	 * 说明 ：判断集合是否为空，和集合里的数字是否存在空值或空字符串 参数 ： 返回值：
	 */
	public static <T> boolean isEmptyList(List<T[]> list) {
		if (null == list || list.size() == 0)
			return true;
		for (T[] o : list) {
			return isEmptyArray(o);
		}
		return false;
	}

	/**
	 * 说明 ：判断数组是否为空是否存在空值或空字符串 参数 ： 返回值：
	 */
	public static <T> boolean isEmptyArray(T[] list) {
		if (null == list || list.length == 0) {
			return true;
		}
		for (T t : list) {
			if (null == t || t.toString().equals("")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 说明 ：判断集合是否为空，和集合里的数字是否存在空值或空字符串 参数 ： 返回值：
	 */
	public static <T> void removeEmptyArray(List<T[]> list) {
		if (null == list || list.size() == 0)
			return;
		for (int i = 0; i < list.size(); i++) {
			if (isEmptyArray(list.get(i))) {
				list.remove(i);
				i--;
			}
			;
		}
		return;
	}

	/**
	 * 说明 ：验证价格 json 数据是否有花括号包围
	 */
	public static boolean isJson(String str) {
		if (null != str && str.startsWith("{") && str.endsWith("}")) {
			return true;
		}
		return false;
	}

	/**
	 * 说明 ：验证价格 任意个小数，开头可以为加减号
	 */
	public static boolean isPrice(String str) {
		return regex(str, "^(\\-|\\+)?\\d+(\\.\\d+)?$");
	}

	/**
	 * 说明 ：验证数字
	 */
	public static boolean isNumeric(String str) {
		return regex(str, "[0-9]*");
	}

	/**
	 * 说明 ： 验证是否日期 只能 20开头的年份 如2014 中间以 减号分割 月份为1-12 天为1-31
	 */
	public static boolean isDate(String str) {
		return regex(str,
				"^20\\d{2}-((0?[1-9])|1[0-2])-((0?[1-9])|([1-2][0-9])|(3[0-1]))$");
	}

	/**
	 * 说明 ： 验证是否时间
	 */
	public static boolean isTime(String str) {
		return regex(str,
				"^((0?[0-9])|(1[0-9])|(2[0-3])):((0?[0-9])|([1-5][0-9]))$");
	}

	/**
	 * 将list划分为等分的集合
	 * 
	 * @param sourse
	 *            数据源
	 * @param args
	 *            第一个参数 数量（ 默认1000）
	 * @return 按指定数量等分，
	 */
	public static <T> List<List<T>> getEachList(List<T> sourse, int... args) {
		List<List<T>> eachlists = new ArrayList<List<T>>();
		if (isNotEmptyListorMap(sourse)) {
			int totalCount = sourse.size();
			int pageSize = 1000;
			int pageCount = 10;
			if (null != args && args.length > 0 && args[0] > 1) {
				pageSize = args[0];
			}
			pageCount = totalCount % pageSize == 0 ? totalCount / pageSize
					: totalCount / pageSize + 1;
			for (int i = 0; i < pageCount;) {
				List<T> tp = null;
				if (i == pageCount - 1) {
					tp = sourse.subList(i * pageSize, totalCount);
					eachlists.add(tp);
					break;
				}
				tp = sourse.subList(i * pageSize, ++i * pageSize);
				eachlists.add(tp);
			}
		}
		return eachlists;
	}

	/**
	 * 说明 ：用给定的正则表达式验证 参数 ： 返回值：
	 */
	public static boolean regex(String str, String express) {
		if (str != null) {
			Pattern p = Pattern.compile(express);
			Matcher m = p.matcher(str);
			return m.find();
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T copyObject(T obj) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream ops = null;
		ObjectInputStream ois = null;
		try {
			ops = new ObjectOutputStream(bos);
			ops.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		try {
			ois = new ObjectInputStream(bis);
			return ((T) ois.readObject());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ops != null) {
				try {
					ops.flush();
					ops.close();
					if (bos != null) {
						bos.flush();
						bos.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
					if (bis != null)
						bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	// public static String getMno(User user) {
	// String mno = "";
	// try {
	// mno=CommUtil.getMerchantNumber(user);
	// if(mno==null){
	// mno = SecurityUtils.getMD5("123456chenwupeng");
	// }
	// } catch (Exception e) {
	// mno = SecurityUtils.getMD5("123456chenwupeng");
	// }
	// return mno;
	// }
	public static String getUserName(User user) {
		String name = "";
		try {
			name = user.getName();
		} catch (Exception e) {
			name = "陈吴鹏";
		}
		return name;
	}

	// public static String getMno() {
	// String mno = "";
	// try {
	// mno=CommUtil.getMerchantNumber(SecurityContext.getUser());
	// if(mno==null){
	// mno = SecurityUtils.getMD5("123456chenwupeng");
	// }
	// } catch (Exception e) {
	// mno = SecurityUtils.getMD5("123456chenwupeng");
	// }
	// return mno;
	// }
	public static User getUser() {
		User user = null;
		try {
			user = SecurityContext.getUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public static String getUserName() {
		String name = "";
		try {
			name = SecurityContext.getUser().getName();
		} catch (Exception e) {
			name = "陈吴鹏";
		}
		return name;
	}

	public static String getUserLoginName() {
		String name = "";
		try {
			name = SecurityContext.getUser().getLoginName();
		} catch (Exception e) {
			name = "chenwp";
		}
		return name;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param <T>
	 * 
	 * @param list或map
	 * @return
	 */
	public static <T> boolean isNotEmptyListorMap(T list) {
		if (null != list) {
			if (list instanceof Map && ((Map<?, ?>) list).size() > 0) {
				return true;
			} else if (list instanceof List && ((List<?>) list).size() > 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean isIp(String str) {
		// return regex(str,
		// "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
		return regex(
				str,
				"^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$");
	}

	public static boolean isNotEmptyAndNULL(String str) {
		if ("null".equalsIgnoreCase(str) || isEmpty(str)) {
			return false;
		}
		return true;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str.replace(" ", "")))
			return true;
		return false;
	}

	public static boolean isYourIP(String str) {
		return str == null ? false : str.startsWith("your ip:");
	}

	/**
	 * 字符串数字获取
	 * 
	 * @param str
	 * @return
	 */
	public static String getMathByString(String str) {
		String str2 = "";
		if (str != null && !"".equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
					str2 += str.charAt(i);
				}
			}
		}
		return str2;
	}

	public static void ss(String[] args) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
	}
	
	/**
	 * 保留2为小数，四舍五入
	 * @param d
	 * @return
	 */
	public static Double formatDouble2(Double d) {
		BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.HALF_UP);
		return bg.doubleValue();
	}
	
	public static String formatDoubleStr(double d) {
        return String.format("%.2f", d);
    }
	
	public static String formatDoubleStr(String str) {
		double d = Double.parseDouble(str);
		return String.format("%.2f", d);
	}
	
	/**
	 * 判断是否有中文
	 * @param str
	 * @return
	 */
	public static boolean isContainChinese(String str) {
		boolean temp = false;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			temp = true;
		}
		return temp;
	}
	
}
