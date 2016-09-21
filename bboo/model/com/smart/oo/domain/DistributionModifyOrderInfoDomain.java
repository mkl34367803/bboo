package com.smart.oo.domain;

/**
 * 回填客票
 * 
 * @author eric
 * 
 */
public class DistributionModifyOrderInfoDomain {

	private GjOrderAccountVO account;
	/**
	 * 代理商id
	 */
	private String agentId;

	private IeIssueTicketVO order;
	/**
	 * N 国内  I国际 默认 国际
	 */
	private String orderType; 
	public GjOrderAccountVO getAccount() {
		return account;
	}

	public void setAccount(GjOrderAccountVO account) {
		this.account = account;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public IeIssueTicketVO getOrder() {
		return order;
	}

	public void setOrder(IeIssueTicketVO order) {
		this.order = order;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
