package com.readinglife.framework.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.readinglife.framework.po.BasePO;
import com.readinglife.framework.po.BasePoExample;


public interface IBaseDAO<PO extends BasePO,PK extends Serializable, PE extends BasePoExample> {
	
	int countByExample(PE example);

	int deleteByExample(PE example);

	int deleteByPrimaryKey(PK pk);

	int insert(PO record);

	int insertSelective(PO record);

	List<PO> selectByExample(PE example);

	PO selectByPrimaryKey(PK pk);

	int updateByExampleSelective(@Param("record") PO record, @Param("example") PE example);

	int updateByExample(@Param("record") PO record, @Param("example") PE example);

	int updateByPrimaryKeySelective(PO record);

	int updateByPrimaryKey(PO record);

}
