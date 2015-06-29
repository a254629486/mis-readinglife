package com.system.service.impl;
import org.apache.shiro.ShiroException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 public class ShiroCacheManager implements CacheManager, Initializable, Destroyable {
	     Logger logger = LoggerFactory.getLogger(this.getClass());

	     /**
	      * 缓存项前缀
	      */
	     private String sessionCachePrefix = "cache-";

	     @Override
	     public void destroy() throws Exception {
	     }

	     /**
	      * 根据名字返回CACHE
	      * @param s
	      * @param <K>
	      * @param <V>
	      * @return
	      * @throws CacheException
	      */
	     @Override
	     public <K, V> Cache<K, V> getCache(String s) throws CacheException {
	         logger.info("shiro get cache, return : ShiroCache, cache name: {}", s);
	         return new ShiroCache<K, V>(sessionCachePrefix, s);
	     }

	     @Override
	     public void init() throws ShiroException {
	     }

	     public void setSessionCachePrefix(String sessionCachePrefix) {
	         this.sessionCachePrefix = sessionCachePrefix;
	     }
}