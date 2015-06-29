package com.system.model.vo;

/**
 * 字段对象
 * @author liushuang
 *	2014-4-28 上午9:48:32
 */
public class ColumnVO implements java.io.Serializable {

	private static final long serialVersionUID = 5005275019961986174L;
	/**
	 * 字段名称
	 */
	private String name ;
	/**
	 * 字段别名
	 */
	private String alias ;
	/**
	 * 字段权限
	 */
	private String role ;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ColumnVO [name=" + name + ", alias=" + alias + ", role=" + role
				+ "]";
	}
	

}
