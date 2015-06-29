package com.system.model.vo;

public class SysCalendarVO{
	
	private String week;

    private Short holidayFlag;

    private String holidayName;

    private Short year;

    private Short month;

    private Short day;
    
    private String cssclass;
    
    private String selected;

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
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
		this.holidayName = holidayName;
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

	public String getCssclass() {
		return cssclass;
	}

	public void setCssclass(String cssclass) {
		this.cssclass = cssclass;
	}
    
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

}