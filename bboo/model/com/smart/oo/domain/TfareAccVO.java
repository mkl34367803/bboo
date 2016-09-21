package com.smart.oo.domain;

public class TfareAccVO {

	private String userAct;// 用户账号(必填项)
	private String userPwd;// 用户密码(必填项)
	private String userKey;// 校验码(必填项)

	public String getUserAct() {
		return userAct;
	}

	public void setUserAct(String userAct) {
		this.userAct = userAct;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

}
