 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.controller<br/>
 * <b>文件名：</b>SysPersonStaffController.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.readinglife.framework.web.BaseController;
import com.readinglife.tools.json.Jacksons;
import com.readinglife.tools.string.StringUtil;
import com.system.constants.SysConstants;

/**
 * <b>类名称：</b>SysConstantsController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@Controller
@RequestMapping("/sysConstantsController")
public class SysConstantsController extends BaseController{  
	
	private final String URL_MAOOING =  "/sysConstantsController/";
 
	
	@RequestMapping("/getTypeList/{type}")
	@ResponseBody
	public String getTypeList(@PathVariable String type,HttpServletRequest request) {
		String select = request.getParameter("select");
		Map<String, String> rootMap = SysConstants.ROOT_MAP.get(type);
		
		Set<String> set = rootMap.keySet();
		Map<String, String> map = null;
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for (String string : set) {
			if(StringUtil.isNotBlank(select)&&"true".equals(select.trim())){
				map = new HashMap<String, String>();
				map.put("selected", "selected");
				map.put("id", "");
				map.put("text", "请选择");
				list.add(map);
				select=null;
			}
			map = new HashMap<String, String>();
			map.put("id", string);
			map.put("text", rootMap.get(string).toString());
			list.add(map);
		}
		
		return Jacksons.json().fromObjectToJson(list);
	}
	
	
}
