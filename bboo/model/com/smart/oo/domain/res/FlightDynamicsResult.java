package com.smart.oo.domain.res;

import java.util.List;

public class FlightDynamicsResult {

	private String source;

	private List<DynamicsVO> datas;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<DynamicsVO> getDatas() {
		return datas;
	}

	public void setDatas(List<DynamicsVO> datas) {
		this.datas = datas;
	}

}
