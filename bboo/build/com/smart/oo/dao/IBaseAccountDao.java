package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.framework.base.Page;

public interface IBaseAccountDao {
	/**
	 * 根据条件分页查询账户
	 * 
	 * @param page
	 * @param baseAccountEntity
	 * @return
	 * @throws Exception
	 */
	public List<BaseAccountEntity> queryBaseAccount(Page page,
			BaseAccountEntity baseAccountEntity) throws Exception;
	/**
	 * 将数据库中的account信息全部查询出来，不带任何条件的查询。
	 * 如何需要筛选数据，请注意不要引用返回的list，因为该返回值是个缓存值，
	 * 供很多方法调用
	 * @return
	 * @throws Exception
	 */
	List<BaseAccountEntity> queryBaseAccounts() throws Exception ;
	/**
	 * 根据条件查询账户的总记录数
	 * 
	 * @param baseAccountEntity
	 * @return
	 * @throws Exception
	 */
	public Integer queryCountAccount(BaseAccountEntity baseAccountEntity)
			throws Exception;

	/**
	 * 新增或者修改账户信息
	 * 
	 * @param baseAccountEntity
	 * @return
	 * @throws Exception
	 */
	public Integer saveAndUpdateBaseAccount(BaseAccountEntity baseAccountEntity)
			throws Exception;
	/**
	 * 通过商户号，查询该商户号的所有分销账号信息。
	 * @param mno
	 * @return
	 */
	public List<BaseAccountEntity> queryBaseAccountByMno(String mno) throws Exception;
	/**
	 * 通过id查询基本账号信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BaseAccountEntity queryBaseAccountByID(Integer id) throws Exception;
}
