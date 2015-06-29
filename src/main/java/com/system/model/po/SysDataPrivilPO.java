package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysDataPrivilPO extends BasePO {
    private String dataPrivilId;

    private String tableCode;

    private String tableName;

    private String columnCode;

    private String columnName;

    private String role;

    public String getDataPrivilId() {
        return dataPrivilId;
    }

    public void setDataPrivilId(String dataPrivilId) {
        this.dataPrivilId = dataPrivilId == null ? null : dataPrivilId.trim();
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode == null ? null : tableCode.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode == null ? null : columnCode.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public  SysDataPrivilPO() {
        super.PK="dataPrivilId";
    }
}