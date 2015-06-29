/**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service<br/>
 * <b>文件名：</b>SysPositiRoleService.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 * 
 */
package com.system.service;

import java.util.Map;

import com.readinglife.framework.service.IBaseService;
import com.readinglife.framework.web.JsonPager;
import com.system.model.po.SysPositiPO;

/**
 * <b>类名称：</b>SysPositiRoleService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
public interface SysPositiRoleService extends IBaseService {
    JsonPager selectByPageMap(JsonPager jPager, Map param);

    JsonPager selectedByPageMap(JsonPager jPager, Map param);

    JsonPager selectDataByPageMap(JsonPager jPager, Map param);

    JsonPager selectedDataByPageMap(JsonPager jPager, Map param);

    JsonPager selectPrivilByPageMap(JsonPager jPager, Map param);

    public void save(String positiPrivilId, String positiRoleId, String positiRoleDataId, String addorupdate,
        SysPositiPO sysPositiPO);
}
