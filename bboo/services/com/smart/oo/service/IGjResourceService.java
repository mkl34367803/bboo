package com.smart.oo.service;

import java.util.List;

import com.smart.entity.Resource;
import com.smart.framework.base.Page;
import com.smart.oo.from.ResourceVo;

public interface IGjResourceService {
public Integer saveResourse(Resource entity) throws Exception;
	
	public List<Resource> queryResource(Page page,ResourceVo resourceParam) throws Exception;
	
	public List<Resource> queryAllResource(Page page,ResourceVo resourceParam) throws Exception;
	
	public Integer updateResource(Resource entity) throws Exception;
	
	public Integer deleteResourceByBatch(List<String> ids) throws Exception;
	
	public Resource getResourceByID(Integer integer) throws Exception;
	/**
	 * 获取所有父级菜单
	 * @return
	 * @throws Exception
	 */
	public  List<Resource> getParentResource() throws Exception;
}
