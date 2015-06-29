package com.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.readinglife.framework.service.BaseService;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.UUIDGenerator;
import com.system.model.po.SysDataPrivilPO;
import com.system.model.po.SysDataRolePO;
import com.system.model.po.SysDataRolePOExample;
import com.system.service.SysDataService;
@Service
public class SysDataServiceImpl extends BaseService implements
		SysDataService {

	@Override
	public Map<String, Object> dataSysList() {
//		List<String> list = this.getAllDataSourceKey();
//		for (String string : list) {
//			System.out.println("数据源key="+string);
//			List<HashMap<String,String>> tablesName = this.selectList(string, "com.readinglife.b2b.sys.dao.impl.SysDataDAO.queryTable", null);
//			for (HashMap<String, String> hashMap : tablesName) {
//				System.out.println(hashMap.get("Tables_in_"+string));
//			}
//		}
//		System.out.println("-----------------------------------------------");
//		System.out.println("-----------------------------------------------");
//		List<HashMap<String,String>> columns =this.selectList("com.readinglife.b2b.sys.dao.impl.SysDataDAO.qAllColumns", list);
//		for (HashMap<String, String> hashMap : columns) {
//			System.out.println(hashMap);
//		}
		return null;
	}

	@Override
	public List<Map<String, String>> tableList(String[] databases) {
//		List<String> list = this.getAllDataSourceKey();
		List<Map<String,String>> tables =this.selectList("com.system.dao.impl.SysDataDAO.qTableByDB", databases);
		return tables;
	}
	@SuppressWarnings("serial")
	public List<Map<String,String>> columnsListByTable(final String dbname,final String tableName){
		return this.selectList("com.system.dao.impl.SysDataDAO.qColumnsByTable", new HashMap<String,String>(){
		{
			put("dbName",dbname);
			put("tableName",tableName);
		}});
	}

	@Override
	public List<SysDataRolePO> getRoleList() {
		SysDataRolePOExample sd = new SysDataRolePOExample();
		sd.createCriteria().andStatusEqualTo("1");
		return selectList("com.system.dao.impl.SysDataRolePOMapper.selectByExample", sd);
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public JsonPager<?> getRoleDataList(final JsonPager<?> jPager,Map<String,Object> parmMap) {
		return selectByPage("com.system.dao.impl.SysDataDAO.qRoleDataList", "com.system.dao.impl.SysDataDAO.qRoleDataListCnt", jPager, parmMap);
	}

	@SuppressWarnings("serial")
	@Override
	@Transactional
	public void save(final SysDataPrivilPO sysDataPrivilPO, final String dataRoleId) {
		sysDataPrivilPO.setDataPrivilId(UUIDGenerator.getUUID()	);
		this.insert("com.system.dao.impl.SysDataPrivilPOMapper.insert", sysDataPrivilPO);
		this.insert("com.system.dao.impl.SysDataDAO.saveRoleDataPriv", new HashMap<String,String>(){{
			put("dataRoleId",dataRoleId);
			put("dataPrivilId",sysDataPrivilPO.getDataPrivilId());
		}});
	}
	@Override
	@Transactional
	public void remove(String dataPrivId) {
		this.delete("com.system.dao.impl.SysDataDAO.dDataRolePriv", dataPrivId);
		this.delete("com.system.dao.impl.SysDataPrivilPOMapper.deleteByPrimaryKey", dataPrivId);
	}

	@Override
	@Transactional
	public void update(SysDataPrivilPO sysDataPrivilPO) {
		this.update("com.system.dao.impl.SysDataPrivilPOMapper.updateByPrimaryKeySelective", sysDataPrivilPO);
	}
	
	
}
