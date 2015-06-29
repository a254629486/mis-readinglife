package com.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.readinglife.framework.web.BaseController;
import com.readinglife.framework.web.JsonPager;
import com.system.model.po.SQLMapper;
import com.system.model.po.SysDataPrivilPO;
import com.system.service.SysDataService;

//@Controller
//@RequestMapping("/sysData")
public class SysDataController extends BaseController {
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	private SysDataService sysDataService;
	@Autowired
//	private SysDataRolePrivService sysDataRolePrivService ;
	
	/**
	 * 数据权限访问页面
	 * @return
	 */
	@SuppressWarnings("serial")
	@RequestMapping(value="/data",method=RequestMethod.GET)
	public String dataPage(Model model,final String roleName,final String tableName){
		model.addAllAttributes(
				new HashMap<String,Object>(){
					{
						put("roleList", sysDataService.getRoleList());
					}
				}
		);
		return "sys/data/data";
	}
	@RequestMapping(value="/dataList",method=RequestMethod.POST)
	@ResponseBody
	public Object dataList(HttpServletRequest request){
		Map<String, Object> param = formatParam(request);
		JsonPager<?> jPager = getJsonPager(param);
		return sysDataService.getRoleDataList(jPager,param);
	}
	@RequestMapping(value="/tableList",method=RequestMethod.GET)
	@ResponseBody	
	public List<Map<String,String>> tableList(){
		Properties properties = new Properties();
		try {
			 properties.load(getClass().getClassLoader().getResourceAsStream("dataPriv.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String databases = (String) properties.get("databases");
		String[] arr = databases.split(",");
		return sysDataService.tableList(arr);
	}
	
	@RequestMapping(value="/coloumnList",method=RequestMethod.GET)
	@ResponseBody
	public List<Map<String,String>> coloumnList(String dbname,String tablename){
		return sysDataService.columnsListByTable(dbname, tablename);
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Object save(SysDataPrivilPO sysDataPrivilPO,String dataRoleId){
		sysDataService.save(sysDataPrivilPO, dataRoleId);
		return "{\"success\":true,\"msg\":\"操作成功\"}";
	}
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	@ResponseBody
	public Object remove(String dataPrivId){
		sysDataService.remove(dataPrivId);
		return "{\"success\":true,\"msg\":\"操作成功\"}";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody	
	public Object update(SysDataPrivilPO sysDataPrivilPO){
		sysDataService.update(sysDataPrivilPO);
		return "{\"success\":true,\"msg\":\"操作成功\"}";
	}
	/*@RequestMapping(value="/testData",method=RequestMethod.GET)
	@ResponseBody
	public String testData(){
		String sql = "";
		try {
			SQLMapper sqlMapper = new SQLMapper();
			sqlMapper.addTable("sys_area").andSql("sql = 22").addSelectSQL("select * from ");
			sql = sysDataRolePrivService.getData(sqlMapper);
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{"+sql+"}";
	}*/
	@RequestMapping(value="/testUpload",method=RequestMethod.GET)
	public String testUpload(HttpServletRequest request,HttpServletResponse response ,Model model){
		model.addAttribute("sid", request.getSession().getId());
		return "sys/data/testUpload";
	}
}
