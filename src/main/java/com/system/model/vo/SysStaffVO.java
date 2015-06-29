package com.system.model.vo;

import com.readinglife.framework.po.BasePO;

 public class SysStaffVO  extends BasePO {
	 
	 private String staffId;
	 private String departId;
	 private String status;
	 private String privilId;
	 private String positiId;
	 
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrivilId() {
		return privilId;
	}
	public void setPrivilId(String privilId) {
		this.privilId = privilId;
	}
	public String getPositiId() {
		return positiId;
	}
	public void setPositiId(String positiId) {
		this.positiId = positiId;
	}
	 
	 
	 
}
