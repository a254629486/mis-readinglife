package com.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 public class ShiroCache<K, V> implements Cache<K, V> {
	     Logger logger = LoggerFactory.getLogger(this.getClass());

	 	@Resource
		private MemcachedClient memcachedClient;
	     
	     private String sessionCachePrefix;
	     private String cacheName;

	     public ShiroCache(String sessionCachePrefix, String cacheName) {
	         this.sessionCachePrefix = sessionCachePrefix;
	         this.cacheName = cacheName;
	     }

	     @Override
	     public V get(K k) throws CacheException {
	         logger.debug("get cache for key: {}", k);

	         if (null!=k){
				try {
					return (V) memcachedClient.get(getKey(k));
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}
	         }
	         return null;
	     }

	     @Override
	     public V put(K k, V v) throws CacheException {
	    	 try {
	    		 memcachedClient.set(getKey(k), 0, v);
	    		 
	    		 logger.debug("put cache for key: {}, return value: {}", k, v);
				 return (V) memcachedClient.get(getKey(k));
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MemcachedException e) {
				e.printStackTrace();
			}
	    	 return null;
	     }

	     @Override
	     public V remove(K k) throws CacheException {
	    	 try {
	    		 V v = memcachedClient.get(cacheName+k.toString());
	    		 memcachedClient.delete(getKey(k));
	    		 
	    		 logger.debug("remove cache for key: {}, return value: {}", k, v);
				 return v;
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MemcachedException e) {
				e.printStackTrace();
			}
	    	 return null;
	     }

	     @Override
	     public void clear() throws CacheException {
	    	 try {
				memcachedClient.delete(cacheName);
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MemcachedException e) {
				e.printStackTrace();
			}
			 logger.debug("clear cache");
	     }

	     @Override
	     public int size() {
	         try {
				return memcachedClient.getStats().size();
			} catch (MemcachedException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
			return 0;
	     }

	     @Override
	     public Set<K> keys() {
	    	 Set<K> cl = null;
			try {
				cl = (Set<K>) memcachedClient.getStats().keySet();
			} catch (MemcachedException e) {
				e.printStackTrace();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}catch (TimeoutException e) {
				e.printStackTrace();
			}
	         logger.debug("get cache keys, size : {}", cl.size());
	         return cl;
	     }

	     @Override
	     public Collection<V> values() {
	         Collection<V> cs = new ArrayList<V>();
//	         try {
//				Iterator itemsItr = memcachedClient.getStats().keySet().iterator();
//			 
//				
//			} catch (MemcachedException | InterruptedException
//					| TimeoutException e) {
//				e.printStackTrace();
//			}
//	         logger.debug("get cache values, size : {}", cs.size());
	         return cs;
	     }
	     
	     private String getKey(K k){
			return sessionCachePrefix+k.toString();
	     }
	 }