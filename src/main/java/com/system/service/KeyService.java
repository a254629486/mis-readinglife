package com.system.service;

import java.util.List;

import com.readinglife.tools.key.KeyModel;


public interface KeyService {
	
	/**
	 * 获得所有主键生成器
	 * @return
	 */
	public List<KeyModel> searchKeyList();
	/**
	 * 是否KEY重复
	 * @param keyId
	 * @return
	 */
	public boolean isHaveKeyId(String keyId);
	/**
	 * 保存KEY生成器
	 * @param keyVO
	 */
	public void saveKey(KeyModel keyVO);
	/**
	 * 获得KEY
	 * @param keyId
	 * @return
	 */
	public KeyModel searchKeyVO(String keyId);
	
	/**
	 * 更新KEY
	 * @param keyVO
	 */
	public void updateKey(KeyModel keyVO);
	
}
