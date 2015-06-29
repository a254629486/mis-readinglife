 /**
 * <b>项目名：</b>readinglife-common<br/>
 * <b>包名：</b>com.readinglife.framework.exceptions.supper<br/>
 * <b>文件名：</b>ExceptionType.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年9月24日-下午4:49:24<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.readinglife.framework.exceptions.supper;

 /**
 * <b>类名称：</b>ExceptionType<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月24日 下午4:49:24<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public enum ExceptionType {
	VALIDATOR(1000),MESSAGE(2000),SERVER(3000),SYSTEM(4000),ERROR(5000);
	
	private ExceptionType(Integer value) {
		this.value = value;
	}
	
	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value+"";
	}
}
