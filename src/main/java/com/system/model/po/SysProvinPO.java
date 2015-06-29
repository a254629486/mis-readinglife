package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysProvinPO extends BasePO {
    private String provinId;

    private String name;

    private String code;

    private Integer priori;

    public String getProvinId() {
        return provinId;
    }

    public void setProvinId(String provinId) {
        this.provinId = provinId == null ? null : provinId.trim();
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

    public  SysProvinPO() {
        super.PK="provinId";
    }
}