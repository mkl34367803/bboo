package com.smart.oo.service;

import java.util.List;
import java.util.Map;

import com.smart.comm.entity.AfterPolicyEntity;
import com.smart.framework.base.Page;

public interface IAfterPolicyService {

	List<AfterPolicyEntity> findDataList(String cacheKey, boolean iscache)
			throws Exception;

	Map<String, List<AfterPolicyEntity>> findDataMap(String cacheKey,
			boolean iscache) throws Exception;

	List<AfterPolicyEntity> findListForPage(AfterPolicyEntity e, Page p)
			throws Exception;

	Integer saveData(AfterPolicyEntity e) throws Exception;

	void deleteByID(Integer id) throws Exception;

	Integer updateData(AfterPolicyEntity e) throws Exception;
}
