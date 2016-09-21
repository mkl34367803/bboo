package com.smart.oo.response;

import com.bbang.api.BbangResponse;

public class CommResultResponse extends BbangResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 361933408097019040L;
	private String msg;
	private String msgcode;
	private String resjson;
	private String reqjson;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgcode() {
		return msgcode;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}

	public String getResjson() {
		return resjson;
	}

	public void setResjson(String resjson) {
		this.resjson = resjson;
	}

	public String getReqjson() {
		return reqjson;
	}

	public void setReqjson(String reqjson) {
		this.reqjson = reqjson;
	}
}
