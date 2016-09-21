package com.smart.oo.domain.res;

import java.util.List;

public class GetFdsPriceResult {

	private String msg;
	private List<FDDataVO> datas;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FDDataVO> getDatas() {
		return datas;
	}

	public void setDatas(List<FDDataVO> datas) {
		this.datas = datas;
	}

}
