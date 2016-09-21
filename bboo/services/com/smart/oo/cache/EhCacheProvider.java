package com.smart.oo.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EhCacheProvider {

	private static ClassPathXmlApplicationContext ctxt;

	private static final String CACHE_XML = "/cacheContext.xml";

	// private static URL url;
	//
	// private static CacheManager manager;

	private final static Logger logger = Logger
			.getLogger(EhCacheProvider.class);

	static {
		try {
			ctxt = new ClassPathXmlApplicationContext(CACHE_XML);
			// url = EhCacheProvider.class.getResource(CACHE_XML);
			// manager = CacheManager.create(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Cache getCache(String cacheName) throws Exception {

		// Cache c = manager.getCache(cacheName);
		// System.out.println(c);
		Cache c = (Cache) ctxt.getBean(cacheName);
		System.out.println(c);
		return c;
	}

	public static Object getObjectInCache(String cacheName, String key)
			throws Exception {
		Cache ch = getCache(cacheName);
		if (null != ch) {
			logger.info("缓存[" + cacheName + "]中的对象数量为= " + ch.getSize());
		}
		Element ele = ch.get(key);
		if (ele == null) {
			logger.error("缓存[" + cacheName + "]中key为[" + key + "]的对象不存在");
			return null;
		} else {
			return ele.getObjectValue();
		}
	}

	public static void setObjectInCache(String cacheName, String key,
			Object value) throws Exception {
		Cache ch = getCache(cacheName);
		Element ele = new Element(key, value);
		ch.put(ele);
	}

	public static void delCaches(String cacheName) throws Exception {
		Cache ch = getCache(cacheName);
		ch.removeAll();
		logger.info("清除缓存[" + cacheName + "]内的所有数据。");
	}

	public static void delCache(String cacheName, String key) throws Exception {
		Cache ch = getCache(cacheName);
		ch.remove(key);
		logger.info("清除缓存[" + cacheName + "]内的所有数据。");
	}

	public static boolean checkKey(String cacheName, String key)
			throws Exception {
		Cache ch = getCache(cacheName);
		return ch.getKeys().contains(key);
	}

}
