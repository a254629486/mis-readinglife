 /**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>InitService.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年11月7日-下午1:28:27<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import com.readinglife.framework.service.BaseService;
import com.system.constants.SysConstants;
import com.system.model.po.SysLokcdePO;
import com.system.model.po.SysLokcdePOExample;
import com.system.model.po.SysLoktpePO;
import com.system.model.po.SysPrivilPO;
import com.system.model.po.SysPrivilPOExample;
import com.system.service.InitService;
import com.system.service.SysLokcdeService;
import com.system.service.SysLoktpeService;
import com.system.service.SysPrivilService;
import com.system.util.CachePrivilUtil;

 /**
 * <b>类名称：</b>InitService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年11月7日 下午1:28:27<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@Service("initService")
public class InitServiceImpl extends BaseService implements InitService {

	@Resource
	private MemcachedClient memcachedClient;
	
	@Resource
	private SysPrivilService sysPrivilService;
	
	@Resource
	private SysLokcdeService sysLokcdeService;
	
	@Resource
	private SysLoktpeService sysLoktpeService;
	
	
	@SuppressWarnings("unchecked")
	public void initPrivil() {
		SysPrivilPOExample example = new SysPrivilPOExample();
		com.system.model.po.SysPrivilPOExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(SysConstants.PRIVIL_STATUS_ON);
		List<SysPrivilPO> list = sysPrivilService.selectByExample(example);
		try {
			if(list!=null&&list.size()>0)
				memcachedClient.set("system_base_privil", 0, CachePrivilUtil.getPersonPrivilMap(list));
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void initBaseCode() {
		List<SysLoktpePO> typeList = sysLoktpeService.selectByExample(null);
		SysLokcdePOExample example = new SysLokcdePOExample();
		example.setOrderByClause("loktpe");
		List<SysLokcdePO> codeList = sysLokcdeService.selectByExample(example);
		
		try {
			Map<String,String> type = new HashedMap();
			for (SysLoktpePO sysLoktpePO : typeList) {
				type.put(sysLoktpePO.getLoktpe(), sysLoktpePO.getLoktpeName());
			}
			memcachedClient.set("sys_base_type_name", 0, type);
			Map<String,String> code = new HashedMap();
			for (SysLokcdePO sysLokcdePO : codeList) {
				code.put(sysLokcdePO.getLokcde(), sysLokcdePO.getLokcdeName());
			}
			memcachedClient.set("sys_base_code_name", 0, code);
			Map<String,List<SysLokcdePO>> typecode = new HashedMap();
			String tCode = null;
			List<SysLokcdePO> list = null;
			for (SysLokcdePO sysLokcdePO : codeList) {//注意一定是有序的list才行
				if(tCode==null||!tCode.equals(sysLokcdePO.getLoktpe())){
					list = new ArrayList<SysLokcdePO>();
					typecode.put(sysLokcdePO.getLoktpe(), list);
					tCode = sysLokcdePO.getLoktpe();
				}
				if(tCode.equals(sysLokcdePO.getLoktpe())){
					list.add(sysLokcdePO);
				}
			}
			memcachedClient.set("sys_base_codetype", 0, typecode);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		
	}

}
