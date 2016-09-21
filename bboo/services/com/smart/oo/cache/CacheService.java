package com.smart.oo.cache;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.smart.oo.cache.inter.IehCacheService;

public class CacheService<T> implements IehCacheService<T> {

	private static final Logger log = Logger.getLogger(CacheService.class);

	@SuppressWarnings("unchecked")
	@Override
	public T getInCache(String cachename, String key) throws Exception {
		// TODO Auto-generated method stub
		// 直接调用辅助方法，返回结果
		return (T) EhCacheProvider.getObjectInCache(cachename, key);
	}

	@Override
	public void setInCache(String cachename, String key, T object)
			throws Exception {
		// TODO Auto-generated method stub
		// 调用辅助方法，放入缓存
		EhCacheProvider.setObjectInCache(cachename, key, object);
	}

	@Override
	public void delCaches(String cachename) throws Exception {
		// TODO Auto-generated method stub
		// 调用辅助方法，清空缓存数据
		EhCacheProvider.delCaches(cachename);
		log.info("清空名称为[" + cachename + "]的缓存");
	}

	@Override
	public boolean checkKey(String cachename, String key) throws Exception {
		// TODO Auto-generated method stub
		return EhCacheProvider.checkKey(cachename, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getInCaches(String cachename, String key) throws Exception {
		// TODO Auto-generated method stub
		return (List<T>) EhCacheProvider.getObjectInCache(cachename, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<T>> getMapInCaches(String cachename, String key)
			throws Exception {
		// TODO Auto-generated method stub
		return (Map<String, List<T>>) EhCacheProvider.getObjectInCache(
				cachename, key);
	}

	@Override
	public void setInCaches(String cachename, String key, List<T> objects)
			throws Exception {
		// TODO Auto-generated method stub
		EhCacheProvider.setObjectInCache(cachename, key, objects);
	}

	@Override
	public void setInCaches(String cachename, String key,
			Map<String, List<T>> objects) throws Exception {
		// TODO Auto-generated method stub
		EhCacheProvider.setObjectInCache(cachename, key, objects);
	}

	@Override
	public void delCache(String cachename, String key) throws Exception {
		// TODO Auto-generated method stub
		EhCacheProvider.delCache(cachename, key);
		log.info("清空名称为[" + cachename + "]的缓存");
	}

}
