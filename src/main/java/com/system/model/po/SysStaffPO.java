package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysStaffPO extends BasePO {
    private String staffId;

    private String departId;

    private String loginName;

    private String loginPwd;

    private String name;

    private String gender;

    private String age;

    private String email;

    private String qq;

    private String mphoneNo;

    private String tphoneNo;

    private String extensNo;

    private String status;

    private String staffType;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId == null ? null : departId.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getMphoneNo() {
        return mphoneNo;
    }

    public void setMphoneNo(String mphoneNo) {
        this.mphoneNo = mphoneNo == null ? null : mphoneNo.trim();
    }

    public String getTphoneNo() {
        return tphoneNo;
    }

    public void setTphoneNo(String tphoneNo) {
        this.tphoneNo = tphoneNo == null ? null : tphoneNo.trim();
    }

    public String getExtensNo() {
        return extensNo;
    }

    public void setExtensNo(String extensNo) {
        this.extensNo = extensNo == null ? null : extensNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType == null ? null : staffType.trim();
    }

    public  SysStaffPO() {
        super.PK="staffId";
    }
}