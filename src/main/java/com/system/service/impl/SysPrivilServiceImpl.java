/**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>SysPrivilServiceImpl<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.readinglife.framework.service.SingleBaseService;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.reflect.ReflectUtil;
import com.readinglife.tools.string.StringUtil;
import com.system.model.po.SysPrivilPO;
import com.system.model.po.SysPrivilPOExample;
import com.system.service.SysPrivilService;

/**
 * <b>类名称：</b>SysPrivilServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@SuppressWarnings("rawtypes")
@Service("SysPrivilService")
public class SysPrivilServiceImpl extends SingleBaseService implements
		SysPrivilService {

	public String initNamespace() {
		return "com.system.dao.impl.SysPrivilPOMapper";
	}

	public JsonPager selectByPageMap(JsonPager jPager, Map param) {
		SysPrivilPOExample example = new SysPrivilPOExample();
		com.system.model.po.SysPrivilPOExample.Criteria c = example
				.createCriteria();
		List<String> values = new ArrayList<String>();
		/*参考 SysConstants.PRIVIL_TYPE 所有按钮级别操作都不显示*/
		values.add("4");
		values.add("6");
		values.add("7");
		values.add("8");
		values.add("9");
		values.add("10");
		values.add("11");
		c.andTypeNotIn(values);
		if (StringUtil.isNotNull(param.get("sort"))
				&& StringUtil.isNotNull(param.get("order"))) {
			example.setOrderByClause(ReflectUtil.toSqlName(param.get("sort")
					.toString()) + " " + param.get("order").toString());
		}else {
			example.setOrderByClause(" priori asc ");
		}

		return selectByPage(jPager, example);
	}

	
	/**
	 * 删除节点及其子节点
	 * @Title deleteTreeNode
	 * @Description TODO
	 * @param id
	 * @return 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年10月11日-下午3:16:57
	 * @update 
	 */
	private int deleteTreeNode(String id){
		int num = 0;
		SysPrivilPOExample example = new SysPrivilPOExample();
		com.system.model.po.SysPrivilPOExample.Criteria criteria = example.createCriteria();
		criteria.andPrivilPidEqualTo(id);
		List<SysPrivilPO> poList = selectByExample(example);
		for (SysPrivilPO po : poList) {
			num = deleteTreeNode(po.getPrivilId());
		}
		return num+deleteByPrimaryKey(id);
	}

	public JsonPager selectButtonByPageMap(JsonPager jPager, Map param) {
		SysPrivilPOExample example = new SysPrivilPOExample();
		com.system.model.po.SysPrivilPOExample.Criteria c = example.createCriteria();
		 c.andPrivilPidEqualTo(param.get("privilPid").toString());
		return selectByPage(jPager, example);
	}

	public int deleteNodesByPrimaryKeys(String pks, Class clazz) {
		return deleteTreeNode(pks);
	}
	
}
