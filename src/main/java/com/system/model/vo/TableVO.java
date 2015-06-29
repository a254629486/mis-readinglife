package com.system.model.vo;

import java.util.List;

/**
 * table对象
 * @author liushuang
 *	2014-4-28 上午9:47:42
 */
public class TableVO implements java.io.Serializable {

	private static final long serialVersionUID = 8994407557316553959L;
	/**
	 * 表名
	 */
	private String name;
	/**
	 * 别名
	 */
	private String alias ;
	/**
	 * 字段数据权限
	 */
	private List<ColumnVO> dataColumn ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public List<ColumnVO> getDataColumn() {
		return dataColumn;
	}
	public void setDataColumn(List<ColumnVO> dataColumn) {
		this.dataColumn = dataColumn;
	}
	@Override
	public String toString() {
		return "TableVO [name=" + name + ", alias=" + alias + ", dataColumn="
				+ dataColumn + "]";
	}
	
	

}
