package com.system.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.readinglife.framework.service.SingleBaseService;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.reflect.ReflectUtil;
import com.readinglife.tools.string.StringUtil;
import com.system.model.po.SysLoktpePOExample;
import com.system.service.LoktpeService;

@Service("loktpeService")
public class LoktpeServiceImpl extends SingleBaseService implements LoktpeService  {

	@Override
	public JsonPager searchLoktpeByPageMap(JsonPager jPager, Map param) {
		SysLoktpePOExample example = new SysLoktpePOExample();
		com.system.model.po.SysLoktpePOExample.Criteria c = example
				.createCriteria();

		if (StringUtil.isNotNull(param.get("loktpe"))) {
			c.andLoktpeLike(param.get("loktpe") + "%");
		}
		if (StringUtil.isNotNull(param.get("loktpeName"))) {
			c.andLoktpeNameLike(param.get("loktpeName") + "%");
		}

		if (StringUtil.isNotNull(param.get("sort"))
				&& StringUtil.isNotNull(param.get("order"))) {
			example.setOrderByClause(ReflectUtil.toSqlName(param.get("sort")
					.toString()) + " " + param.get("order").toString());
		}
		
		return selectByPage(jPager, example);
	}

	@Override
	public String initNamespace() {
		
		return "com.system.dao.impl.SysLoktpePOMapper";
	}

}
