package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysStaffCsmdatPO extends BasePO {
    private String staffId;

    private String dataRoleId;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getDataRoleId() {
        return dataRoleId;
    }

    public void setDataRoleId(String dataRoleId) {
        this.dataRoleId = dataRoleId == null ? null : dataRoleId.trim();
    }

    public  SysStaffCsmdatPO() {
        super.PK="staffId";
    }
}