package com.smart.oo.domain.res;

import com.smart.utils.StringUtils;

public class RtPnrPriceVO implements Comparable<RtPnrPriceVO> {

	private String fare = "0";
	private String tax = "0";
	private String yq = "0";

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getYq() {
		return yq;
	}

	public void setYq(String yq) {
		this.yq = yq;
	}

	private int bj(RtPnrPriceVO o) {
		int comInt = 0;
		if (StringUtils.isNotEmpty(this.fare)
				&& StringUtils.isNotEmpty(o.getFare())) {
			if (Float.parseFloat(this.fare) - Float.parseFloat(o.getFare()) > 0) {
				comInt = (int) (Float.parseFloat(this.fare) - Float
						.parseFloat(o.getFare())) + 1;
			} else {
				comInt = (int) (Float.parseFloat(this.fare) - Float
						.parseFloat(o.getFare())) - 1;
			}
		}
		return comInt;
	}

	@Override
	public int compareTo(RtPnrPriceVO o) {
		// TODO Auto-generated method stub
		int comInt = 0;
		comInt = this.bj(o);
		return (comInt != 0 ? this.bj(o) : comInt);
	}
}
