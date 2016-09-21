package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.AfterPolicyEntity;
import com.smart.framework.base.Page;

public interface IAfterPolicyDao {

	List<AfterPolicyEntity> findAlls() throws Exception;

	List<AfterPolicyEntity> findListForPage(AfterPolicyEntity e, Page p)
			throws Exception;

	Integer saveData(AfterPolicyEntity e) throws Exception;

	void deleteByID(Integer id) throws Exception;

	Integer updateData(AfterPolicyEntity e) throws Exception;

}
