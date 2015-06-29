package com.system.model.po;

import com.readinglife.framework.po.BasePO;
import java.util.Date;

public class SysLogLoginPO extends BasePO {
    private String loginId;

    private String staffId;

    private String personId;

    private String personName;

    private String loginName;

    private String loginIp;

    private Date loginTimstp;

    private Date logoutTimstp;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginTimstp() {
        return loginTimstp;
    }

    public void setLoginTimstp(Date loginTimstp) {
        this.loginTimstp = loginTimstp;
    }

    public Date getLogoutTimstp() {
        return logoutTimstp;
    }

    public void setLogoutTimstp(Date logoutTimstp) {
        this.logoutTimstp = logoutTimstp;
    }

    public  SysLogLoginPO() {
        super.PK="loginId";
    }
}