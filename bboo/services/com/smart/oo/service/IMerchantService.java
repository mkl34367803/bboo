package com.smart.oo.service;

import java.util.List;

import com.smart.entity.Merchant;

public interface IMerchantService {

	/**
	 * 查询所有Merchant
	 * @return
	 * @throws Exception
	 */
	List<Merchant> queryMerchants() throws Exception;
	
	/**
	 * 更新
	 * @param merchant
	 * @throws Exception
	 */
	void updateMerchant(Merchant merchant) throws Exception;
	
	/**
	 * 删除
	 * @param mercid
	 * @throws Exception
	 */
	void deleteMerchant(Integer mercid) throws Exception;
	
	/**
	 * 保存
	 * @param cMerchant
	 * @throws Exception
	 */
	void saveMerchant(Merchant merchant) throws Exception;
	
	/**
	 * 通过公司名称查询
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	Merchant queryByCompanyName(String companyName) throws Exception;
	
	/**
	 * 通过条件查找
	 * @param merchant
	 * @return
	 * @throws Exception
	 */
	List<Merchant> query(Merchant merchant) throws Exception;
}
