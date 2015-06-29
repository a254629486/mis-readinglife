package com.readinglife.framework.service;

import java.io.Serializable;
import java.util.List;

import com.readinglife.framework.po.BasePO;
import com.readinglife.framework.web.JsonPager;


public interface IBaseService<PO extends BasePO,PK extends Serializable, PE> {
	 
	<E> List<E> selectLimitedList(String selectId,Object parameter, int offset,int limit);
	
	<E> JsonPager<E> selectByPage(String selectId,String countId,JsonPager<E> jPager,Object parameter);

}
