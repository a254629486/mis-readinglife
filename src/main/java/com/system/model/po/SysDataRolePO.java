package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysDataRolePO extends BasePO {
    private String dataRoleId;

    private String name;

    private String code;

    private String status;

    public String getDataRoleId() {
        return dataRoleId;
    }

    public void setDataRoleId(String dataRoleId) {
        this.dataRoleId = dataRoleId == null ? null : dataRoleId.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public  SysDataRolePO() {
        super.PK="dataRoleId";
    }
}