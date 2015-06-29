package com.readinglife.framework.service;

import java.io.Serializable;
import java.util.List;

import com.readinglife.framework.po.BasePO;
import com.readinglife.framework.web.JsonPager;


public interface ISingleBaseService<PO extends BasePO,PK extends Serializable, PE> {
	
	int countByExample(PE example);

	int deleteByExample(PE example);

	int deleteByPrimaryKey(PK pk);

	int insert(PO record);

	int insertSelective(PO record);

	List<PO> selectByExample(PE example);

	PO selectByPrimaryKey(PK pk);

	int updateByExampleSelective(PO record,PE example);

	int updateByExample(PO record,PE example);

	int updateByPrimaryKeySelective(PO record);

	int updateByPrimaryKey(PO record);
	
	int deleteByPrimaryKeys(String pks,Class clazz);
	
	int save(PO record);
	
	<E> JsonPager<E> selectByPage(JsonPager<E> jPager,PE example);

}
