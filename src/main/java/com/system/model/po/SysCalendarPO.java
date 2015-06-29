package com.system.model.po;

import com.readinglife.framework.po.BasePO;

public class SysCalendarPO extends BasePO {
    private String week;

    private Short holidayFlag;

    private String holidayName;

    private Short year;

    private Short month;

    private Short day;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public Short getHolidayFlag() {
        return holidayFlag;
    }

    public void setHolidayFlag(Short holidayFlag) {
        this.holidayFlag = holidayFlag;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName == null ? null : holidayName.trim();
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Short getMonth() {
        return month;
    }

    public void setMonth(Short month) {
        this.month = month;
    }

    public Short getDay() {
        return day;
    }

    public void setDay(Short day) {
        this.day = day;
    }
}