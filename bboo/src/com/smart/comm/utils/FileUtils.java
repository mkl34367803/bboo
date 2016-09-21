package com.smart.comm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;

import com.smart.utils.HttpUtils;
import com.smart.utils.ZipFileUtil;

public class FileUtils {

	public static void writeHeader(String fileUrl, String... heads) {
		StringBuffer headerbuf = new StringBuffer();
		for (String h : heads) {
			headerbuf.append(h + "	");
		}
		headerbuf.append("\r\n");
		xiefile(headerbuf, fileUrl);
	}

	public static void writeBody(String fileUrl, String... bodys) {
		StringBuffer bodybuf = new StringBuffer();
		for (String b : bodys) {
			bodybuf.append(b + "	");
		}
		bodybuf.append("\r\n");
		xiefile(bodybuf, fileUrl);
	}

	public static void writeBody(StringBuffer bodybuf, String... bodys) {
		for (String b : bodys) {
			bodybuf.append(b + "	");
		}
		bodybuf.append("\r\n");
	}

	public static StringBuffer xiefile(StringBuffer sb, String filename) {
		BufferedWriter bw = null;
		OutputStream os = null;
		try {

			if (sb != null) {
				os = new FileOutputStream(filename, true);
				Writer wd = new OutputStreamWriter(os, "utf-8");
				bw = new BufferedWriter(wd);
				bw.write(sb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.flush();
					bw.close();
				}
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (IOException e1) {
				bw = null;
				os = null;
				e1.printStackTrace();
			}
		}
		sb = null;
		sb = new StringBuffer();
		return sb;
	}

	public static String writeEnd(String fileUrl) {
		String zipFile = ZipFileUtil.compressGzipFile(fileUrl);
		File f = new File(fileUrl);
		f.delete();
		return zipFile;
	}

	public static String getFilePath(String fileDir, String aircode) {
		String file = "";
		try {
			file = URLDecoder.decode(HttpUtils.class.getClassLoader()
					.getResource("").toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		file = file.substring(5, file.indexOf("WEB-INF")) + fileDir + "/";
		String fileName = aircode + "_" + "$" + System.currentTimeMillis()
				+ ".txt";
		file = file + fileName;
		return file;
	}

	public static String getJspContents(String dir, String jsp, String code) {
		String file = "";
		try {
			file = URLDecoder.decode(HttpUtils.class.getClassLoader()
					.getResource("").toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		file = file.substring(5, file.indexOf("WEB-INF")) + dir + "/";
		String fileName = jsp;
		file = file + fileName;
		return com.smart.utils.IOUtils.readFileByLines(file, code);
	}
}
