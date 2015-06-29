package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysLoktpePO extends BasePO {
    private String loktpe;

    private String loktpeName;

    public String getLoktpe() {
        return loktpe;
    }

    public void setLoktpe(String loktpe) {
        this.loktpe = loktpe == null ? null : loktpe.trim();
    }

    public String getLoktpeName() {
        return loktpeName;
    }

    public void setLoktpeName(String loktpeName) {
        this.loktpeName = loktpeName == null ? null : loktpeName.trim();
    }

    public  SysLoktpePO() {
        super.PK="loktpe";
    }
}