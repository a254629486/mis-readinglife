 /**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.tools<br/>
 * <b>文件名：</b>CachePrivilUtil.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年11月7日-下午2:41:03<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.DigestUtils;

import com.system.model.po.SysPrivilPO;


 /**
 * <b>类名称：</b>CachePrivilUtil<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年11月7日 下午2:41:03<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class CachePrivilUtil {
	/**
     * @Title 用户权限Hash地址
     * @Description TODO
     * @param po
     * @return 
     * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
     * @date 2013年10月31日-下午1:22:21
     * @update 
     */
    public static Map<String,String> getPersonPrivilMap(List<SysPrivilPO> SysPrivilPOs ){
    	Map<String,String> map = new HashMap<String,String>();
    	for (SysPrivilPO sysPrivilPO : SysPrivilPOs) {
			map.put(DigestUtils.md5DigestAsHex(sysPrivilPO.getPrograCode().getBytes()), sysPrivilPO.getPrograCode());
			map.put(sysPrivilPO.getPrivilId(), sysPrivilPO.getPrograCode());
		}
		return map.size()>0?map:null;
    }
    
}