package com.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.readinglife.framework.web.BaseController;
import com.system.model.po.SysCalendarPO;
import com.system.model.vo.SysCalendarVO;
import com.system.service.SysCalendarService;

@Controller
@RequestMapping("/sysCalendarController")
public class SysCalendarController extends BaseController{
	
	@Autowired
	SysCalendarService sysCalendarService;
	
	/**
	 * 跳转界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/calendar")
    public String calendar(HttpServletRequest request) {
        return "sys/configuration/calendar";
    }
	/**
	 * 生成xx年-xx年的日历
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/creat")
	public String creat(HttpServletRequest request){
		String startyear=request.getParameter("startyear");
		String endyear=request.getParameter("endyear");
		int year1=Integer.valueOf(startyear).intValue();
		int year2=Integer.valueOf(endyear).intValue();
		List<List<SysCalendarPO>> lists = this.sysCalendarService.create(year1, year2);
		return result(true, SAVE_SUCCESS);
	};
	
	@ResponseBody
	@RequestMapping("/show")
	public List<SysCalendarVO> showCalendar(){
		Calendar  cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int day=cal.get(Calendar.DAY_OF_MONTH);
		List<SysCalendarVO> calendarList=this.sysCalendarService.getMonthList(year,month,day);
		return calendarList;
		
	}
	@ResponseBody
	@RequestMapping("/getYearList")
	public List<SysCalendarVO> getYearList(){
		Calendar calen=Calendar.getInstance();
		calen.setTime(new Date());
		int year=calen.get(Calendar.YEAR);
		List<SysCalendarVO> yearlist=this.sysCalendarService.getYearList(year);
		return yearlist;
	}
	@ResponseBody
	@RequestMapping("/searchMonthList")
	public List<SysCalendarVO> searchMonthList(){
		Calendar calen=Calendar.getInstance();
		calen.setTime(new Date());
		int month=calen.get(Calendar.MONTH)+1;
		List<SysCalendarVO> monthlist=this.sysCalendarService.searchMonthList(month);
		return monthlist;
	}
	@ResponseBody
	@RequestMapping("/selectholidayList")
	public List<SysCalendarVO> selectholidayList(HttpServletRequest request){
		String year=request.getParameter("selectyear");
		int selectyear=Integer.valueOf(year).intValue();
		List<SysCalendarVO> selectholidayList=this.sysCalendarService.selectholidayList(selectyear);
		return selectholidayList;
	}
	@ResponseBody
	@RequestMapping("/getyearmonth")
	public List<SysCalendarVO> getyearmonth(HttpServletRequest request){
		String year=request.getParameter("selectyear");
		String month=request.getParameter("selectmonth");
		int selectyear=Integer.valueOf(year).intValue();
		int selectmonth=Integer.valueOf(month).intValue();
		
		int day = 0;
		Calendar  cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curYear=cal.get(Calendar.YEAR);
		int curMonth=cal.get(Calendar.MONTH)+1;
		int curDay=cal.get(Calendar.DAY_OF_MONTH);
		if(curYear == selectyear && curMonth == selectmonth){
			day = curDay;
		}
		
		List<SysCalendarVO> calendarList=this.sysCalendarService.getMonthList(selectyear,selectmonth,day);
		return calendarList;
	}
	@RequestMapping("/addholiday")
	@ResponseBody
	public void addholiday(HttpServletRequest request){
		String selectyear=request.getParameter("year");
		String selectmonth=request.getParameter("month");
		String selectday=request.getParameter("day");
		String holidayname = request.getParameter("holidayname");
		String holiday = request.getParameter("holiday");
		
		int year=Integer.valueOf(selectyear).intValue();
		int month=Integer.valueOf(selectmonth).intValue();
		int day=Integer.valueOf(selectday).intValue();
		Map map = new HashMap();
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		map.put("holidayname", holidayname);
		if (holiday!= null && holiday != "") {
			map.put("flag", holiday);
		}
		this.sysCalendarService.addholidays(map);
	}
	@ResponseBody
	@RequestMapping("/getyearholiday")
	public Map<String,Object> getyearholiday(HttpServletRequest request){
		String year=request.getParameter("selectyear");
		String holiday=request.getParameter("selectholiday");
		try {
			holiday = new String(holiday.getBytes(),"gbk");
			System.out.println("holiday--111="+holiday);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int selectyear=Integer.valueOf(year).intValue();
		int month=this.sysCalendarService.getyearholiday(selectyear,holiday);

		int day = 0;
		Calendar  cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curYear=cal.get(Calendar.YEAR);
		int curMonth=cal.get(Calendar.MONTH)+1;
		int curDay=cal.get(Calendar.DAY_OF_MONTH);
		if(curYear == selectyear && curMonth == month){
			day = curDay;
		}

		List<SysCalendarVO> thiscalendarList=this.sysCalendarService.getMonthList(selectyear,month,day);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("month", month);
		returnMap.put("list", thiscalendarList);
		return returnMap;
	}
}