 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.model<br/>
 * <b>文件名：</b>SysPositiRoleVO.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.model.vo;


import com.readinglife.framework.po.BasePO;

public class PositiRoleVO extends BasePO {

 	 	private java.lang.String roleId;
 	 	private String positiId;

	 	public String getPositiId() {
	 		return positiId;
	 	}

	 	public void setPositiId(String positiId) {
	 		this.positiId = positiId;
	 	}

 	    public java.lang.String getroleId() {
	        return roleId;
	    }
	    public void setroleId(java.lang.String roleId) {
	        this.roleId = roleId;
	    }
	    
}
