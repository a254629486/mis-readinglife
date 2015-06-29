package com.readinglife.framework.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.binding.MapperMethod.MapperParamMap;
import org.springframework.transaction.annotation.Transactional;

import com.readinglife.framework.po.BasePO;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.reflect.ReflectUtil;
import com.readinglife.tools.string.StringUtil;

@Transactional
public abstract class SingleBaseService<PO extends BasePO,PK extends Serializable, PE>
						extends BaseService<PO,PK,PE> implements ISingleBaseService<PO,PK,PE> {

	public abstract String initNamespace() ;
	
	private String initNamespace(String nid){
		return this.initNamespace().concat(nid);
	}
	
	public int countByExample(PE example) {
		return super.selectOne(initNamespace(".countByExample"), example);
	}

	public int deleteByExample(PE example) {
		return super.delete(initNamespace(".deleteByExample"), example);
	}

	public int deleteByPrimaryKey(PK pk) {
		return super.delete(initNamespace(".deleteByPrimaryKey"), pk);
	}

	public int insert(PO record) {
		return super.insert(initNamespace(".insert"), record);
	}

	public int insertSelective(PO record) {
		return super.insert(initNamespace(".insertSelective"), record);
	}

	public List<PO> selectByExample(PE example) {
		return super.selectList(initNamespace(".selectByExample"), example);
	}

	public PO selectByPrimaryKey(PK pk) {
		return super.selectOne(initNamespace(".selectByPrimaryKey"), pk);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int updateByExampleSelective(PO record, PE example) {
		MapperParamMap mpm = new MapperParamMap();
		mpm.put("record", record);
		mpm.put("example", example);
		return super.update(initNamespace(".updateByExampleSelective"), mpm);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int updateByExample(PO record, PE example) {
		MapperParamMap mpm = new MapperParamMap();
		mpm.put("record", record);
		mpm.put("example", example);
		return super.update(initNamespace(".updateByExample"), mpm);
	}

	public int updateByPrimaryKeySelective(PO record) {
		return super.update(initNamespace(".updateByPrimaryKeySelective"), record);
	}

	public int updateByPrimaryKey(PO record) {
		return super.update(initNamespace(".updateByPrimaryKey"), record);
	}
	
	public int deleteByPrimaryKeys(String pks,Class clazz) {
		 Integer rs = 0;
		 for (Object object : pks.split(",")) {
			 if("java.lang.Integer".equals(clazz.getName())){
				 rs += super.delete(initNamespace(".deleteByPrimaryKey"), Integer.parseInt(object.toString()));
			 }
			 if("java.lang.Long".equals(clazz.getName())){
				 rs += super.delete(initNamespace(".deleteByPrimaryKey"), Long.parseLong(object.toString()));
			 }
			 if("java.lang.String".equals(clazz.getName())){
				 rs += super.delete(initNamespace(".deleteByPrimaryKey"), object.toString());
			 }
		 }
		return rs;
	}
	
	public int save(PO record) {
		if(record!=null&&StringUtil.isNotNull(ReflectUtil.getFieldValue(record, record.PK))){
			return	updateByPrimaryKey(record);
		}else {
			return	insert(record);
		}
	}
	
	public <E> JsonPager<E> selectByPage(JsonPager<E> jPager, PE example) {
		return super.fetchPage(initNamespace(".selectByExample"),initNamespace(".countByExample"), jPager, example);
	}

}
