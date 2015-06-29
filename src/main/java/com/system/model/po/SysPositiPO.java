package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysPositiPO extends BasePO {
    private String positiId;

    private String positiPid;

    private String departId;

    private String name;

    private Integer priori;

    private String status;

    public String getPositiId() {
        return positiId;
    }

    public void setPositiId(String positiId) {
        this.positiId = positiId == null ? null : positiId.trim();
    }

    public String getPositiPid() {
        return positiPid;
    }

    public void setPositiPid(String positiPid) {
        this.positiPid = positiPid == null ? null : positiPid.trim();
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId == null ? null : departId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public  SysPositiPO() {
        super.PK="positiId";
    }
}