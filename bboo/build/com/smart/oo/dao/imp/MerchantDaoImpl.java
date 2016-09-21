package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.entity.Merchant;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IMerchantDao;

@Repository("MerchantDaoImpl")
public class MerchantDaoImpl extends BaseDAO<Merchant, Integer> implements IMerchantDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1278557722860713585L;

	@Override
	public List<Merchant> queryMerchants() throws Exception {
		return this.find("from Merchant where 1=1");
	}

	@Override
	public void updateMerchant(Merchant merchant) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMerchant(Integer mercid) throws Exception {
		this.delete(mercid);
	}

	@Override
	public void saveMerchant(Merchant merchant) throws Exception {
		this.save(merchant);
	}

	@Override
	public Merchant queryByCompanyName(String companyName) throws Exception {
		return this.findUnique("from Merchant where companyName='" + companyName + "'");
	}

	@Override
	public List<Merchant> query(Merchant merchant) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from Merchant where 1=1 ");
		if (merchant.getMercid() != null) {
			hql.append(" and mercid='"+merchant.getMercid()+"'");
		}
		if (merchant.getMno() != null) {
			hql.append(" and mno='"+merchant.getMno()+"'");
		}
		if (merchant.getCompanyName() != null) {
			hql.append(" and companyName='"+merchant.getCompanyName()+"'");
		}
		if (merchant.getAddress() != null) {
			hql.append(" and address='"+merchant.getAddress()+"'");
		}
		if (merchant.getTel() != null) {
			hql.append(" and tel='"+merchant.getTel()+"'");
		}
		if (merchant.getPhone() != null) {
			hql.append(" and phone='"+merchant.getPhone()+"'");
		}
		if (merchant.getQq() != null) {
			hql.append(" and qq='"+merchant.getQq()+"'");
		}
		if (merchant.getMsn() != null) {
			hql.append(" and msn='"+merchant.getMsn()+"'");
		}
		if (merchant.getRandm() != null) {
			hql.append(" and randm='"+merchant.getRandm()+"'");
		}
		if (merchant.getAppCode() != null) {
			hql.append(" and appCode='"+merchant.getAppCode()+"'");
		}
		if (merchant.getZipCode() != null) {
			hql.append(" and zipCode='"+merchant.getZipCode()+"'");
		}
		if (merchant.getDatasrc() != null) {
			hql.append(" and datasrc='"+merchant.getDatasrc()+"'");
		}
		if (merchant.getUsernum() != null) {
			hql.append(" and usernum='"+merchant.getUsernum()+"'");
		}
		if (merchant.getCreateTime() != null) {
			hql.append(" and createTime='"+merchant.getCreateTime()+"'");
		}
		return this.find(hql.toString());
	}

}
