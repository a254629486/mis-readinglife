/**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.system.service.impl<br/>
 * <b>文件名：</b>SysStaffServiceImpl<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.service.impl;

import java.util.Map;
import org.springframework.stereotype.Service;

import com.readinglife.framework.service.SingleBaseService;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.string.StringUtil;
import com.readinglife.tools.reflect.ReflectUtil;
import com.system.model.po.SysStaffPOExample;
import com.system.service.SysStaffService;

/**
 * <b>类名称：</b>SysStaffServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@SuppressWarnings("rawtypes")
@Service("SysStaffService")
public class SysStaffServiceImpl extends SingleBaseService implements
		SysStaffService {

	public String initNamespace() {
		return "com.system.dao.impl.SysStaffPOMapper";
	}

	public JsonPager selectByPageMap(JsonPager jPager, Map param) {
		SysStaffPOExample example = new SysStaffPOExample();
		com.system.model.po.SysStaffPOExample.Criteria c = example
				.createCriteria();

		if (StringUtil.isNotNull(param.get("departId"))) {
			c.andDepartIdLike(param.get("departId") + "%");
		}

		if (StringUtil.isNotNull(param.get("sort"))
				&& StringUtil.isNotNull(param.get("order"))) {
			example.setOrderByClause(ReflectUtil.toSqlName(param.get("sort")
					.toString()) + " " + param.get("order").toString());
		}

		return selectByPage(jPager, example);
	}

}
