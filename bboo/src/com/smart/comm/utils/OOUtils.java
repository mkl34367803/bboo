package com.smart.comm.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.smart.entity.User;
import com.smart.utils.HttpUtils;

public class OOUtils {

	public static String getUserMerchant(User u) {

		return u != null && u.getMert() != null ? u.getMert().getMno() : "-1";
	}

	public static String exceptionToString(Throwable e) {
		PrintWriter writer = null;
		StringWriter stringWriter = null;
		try {
			stringWriter = new StringWriter(1000);
			writer = new PrintWriter(stringWriter);
			e.printStackTrace(writer);
			return stringWriter.toString();
		} catch (Exception e2) {
			System.out.println("exceptionToString exception");
		} finally {
			if (null != writer) {
				writer.close();
			}
			if (null != stringWriter) {
				try {
					stringWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String getfigure(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		StringBuffer buf = new StringBuffer();
		while (matcher.find()) {
			buf.append(matcher.group(0));
		}
		String str = buf.toString();
		buf.setLength(0);
		buf = null;
		return str;
	}

	public static List<String> readFileByLine(String fileUrl)
			throws FileNotFoundException {
		FileReader fin = new FileReader(fileUrl);
		String str = null;
		List<String> list = new ArrayList<String>();
		if (fin != null) {
			Scanner scanner = new Scanner(fin);
			while (scanner.hasNextLine()) {// 如果有内容
				str = scanner.nextLine();
				list.add(str);
			}
			try {
				fin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				scanner.close();
			}
		}
		return list;
	}

	/**
	 * 获取文件路径
	 * 
	 * @param fileDir
	 * @param fileTxt
	 * @return
	 */
	public static String getFileUri(String fileDir, String fileTxt) {
		// 读文件
		String file = "";
		try {
			file = URLDecoder.decode(HttpUtils.class.getClassLoader()
					.getResource("").toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		file = file.substring(5, file.indexOf("WEB-INF")) + fileDir + "/";
		String fileName = fileTxt;
		file = file + fileName;
		return file;
	}

}
