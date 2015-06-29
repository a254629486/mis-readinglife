package com.readinglife.framework.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;

import com.readinglife.framework.dao.MyBatisDaoSupport;
import com.readinglife.framework.example.SqlKeyCriterion;
import com.readinglife.framework.po.BasePO;
import com.readinglife.framework.web.JsonPager;


public abstract class BaseService<PO extends BasePO,PK extends Serializable, PE>
						extends MyBatisDaoSupport implements IBaseService<PO,PK,PE>{

	protected Log logger = LogFactory.getLog(getClass());

    public <E> List<E> selectLimitedList(String selectId,Object parameter,int offset,int limit) {
    	RowBounds rowBounds = new RowBounds(offset, limit);
        return super.selectLimitedList(selectId, parameter,rowBounds);
    }
	
	public <E> JsonPager<E> selectByPage(String selectId,String countId,JsonPager<E> jPager, Object parameter) {
		return super.fetchPage(selectId,countId, jPager, parameter);
	}

	public static SqlKeyCriterion namespace(Class<?> cls){
		SqlKeyCriterion sqlKeyCriterion=new SqlKeyCriterion();
		String className=cls.getName();
		if(className.substring(className.length()-2, className.length()).equals("VO")){
			className=className.replace(".base.", ".");
		}
		String mapperPackage=className.substring(0,className.indexOf(".model."))+".dao.impl.";
		className=className.substring(className.lastIndexOf(".")+1, className.length());
		String namespace=mapperPackage+className;
		sqlKeyCriterion.setNamespace(namespace);
		return sqlKeyCriterion;
	}

}
