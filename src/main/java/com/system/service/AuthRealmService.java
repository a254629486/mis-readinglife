 /**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service<br/>
 * <b>文件名：</b>AuthRealmService.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月31日-上午10:47:06<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.service;

import java.util.List;

import com.readinglife.framework.service.IBaseService;
import com.system.model.po.SysPrivilPO;
import com.system.model.vo.SysStaffVO;

 /**
 * <b>类名称：</b>AuthRealmService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月31日 上午10:47:06<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public interface AuthRealmService extends IBaseService{

	/**
	 * @Title 获取缺省岗位
	 * @Description TODO
	 * @param staffId
	 * @return 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年10月31日-上午10:52:47
	 * @update 
	 */
	public SysStaffVO getDefaultPositi(String staffId);
	
	public List<SysPrivilPO> getPrivilByStaffId(String staffId,String positiId);
	
}
