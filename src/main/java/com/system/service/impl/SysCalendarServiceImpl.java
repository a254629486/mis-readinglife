package com.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.readinglife.framework.service.SingleBaseService;
import com.system.model.po.SysCalendarPO;
import com.system.model.vo.SysCalendarVO;
import com.system.service.SysCalendarService;

@Service("pmsCfCalendarService")
public class SysCalendarServiceImpl extends SingleBaseService  implements SysCalendarService{
	
	@Override
	public String initNamespace() {
		// TODO Auto-generated method stub
		return "com.system.dao.impl.SysCalendarPOMapper";
	}
	public List<List<SysCalendarPO>> create(int startYear, int endYeay) {
		int total=0;//总数
		int week=0;//第一天是星期几
		int firstday=0;//临时变量
		List<List<SysCalendarPO>> newarraylist = new ArrayList<List<SysCalendarPO>>();
		List<SysCalendarPO> list= new ArrayList<SysCalendarPO>();
		//开始年份到结束年份总共有多少天
		for(int i=startYear;i<=endYeay;i++){
			if((i%4==0&&i%100!=0)||(i%400==0)){
				total=total+366;
			}
			else{
				total=total+365;
			}
		}
		System.out.println(total);
		
		//1990年的周一是一月一号，依此类推，开始年份的第一天是周几
		for(int i=1900;i<startYear;i++){
			if((i%4==0 && i%100!=0)||(i%400==0)){
				firstday=firstday+366;
			}
			else{
				firstday=firstday+365;
			}
		}
		firstday=firstday+1;
		if(firstday%7==0){
			week=7;
		}
		else{
			week=firstday%7;
		}
		
		//测试传进来的年份第一天是周几 
		// week=((startYear-1)+(startYear-1)/4-(startYear-1)/100+(startYear-1)/400+1)%7;
		
		//从开始年份到结束年份
		for(int i=startYear;i<=endYeay;i++){
			//一年
			for(int j=1;j<13;j++){
				if(j==1||j==3||j==5||j==7||j==8||j==10||j==12){
					for(int k=1;k<32;k++){
						SysCalendarPO po = new SysCalendarPO();
						if (week>7) {
							week = 1;
						}
						po.setWeek(String.valueOf(week));
						po.setYear(Short.parseShort(String.valueOf(i)));
						po.setMonth(Short.parseShort(String.valueOf(j)));
						po.setDay(Short.parseShort(String.valueOf(k)));
						list.add(po);
						week ++;
						this.insert("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.insertdata", po );
					}
				}
				else if(j==4||j==6||j==9||j==11){
					for(int k=1;k<31;k++){
						SysCalendarPO po = new SysCalendarPO();
						if (week>7) {
							week = 1;
						}
						po.setWeek(String.valueOf(week));
						po.setYear(Short.parseShort(String.valueOf(i)));
						po.setMonth(Short.parseShort(String.valueOf(j)));
						po.setDay(Short.parseShort(String.valueOf(k)));
						list.add(po);
						week ++;
						this.insert("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.insertdata", po );
					}
				}
				else if(j==2&&((i%4==0 && i%100!=0)||(i%400==0))){
					for(int k=1;k<30;k++){
						SysCalendarPO po = new SysCalendarPO();
						if (week>7) {
							week = 1;
						}
						po.setWeek(String.valueOf(week));
						po.setYear(Short.parseShort(String.valueOf(i)));
						po.setMonth(Short.parseShort(String.valueOf(j)));
						po.setDay(Short.parseShort(String.valueOf(k)));
						list.add(po);
						week ++;
						this.insert( "com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.insertdata", po );
					}
				}
				else{
					for(int k=1;k<29;k++){
						SysCalendarPO po = new SysCalendarPO();
						if (week>7) {
							week = 1;
						}
						po.setWeek(String.valueOf(week));
						po.setYear(Short.parseShort(String.valueOf(i)));
						po.setMonth(Short.parseShort(String.valueOf(j)));
						po.setDay(Short.parseShort(String.valueOf(k)));
						list.add(po);
						week ++;
						this.insert("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.insertdata", po );
					}
				}
			
			
			}
			
			newarraylist.add(list);
			
		}
		return newarraylist;

	}

	@SuppressWarnings("rawtypes")
	public List<SysCalendarVO> getMonthList(int year, int month,int day) {
		//从数据库查出本月的每一天
		Map nowmap=new HashMap();
		nowmap.put("year", year);
		nowmap.put("month", month);
		String week = "";
		List<SysCalendarVO> nowMothList=this.selectList("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.getNowMonthList",nowmap);
		//在本月的cssclass中添加字符串为“nowday”
		for (int i = 0; i < nowMothList.size(); i++) {
			SysCalendarVO nowday=nowMothList.get(i);
			/*if(nowday.getWeek().equals("6")||nowday.getWeek().equals("7")){
				nowday.setCssclass("weekenks");
			}else if(nowday.getDay()==day){
				nowday.setCssclass("today");
			}else{
				nowday.setCssclass("nowday");
			}*/
			String cls = "";
			if(nowday.getWeek().equals("6")||nowday.getWeek().equals("7")){
				cls = "weekenks";
			}else{
				cls = "nowday";
			}
			if(nowday.getDay()==day){
				cls += " today";
			}
			nowday.setCssclass(cls);
			//得到的本月的第一天为周几
			if(i==0){
				 week=nowday.getWeek();
			}
		}
		
		//把所有的周六周末的caaclass中添加字符串“weekends”
		
		
		
		int firstweek=Integer.valueOf(week).intValue();
		//展示本月日期时，上个月展示的日期数beforenum
		int beforenum=firstweek-1;
		//展示本月日期时的总数nownum
		int nownum=nowMothList.size();
		//展示本月日期时，下个月展示的日期数afternum
		int afternum=42-beforenum-nownum;
		
		//从数据库查出上个月的每一天
		Map beforemap=new HashMap();
		if(month==1){
			beforemap.put("year", year-1);
			beforemap.put("month", 12);
		}
		else{
			beforemap.put("year", year);
			beforemap.put("month", month-1);
		}
		beforemap.put("beforenum", beforenum);
		List<SysCalendarVO> beforeMothList=this.selectList("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.getBeforeMonthList", beforemap);
		if(beforenum!=0){
			for(int i=0;i<beforenum;i++){
				SysCalendarVO beforeday=beforeMothList.get(i);
				beforeday.setCssclass("beforeday");
			}
		}
		//从数据库查出下个月的每一天
		Map aftermap=new HashMap();
		if(month==12){
			aftermap.put("year", year+1);
			aftermap.put("month", 1);
		}
		else{
			aftermap.put("year", year);
			aftermap.put("month", month+1);
		}
		aftermap.put("afternum", afternum);
		List<SysCalendarVO> afterMothList=this.selectList("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.getAfterMonthList", aftermap);
		//在下个月的cssclass中添加字符串为“afterday”
		for(int i=0;i<afternum;i++){
			SysCalendarVO afterday=afterMothList.get(i);
			afterday.setCssclass("afterday");
		}
		beforeMothList.addAll(nowMothList);
		beforeMothList.addAll(afterMothList);
		return beforeMothList;
	}
	public List<SysCalendarVO> getYearList(int year) {
		List<SysCalendarVO> getYearList=this.selectList("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.getYearList", null);
		for(int i=0;i<getYearList.size();i++){
			SysCalendarVO vo=getYearList.get(i);
			if(vo.getYear()==year){
				vo.setSelected("true");
			}
		}
		return getYearList;
	}
	public List<SysCalendarVO> searchMonthList(int month) {
		List<SysCalendarVO> searchMonthList=this.selectList("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.searchMonthList", null);
		for(int i=0;i<searchMonthList.size();i++){
			SysCalendarVO vo=searchMonthList.get(i);
			if(vo.getMonth()==month){
				vo.setSelected("true");
			}
		}
		return searchMonthList;
	}
	public int addholiday(int year, int month, int day) {
		Map map=new HashMap(); 
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		return this.insert(this.initNamespace() + ".update", map );
	}
	public int addholidays(Map map) {
	
		return this.update("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.updateCal", map);
	}
	public List<SysCalendarVO> selectholidayList(int selectyear) {
		List<SysCalendarVO> selectholidayList=this.selectList("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.selectholidayList", selectyear);
		return selectholidayList;
	}
	@Override
	public int getyearholiday(int selectyear, String holiday) {
		Map map=new HashMap();
		map.put("year", selectyear);
		map.put("holidayName", holiday);
		int getmonth=this.selectOne("com.readinglife.b2b.sys.dao.impl.SysCalendarPODAO.getmonth", map);
		return getmonth;
	}

	
}