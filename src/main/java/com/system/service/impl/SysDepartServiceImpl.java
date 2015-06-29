 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>SysDepartServiceImpl<br/>
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
import com.readinglife.tools.reflect.ReflectUtil;
import com.readinglife.tools.string.StringUtil;
import com.system.model.po.SysDepartPOExample;
import com.system.service.SysDepartService;

 /**
 * <b>类名称：</b>SysDepartServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@SuppressWarnings("rawtypes")
@Service("SysDepartService")
public class SysDepartServiceImpl extends 
			SingleBaseService
 implements SysDepartService {

	public String initNamespace() {
		return "com.system.dao.impl.SysDepartPOMapper";
	}
	
	
	public JsonPager selectByPageMap(JsonPager jPager, Map param) {
			SysDepartPOExample example = new SysDepartPOExample() ;
			com.system.model.po.SysDepartPOExample.Criteria c = example.createCriteria();
		
			 			 if (StringUtil.isNotNull(param.get("name"))) {
								c.andNameLike(param.get("name")+"%");
							}
			 			 if (StringUtil.isNotNull(param.get("code"))) {
								c.andCodeLike(param.get("code")+"%");
							}
			
			 if (StringUtil.isNotNull(param.get("sort"))&&StringUtil.isNotNull(param.get("order"))) {
					example.setOrderByClause(ReflectUtil.toSqlName(param.get("sort").toString())+" "+param.get("order").toString());
			 }
			
			return selectByPage(jPager,example);
	}
	
}
