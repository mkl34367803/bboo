package com.smart.oo.request;

public class HttpVo {

	private String url;
	private String contentType;
	private String encode;
	private String methods; // POST GET
	private long timeout = 10 * 1000;
	/**
	 * 跳转 方法  PUB_QUNAR_POLICYS
	 */
	private String taget;
	private String data;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTaget() {
		return taget;
	}

	public void setTaget(String taget) {
		this.taget = taget;
	}

}
