package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysLokcdePO extends BasePO {
    private String lokcde;

    private String loktpe;

    private String lokcdeName;

    public String getLokcde() {
        return lokcde;
    }

    public void setLokcde(String lokcde) {
        this.lokcde = lokcde == null ? null : lokcde.trim();
    }

    public String getLoktpe() {
        return loktpe;
    }

    public void setLoktpe(String loktpe) {
        this.loktpe = loktpe == null ? null : loktpe.trim();
    }

    public String getLokcdeName() {
        return lokcdeName;
    }

    public void setLokcdeName(String lokcdeName) {
        this.lokcdeName = lokcdeName == null ? null : lokcdeName.trim();
    }

    public  SysLokcdePO() {
        super.PK="lokcde";
    }
}