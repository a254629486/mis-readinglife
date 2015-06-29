 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>SysStaffPositiServiceImpl<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.service.impl;

import java.util.Map;
import org.springframework.stereotype.Service;

import com.readinglife.framework.service.SingleBaseService;
import com.readinglife.framework.service.BaseService;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.string.StringUtil;
import com.readinglife.tools.reflect.ReflectUtil;
import com.system.service.SysStaffPositiService;

 /**
 * <b>类名称：</b>SysStaffPositiServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@SuppressWarnings("rawtypes")
@Service("SysStaffPositiService")
public class SysStaffPositiServiceImpl extends 
			BaseService
 implements SysStaffPositiService {

	
	
	public JsonPager selectByPageMap(JsonPager jPager, Map param) {
			return selectByPage("com.system.dao.impl.SysStaffPositiDAO.selectId", "com.system.dao.impl.SysStaffPositiDAO.countId", jPager, param);
	}
	
}
