 /**
 * <b>项目名：</b>readinglife-common<br/>
 * <b>包名：</b>com.readinglife.tools<br/>
 * <b>文件名：</b>OrderedProperties.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月29日-下午1:56:18<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.readinglife.tools.properties;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

 /**
 * <b>类名称：</b>OrderedProperties<br/>
 * <b>类描述：</b>有序读取properties<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月29日 下午1:56:18<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class OrderedProperties extends Properties {
    /**
	 * <b>版本信息：</b>v1.0.0<br/>
	 */
	private static final long serialVersionUID = -8219067822178277667L;
	
	private final LinkedHashSet<Object> keys= new LinkedHashSet<Object>();
    public Enumeration<Object> keys() {
        return Collections.<Object> enumeration(keys);
    }

    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    public Set<Object> keySet() {
        return keys;
    }

    public Set<String>stringPropertyNames() {
        Set<String> set = new LinkedHashSet<String>();
        for(Object key : this.keys) {
            set.add((String) key);
        }
        return set;
    }

}
