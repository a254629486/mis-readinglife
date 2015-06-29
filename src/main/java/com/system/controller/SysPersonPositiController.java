/**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.controller<br/>
 * <b>文件名：</b>SysPersonPositiController.java<br/>
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

import com.readinglife.framework.web.BaseController;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.json.Jacksons;
import com.system.model.vo.SysPersonPositiVO;
import com.system.service.SysPersonPositiService;

/**
 * <b>类名称：</b>SysPersonPositiController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@Controller
@RequestMapping("/sysPersonPositiController")
public class SysPersonPositiController extends BaseController {

	private final String URL_MAOOING = "/sysPersonPositiController/";

	@Autowired
	SysPersonPositiService sysPersonPositiService;

	@RequestMapping("/toSearchList")
	public String toSearchList(HttpServletRequest request) {
		return "//sys/person/sys_person_positi_list";
	}

	@RequestMapping("/searchList")
	@ResponseBody
	public String searchList(HttpServletRequest request, Model model) {
		Map param = formatParamByBeanProperty(request, SysPersonPositiVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysPersonPositiService.selectByPageMap(jPager, param);
		return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}

}
