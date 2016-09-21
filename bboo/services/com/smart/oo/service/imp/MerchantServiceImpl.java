package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.entity.Merchant;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IMerchantService;

@Service("MerchantServiceImpl")
public class MerchantServiceImpl implements IMerchantService {
	
	@Autowired
	private FactoryDaoImpl factoryDaoImpl;

	@Override
	public List<Merchant> queryMerchants() throws Exception {
		return this.factoryDaoImpl.getMerchantDao().queryMerchants();
	}

	@Override
	public void updateMerchant(Merchant merchant) throws Exception {
		this.factoryDaoImpl.getMerchantDao().updateMerchant(merchant);
	}

	@Override
	public void deleteMerchant(Integer mercid) throws Exception {
		this.factoryDaoImpl.getMerchantDao().deleteMerchant(mercid);
	}

	@Override
	public void saveMerchant(Merchant merchant) throws Exception {
		this.factoryDaoImpl.getMerchantDao().saveMerchant(merchant);
	}

	@Override
	public Merchant queryByCompanyName(String companyName) throws Exception {
		return this.factoryDaoImpl.getMerchantDao().queryByCompanyName(companyName);
	}

	@Override
	public List<Merchant> query(Merchant merchant) throws Exception {
		return this.factoryDaoImpl.getMerchantDao().query(merchant);
	}

}
