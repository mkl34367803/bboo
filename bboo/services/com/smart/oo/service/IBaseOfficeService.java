package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.framework.base.Page;

public interface IBaseOfficeService {

	List<BaseOfficeEntity> findDataList() throws Exception;

	List<BaseOfficeEntity> findDataList(String cacheName, String cacheKey,
			boolean iscache) throws Exception;
	public List<BaseOfficeEntity> queryBaseOffice(Page page,BaseOfficeEntity baseOfficeEntity) throws Exception;
	/**
	 * 通过ID查询office信息
	 * @return
	 * @throws Exception
	 */
	public BaseOfficeEntity queryOfficeByID(Integer id) throws Exception;
	/**
	 * 保存
	 * @param baseOfficeEntity
	 * @return
	 * @throws Exception
	 */
	public Integer saveOffice(BaseOfficeEntity baseOfficeEntity) throws Exception;
	/**
	 * 更新
	 * @param baseOfficeEntity
	 * @throws Exception
	 */
	public void updateOffice(BaseOfficeEntity baseOfficeEntity) throws Exception;
	/**
	 * 删除office信息
	 * @return
	 * @throws Exception
	 */
	public void deleteOfficeById(Integer id) throws Exception;
	/**
	 * 通过不为null的字段查询BaseOffice信息
	 * @param baseOfficeEntity
	 * @return
	 */
	public List<BaseOfficeEntity> queryByParamVo(BaseOfficeEntity paramVo) throws Exception ;
}
