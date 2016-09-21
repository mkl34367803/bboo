package com.smart.oo.domain.res;

import java.util.List;

public class GjOrderGetListResult {

	private List<GjOrderGetBaseVO> order;

	private String msg;

	/**
	 * 0成功 其他 失败
	 */
	private String code;

	/**
	 * false 没有下一页 true 还有下一页
	 */
	private boolean hasNext;

	public List<GjOrderGetBaseVO> getOrder() {
		return order;
	}

	public void setOrder(List<GjOrderGetBaseVO> order) {
		this.order = order;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

}
