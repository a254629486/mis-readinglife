 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>SysCompanyDepartServiceImpl<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.readinglife.framework.service.BaseService;
import com.readinglife.framework.web.JsonPager;
import com.system.service.SysCompanyDepartService;

 /**
 * <b>类名称：</b>SysCompanyDepartServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@SuppressWarnings("rawtypes")
@Service("SysCompanyDepartService")
public class SysCompanyDepartServiceImpl extends 
			BaseService
 implements SysCompanyDepartService {

	
	
	public JsonPager selectByPageMap(JsonPager jPager, Map param) {
			return selectByPage("com.system.dao.impl.SysCompanyDepartDAO.selectId", "com.system.dao.impl.SysCompanyDepartDAO.countId", jPager, param);
	}
	
}
