 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.controller<br/>
 * <b>文件名：</b>SysStaffController.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.ForBean;

import com.readinglife.framework.web.BaseController;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.json.Jacksons;
import com.system.model.po.SysStaffPO;
import com.system.service.SysStaffService;

/**
 * <b>类名称：</b>SysStaffController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@Controller
@RequestMapping("/sysStaffController")
public class SysStaffController extends BaseController{  
	
	@Autowired
	SysStaffService sysStaffService;
	
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/save")
	@ResponseBody
	public String save(@ForBean SysStaffPO record) {
		sysStaffService.save(record);
		return result(true, SAVE_SUCCESS);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(HttpServletRequest request) {
		Map param = formatParam(request);
		sysStaffService.deleteByPrimaryKeys(param.get("pkId").toString(),String.class);
		return result(true, DELETE_SUCCESS);
	}
	
	@RequestMapping("/toSearchList")
	public String toSearchList(HttpServletRequest request) {
	   return "//sys/person/sys_staff_list";
	}
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/searchList")
	@ResponseBody
	public String searchList(HttpServletRequest request,Model model) {
		Map param = formatParam(request);
		JsonPager jPager = getJsonPager(param);
		jPager = sysStaffService.selectByPageMap(jPager,param);
	   return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
}
