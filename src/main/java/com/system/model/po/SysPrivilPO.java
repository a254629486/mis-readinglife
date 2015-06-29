package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysPrivilPO extends BasePO {
    private String privilId;

    private String privilPid;

    private String name;

    private String type;

    private String prograCode;

    private Integer priori;

    private String status;

    public String getPrivilId() {
        return privilId;
    }

    public void setPrivilId(String privilId) {
        this.privilId = privilId == null ? null : privilId.trim();
    }

    public String getPrivilPid() {
        return privilPid;
    }

    public void setPrivilPid(String privilPid) {
        this.privilPid = privilPid == null ? null : privilPid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPrograCode() {
        return prograCode;
    }

    public void setPrograCode(String prograCode) {
        this.prograCode = prograCode == null ? null : prograCode.trim();
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

    public  SysPrivilPO() {
        super.PK="privilId";
    }
}