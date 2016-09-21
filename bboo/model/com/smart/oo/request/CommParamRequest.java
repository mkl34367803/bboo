package com.smart.oo.request;

import java.util.Map;

import com.bbang.api.ApiRuleException;
import com.bbang.api.BbangRequest;
import com.bbang.api.internal.util.BbangHashMap;
import com.smart.oo.response.CommResultResponse;

public class CommParamRequest implements BbangRequest<CommResultResponse> {

	private Map<String, String> headerMap = new BbangHashMap();

	private BbangHashMap udfParams; // add user-defined text parameters

	private Long timestamp;

	private String json;// domain参数

	private String sgn;// MD5(json+user+jmkey+serviceName+当天日期)

	private String user;

	private String pwd;

	private String jmkey;

	private String serviceName;// 服务名称

	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "com.smart.ot.y.api." + this.serviceName;
	}

	public Map<String, String> getTextParams() {
		BbangHashMap txtParams = new BbangHashMap();
		txtParams.put("json", this.json);
		txtParams.put("sgn", this.sgn);
		txtParams.put("user", this.user);
		txtParams.put("pwd", this.pwd);
		txtParams.put("jmkey", this.jmkey);
		txtParams.put("serviceName", this.serviceName);
		if (this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public void putOtherTextParam(String key, String value) {
		if (this.udfParams == null) {
			this.udfParams = new BbangHashMap();
		}
		this.udfParams.put(key, value);
	}

	public Class<CommResultResponse> getResponseClass() {
		return CommResultResponse.class;
	}

	public void check() throws ApiRuleException {
	}

	public Map<String, String> getHeaderMap() {
		headerMap.put("Content-Type", "binary/octet-stream");
		return headerMap;
	}

	public BbangHashMap getUdfParams() {
		return udfParams;
	}

	public void setUdfParams(BbangHashMap udfParams) {
		this.udfParams = udfParams;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getSgn() {
		return sgn;
	}

	public void setSgn(String sgn) {
		this.sgn = sgn;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getJmkey() {
		return jmkey;
	}

	public void setJmkey(String jmkey) {
		this.jmkey = jmkey;
	}

	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String getStrParams() {
		// TODO Auto-generated method stub
		return null;
	}

}
