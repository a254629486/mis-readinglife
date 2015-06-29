/**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>AuthRealmServiceImpl.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月31日-上午10:47:58<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.readinglife.framework.service.BaseService;
import com.readinglife.tools.string.StringUtil;
import com.system.constants.SysConstants;
import com.system.model.po.SysPrivilPO;
import com.system.model.vo.SysStaffVO;
import com.system.service.AuthRealmService;

/**
 * <b>类名称：</b>AuthRealmServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月31日 上午10:47:58<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@Service("authRealmService")
public class AuthRealmServiceImpl extends BaseService implements
		AuthRealmService {

	public SysStaffVO getDefaultPositi(String staffId) {
		Map map = new HashMap();
		if(StringUtil.isNotBlank(staffId)){
			map.put("staffId", staffId);
		}else {
			map.put("status", SysConstants.STAFF_STATUS_DEFUL);
		}
		List<SysStaffVO> list = selectList("com.system.dao.impl.AuthRealmDAO.selectPerson", map);
		return (list!=null&&list.size()>0)?list.get(0):null;
	}

	public List<SysPrivilPO> getPrivilByStaffId(String staffId,String positiId) {
		Map map = new HashMap();
		map.put("staffId", staffId);
		map.put("positiId", positiId);
		return selectList("com.system.dao.impl.AuthRealmDAO.selectPersonpRrivil", map);
	}


}
