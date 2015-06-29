package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysCompanPO extends BasePO {
    private String companId;

    private String name;

    private String fullName;

    private String code;

    private String addres;

    private Integer priori;

    private String status;

    private String remark;

    public String getCompanId() {
        return companId;
    }

    public void setCompanId(String companId) {
        this.companId = companId == null ? null : companId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres == null ? null : addres.trim();
    }

    public Integer getPriori() {
        return priori;
    }

    public void setPriori(Integer priori) {
        this.priori = priori;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public  SysCompanPO() {
        super.PK="companId";
    }
}