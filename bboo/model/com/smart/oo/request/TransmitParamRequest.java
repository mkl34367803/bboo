package com.smart.oo.request;

import java.util.Map;

import com.bbang.api.ApiRuleException;
import com.bbang.api.BbangRequest;
import com.bbang.api.internal.util.BbangHashMap;
import com.smart.oo.response.TransmitParamResponse;

public class TransmitParamRequest implements
		BbangRequest<TransmitParamResponse> {

	private Map<String, String> header = new BbangHashMap();
	private BbangHashMap udfParams; // add user-defined text parameters
	private String serviceName;// 服务名称
	private String text;// STR 参数

	@Override
	public void check() throws ApiRuleException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getApiMethodName() {
		// TODO Auto-generated method stub
		return "com.smart.ot.y.api." + this.serviceName;
	}

	@Override
	public Map<String, String> getHeaderMap() {
		// TODO Auto-generated method stub
		return getHeader();
	}

	@Override
	public Class<TransmitParamResponse> getResponseClass() {
		// TODO Auto-generated method stub
		return TransmitParamResponse.class;
	}

	@Override
	public Map<String, String> getTextParams() {
		// TODO Auto-generated method stub

		BbangHashMap txtParams = new BbangHashMap();
		txtParams.put("serviceName", this.serviceName);
		if (this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;

	}

	@Override
	public Long getTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putOtherTextParam(String key, String value) {
		// TODO Auto-generated method stub
		if (this.udfParams == null) {
			this.udfParams = new BbangHashMap();
		}
		this.udfParams.put(key, value);
	}

	@Override
	public void setTimestamp(Long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getStrParams() {
		// TODO Auto-generated method stub
		return getText();
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

}
