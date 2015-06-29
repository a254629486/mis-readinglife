 /**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.constants<br/>
 * <b>文件名：</b>SysConstants.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月16日-上午10:05:03<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.constants;

import java.util.HashMap;
import java.util.Map;

 /**
 * <b>类名称：</b>SysConstants<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月16日 上午10:05:03<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class SysConstants {
	
	public final static Map<String,Map<String,String>> ROOT_MAP = new HashMap<String,Map<String,String>>();
	/**员工默认登录岗位身份 */
	public final static String STAFF_STATUS_DEFUL = "1";
	/**一般员工身份*/
	public final static String STAFF_STATUS = "2";
	
	/**权限-启用 */
	public final static String PRIVIL_STATUS_ON = "1";
	/**权限-关闭 */
	public final static String PRIVIL_STATUS_OFF = "2";
	/**人员-启用 */
	public final static String PERSON_STATUS_ON = "1";
	/**人员-关闭 */
	public final static String PERSON_STATUS_OFF = "2";
	/**工作流-启用 */
	public final static String WORKFLOW_STATUS_ON = "1";
	/**工作流-关闭 */
	public final static String WORKFLOW_STATUS_OFF = "2";
	
	
	public final static String SYS_PRIVIL_PID = "85E2E32EE5ED4735B26C036C598C95B6";
	
	/**
	 * 角色状态
	 */
	public final static Map<String,String> ROLE = new HashMap<String,String>();
	/**
	 * 人员状态
	 */
	public final static Map<String,String> PERSON = new HashMap<String,String>();
	/**
	 * 员工状态
	 */
	public final static Map<String,String> STAFF = new HashMap<String,String>();
	/**
	 * 性别
	 */
	public final static Map<String,String> SEX = new HashMap<String,String>();
	/**
	 * 权限状态
	 */
	public final static Map<String,String> PRIVIL = new HashMap<String,String>();
	/**
	 * 权限类别
	 */
	public final static Map<String,String> PRIVIL_TYPE = new HashMap<String,String>();
	
	/**
	 * 权限类别 操作按钮
	 */
	public final static Map<String,String> PRIVIL_TYPE_BUTTON = new HashMap<String,String>();
	
	/**
	 * 公司类别
	 */
	public final static Map<String,String> COMPANY = new HashMap<String,String>();
	/**
	 * 部门状态
	 */
	public final static Map<String,String> DEPART = new HashMap<String,String>();
	/**
	 * 岗位状态
	 */
	public final static Map<String,String> POSITI = new HashMap<String,String>();
	
	/**
	 * 工作流状态
	 */
	public final static Map<String,String> WORKFLOW = new HashMap<String,String>();
	
	static{
		ROLE.put("1", "启用");
		ROLE.put("2", "关闭");
		
		PERSON.put(PERSON_STATUS_ON, "启用");
		PERSON.put(PERSON_STATUS_OFF, "关闭");
		
		STAFF.put(STAFF_STATUS_DEFUL, "默认岗位");
		
		SEX.put("1", "男");
		SEX.put("2", "女");
		
		PRIVIL.put(PRIVIL_STATUS_ON,"启用");
		PRIVIL.put(PRIVIL_STATUS_OFF,"关闭");

		PRIVIL_TYPE.put("1", "根菜单");
		PRIVIL_TYPE.put("2", "一级菜单");
		PRIVIL_TYPE.put("3", "功能菜单");
		PRIVIL_TYPE.put("5", "目录菜单");
		
		PRIVIL_TYPE_BUTTON.put("4", "操作元素");
		PRIVIL_TYPE_BUTTON.put("6", "导出EXCEL操作");
		PRIVIL_TYPE_BUTTON.put("7", "导入EXCEL操作");
		PRIVIL_TYPE_BUTTON.put("8", "新建操作");
		PRIVIL_TYPE_BUTTON.put("9", "修改操作");
		PRIVIL_TYPE_BUTTON.put("10", "删除操作");
		PRIVIL_TYPE_BUTTON.put("11", "查看操作");
		
	
		COMPANY.put("1", "启用");
		COMPANY.put("2", "关闭");
		
		DEPART.put("1", "启用");
		DEPART.put("2", "关闭");
		
		POSITI.put("1", "启用");
		POSITI.put("2", "关闭");
		
		
		WORKFLOW.put(WORKFLOW_STATUS_ON, "启用");
		WORKFLOW.put(WORKFLOW_STATUS_OFF, "关闭");
		
		
		//添加节点
		ROOT_MAP.put("ROLE", ROLE);
		ROOT_MAP.put("PERSON", PERSON);
		ROOT_MAP.put("STAFF", STAFF);
		ROOT_MAP.put("SEX", SEX);
		ROOT_MAP.put("PRIVIL", PRIVIL);
		ROOT_MAP.put("PRIVIL_TYPE", PRIVIL_TYPE);
		ROOT_MAP.put("PRIVIL_TYPE_BUTTON", PRIVIL_TYPE_BUTTON);
		ROOT_MAP.put("COMPANY", COMPANY);
		ROOT_MAP.put("DEPART", DEPART);
		ROOT_MAP.put("POSITI", POSITI);
		ROOT_MAP.put("WORKFLOW", WORKFLOW);
	}

}
