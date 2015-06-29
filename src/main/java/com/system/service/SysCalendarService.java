package com.system.service;

import java.util.List;
import java.util.Map;

import com.system.model.po.SysCalendarPO;
import com.system.model.vo.SysCalendarVO;


public interface SysCalendarService {
	
	List<List<SysCalendarPO>> create(int startYear, int endYeay);

	List<SysCalendarVO> getMonthList(int year, int month,int day);

	List<SysCalendarVO> getYearList(int year);

	List<SysCalendarVO> searchMonthList(int month);

	int addholiday(int year, int month, int day);
	
	int addholidays(Map map);

	List<SysCalendarVO> selectholidayList(int selectyear);

	int getyearholiday(int selectyear, String holiday);
}