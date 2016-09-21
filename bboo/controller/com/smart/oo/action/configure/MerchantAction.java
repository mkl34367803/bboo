package com.smart.oo.action.configure;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.smart.entity.Merchant;
import com.smart.framework.base.BaseAction;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.StringUtils;

public class MerchantAction extends BaseAction {

	private static final long serialVersionUID = -8374761437532826747L;
	
	@Autowired
	private FactoryServiceImpl factoryServiceImpl;

	public void getMerchants() {
		try {
			List<Merchant> merchants = this.factoryServiceImpl.getMerchantService().queryMerchants();
			JSONObject jsonObject = new JSONObject();
			if (merchants.size() > 0) {
				String merchantsStr = parseToJson(merchants);
				jsonObject.put("merchants", merchantsStr);
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void queryMerchants() {
		try {
			String mercid = request.getParameter("mercid");
			String mno = request.getParameter("mno");
			Merchant merchant = new Merchant();
			if (StringUtils.isNotEmpty(mercid) && StringUtils.isNumeric(mercid)) {
				merchant.setMercid(Integer.parseInt(mercid));
			}
			JSONObject jsonObject = new JSONObject();
			merchant.setMno(mno);
			List<Merchant> merchants = this.factoryServiceImpl.getMerchantService().query(merchant);
			if (merchants.size() > 0) {
				String merchantsStr = parseToJson(merchants);
				jsonObject.put("merchants", merchantsStr);
				this.writeStream(jsonObject, "utf-8");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将获取对象转化为json
	 * @param options
	 * @return
	 */
	private String parseToJson(List<Merchant> merchants) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int i = 0;
		for (Merchant merchant : merchants) {;
			sb.append("{'mercid': '"+merchant.getMercid()+"',");
			sb.append("'mno': '"+merchant.getMno()+"',");
			sb.append("'companyName': '"+merchant.getCompanyName()+"',");
			sb.append("'address': '"+merchant.getAddress()+"',");
			sb.append("'tel': '"+merchant.getTel()+"',");
			sb.append("'phone': '"+merchant.getPhone()+"',");
			sb.append("'qq': '"+merchant.getQq()+"',");
			sb.append("'msn': '"+merchant.getMsn()+"',");
			sb.append("'randm': '"+merchant.getRandm()+"',");
			sb.append("'appCode': '"+merchant.getAppCode()+"',");
			sb.append("'zipCode': '"+merchant.getZipCode()+"',");
			sb.append("'datasrc': '"+merchant.getDatasrc()+"',");
			sb.append("'usernum': '"+merchant.getUsernum()+"',");
			sb.append("'createTime': '"+merchant.getCreateTime()+"'}");
			if(i < merchants.size() -1) {
				sb.append(",");
			}
			i++;
		}
		sb.append("]");
		return sb.toString();
	}
}
