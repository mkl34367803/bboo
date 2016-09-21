package com.smart.oo.domain.res;

import java.util.List;

public class TfareDetrResult {

	private List<DetrDetail> detr;
	private String txt;
	private Integer state;// 0 失败 1成功

	public List<DetrDetail> getDetr() {
		return detr;
	}

	public void setDetr(List<DetrDetail> detr) {
		this.detr = detr;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
