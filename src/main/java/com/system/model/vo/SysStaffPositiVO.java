 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.model.vo<br/>
 * <b>文件名：</b>SysStaffPositiVO.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.model.vo;


import com.readinglife.framework.po.BasePO;

public class SysStaffPositiVO extends BasePO {

	 	private java.lang.String positiId;
	 	private java.lang.String positiPid;
	 	private java.lang.String departId;
	 	private java.lang.String name;
	 	private java.lang.Integer priori;
	 	private java.lang.String status;

 	    public java.lang.String getpositiId() {
	        return positiId;
	    }
	    public void setpositiId(java.lang.String positiId) {
	        this.positiId = positiId;
	    }
	    
 	    public java.lang.String getpositiPid() {
	        return positiPid;
	    }
	    public void setpositiPid(java.lang.String positiPid) {
	        this.positiPid = positiPid;
	    }
	    
 	    public java.lang.String getdepartId() {
	        return departId;
	    }
	    public void setdepartId(java.lang.String departId) {
	        this.departId = departId;
	    }
	    
 	    public java.lang.String getname() {
	        return name;
	    }
	    public void setname(java.lang.String name) {
	        this.name = name;
	    }
	    
 	    public java.lang.Integer getpriori() {
	        return priori;
	    }
	    public void setpriori(java.lang.Integer priori) {
	        this.priori = priori;
	    }
	    
 	    public java.lang.String getstatus() {
	        return status;
	    }
	    public void setstatus(java.lang.String status) {
	        this.status = status;
	    }
	    
}
