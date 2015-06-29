package com.system.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.shiro.session.Session;
import org.springframework.util.SerializationUtils;
//import org.springframework.web.session.mgt.eis.CachingSession;

public class ShiroSessionDAO {//extends CachingSession {

	@Resource
	private MemcachedClient memcachedClient;

	public ShiroSessionDAO() {
	}

	/*public ShiroSessionDAO(boolean useMemCache) {
		super(useMemCache);
	}
*/
	/**
	 * session删除
	 * 
	 * @param session
	 */
	protected void deleteSession(Serializable sessionId) {
		try {
			memcachedClient.delete((String) sessionId);
		} catch (TimeoutException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	protected byte[] getValue(Serializable sessionId) {
		try {
			return memcachedClient.get((String) sessionId);
		} catch (TimeoutException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 创建session, 用户登录
	 * 
	 * @param session
	 * @return
	 */
	protected void createSession(Session session, Serializable sessionId) {
		try {
			memcachedClient.add((String) sessionId, 30,
					SerializationUtils.serialize(session));
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * session读取或更新
	 * 
	 * @param session
	 */
	protected void updateSession(Session session, Serializable sessionId) {
		try {
			System.out.println("sessionId="+sessionId+";;;session="+session.getId());
			memcachedClient.set((String) sessionId, 30,
					SerializationUtils.serialize(session));
		} catch (TimeoutException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前活跃的session, 当前在线数量
	 * 
	 * @return
	 */
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		// try {
		// Set s = memcachedClient.getStats().keySet();
		// for (Object object : s) {
		// sessions.add((Session)memcachedClient.get(object.toString()));
		// }
		//
		// } catch (MemcachedException | InterruptedException | TimeoutException
		// e) {
		// e.printStackTrace();
		// }
		return sessions;
	}
/*	@Override
	public void  delete(Session session){
		System.out.println("当前的sessionid是"+session.getId()+",要删除当前的session了");
		
		super.delete(session);
	}


	@Override
	protected Serializable doCreate(Session session) {
		System.out.println("要创建信的session了,新的sessionid是"+session.getId()+";session对象是:"+session);
		return super.doCreate(session);
	}*/
	
}