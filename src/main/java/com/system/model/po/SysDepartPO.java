package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysDepartPO extends BasePO {
    private String departId;

    private String departPid;

    private String companId;

    private String name;

    private String code;

    private Integer priori;

    private String status;

    private String remark;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId == null ? null : departId.trim();
    }

    public String getDepartPid() {
        return departPid;
    }

    public void setDepartPid(String departPid) {
        this.departPid = departPid == null ? null : departPid.trim();
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public  SysDepartPO() {
        super.PK="departId";
    }
}