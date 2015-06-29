package com.readinglife.framework.dao;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.readinglife.framework.web.JsonPager;

public class MyBatisDaoSupport extends SqlSessionDaoSupport {

	public int insert(String insertId, Object parameter) {
		return getSqlSession().insert(insertId, parameter);
	}

	public int update(String updateId, Object parameter) {
		return getSqlSession().update(updateId, parameter);
	}

	public int delete(String deleteId, Object parameter) {
		return getSqlSession().delete(deleteId, parameter);
	}

	public <M> M selectOne(String selectId, Object parameter) {
		return getSqlSession().selectOne(selectId, parameter);
	}

	public <E> List<E> selectList(String selectId, Object parameter) {
		return getSqlSession().selectList(selectId, parameter);
	}

	public <E> List<E> selectLimitedList(String selectId,Object parameter, RowBounds rowBounds) {
		return getSqlSession().selectList(selectId, parameter,rowBounds);
	}

	public <M> JsonPager<M> fetchPage(String selectId,String countId, JsonPager<M> page,
			Object parameter) {
		page.setTotal(selectPageCount(countId, parameter));
//		int offset = page.getPage()>1?((page.getPage()-1)*page.getSize()):0;
//		RowBounds rowBounds = new RowBounds(offset, page.getSize());
		RowBounds rowBounds = new RowBounds(page.getPage(), page.getSize());
		List<M> results = getSqlSession().selectList(selectId, parameter, rowBounds);
		page.setRows(results);
		return page;
	}

	public int selectPageCount(String countId, Object parameter) {
		return getSqlSession().selectOne(countId, parameter);
	}
}
