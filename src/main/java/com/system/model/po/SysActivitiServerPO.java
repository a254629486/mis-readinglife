package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysActivitiServerPO extends BasePO {
    private String serverId;

    private String serverIp;

    private String name;

    private String status;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId == null ? null : serverId.trim();
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp == null ? null : serverIp.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public  SysActivitiServerPO() {
        super.PK="serverId";
    }
}