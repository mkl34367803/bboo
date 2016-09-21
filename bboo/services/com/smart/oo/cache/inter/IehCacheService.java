package com.smart.oo.cache.inter;

import java.util.List;
import java.util.Map;

public interface IehCacheService<T> {

	public T getInCache(String cachename, String key) throws Exception;

	public List<T> getInCaches(String cachename, String key) throws Exception;

	Map<String, List<T>> getMapInCaches(String cachename, String key)
			throws Exception;

	public void setInCache(String cachename, String key, T object)
			throws Exception;

	public void setInCaches(String cachename, String key, List<T> objects)
			throws Exception;

	void setInCaches(String cachename, String key, Map<String, List<T>> objects)
			throws Exception;

	public void delCaches(String cachename) throws Exception;

	public void delCache(String cachename, String key) throws Exception;

	public boolean checkKey(String cachename, String key) throws Exception;
}
