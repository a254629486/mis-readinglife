 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.controller<br/>
 * <b>文件名：</b>SysPersonStaffController.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.handler.annotation.MessageType;
import org.springframework.web.handler.annotation.ReturnMessageType;
import org.springframework.web.handler.bean.ResultBean;
import org.springframework.web.method.annotation.ForBean;

import com.readinglife.framework.exceptions.MessageException;
import com.readinglife.framework.web.BaseController;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.UUIDGenerator;
import com.readinglife.tools.json.Jacksons;
import com.readinglife.tools.string.StringUtil;
import com.system.model.po.SysStaffPO;
import com.system.model.vo.SysPersonStaffVO;
import com.system.model.vo.SysStaffPositiSaveVO;
import com.system.service.SynchWorkflowDataService;
import com.system.service.SysDepartService;
import com.system.service.SysPersonStaffService;
import com.system.service.SysPositiService;

/**
 * <b>类名称：</b>SysPersonStaffController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@Controller
@RequestMapping("/sysPersonStaffController")
public class SysPersonStaffController extends BaseController{  
	
	private final String URL_MAOOING =  "/sysPersonStaffController/";
	
	@Autowired
	SysPersonStaffService sysPersonStaffService;
	
	@Autowired
	SysDepartService sysDepartService;
	
	@Autowired
	SysPositiService sysPositiService;
	
	@Autowired
	SynchWorkflowDataService synchWorkflowDataService;
	
	@Autowired
	MemcachedClient memcachedClient;
	
	@RequestMapping("/toSearchList")
	public String toSearchList(HttpServletRequest request) {
	   return "//sys/person/sys_person_staff_list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/save")
	@ResponseBody@ReturnMessageType(result=ResultBean.class,value=MessageType.BODY)
	public String save(@ForBean SysStaffPO sysStaffPO,@ForBean SysStaffPositiSaveVO sysStaffPositiSaveVO) throws MessageException {
		if(!StringUtil.isNotBlank(sysStaffPO.getStaffId())){
			sysStaffPO.setStaffId(UUIDGenerator.getUUID());
			sysPersonStaffService.save(sysStaffPO,sysStaffPositiSaveVO);
//			//同步工作流数据
//			SysDepartPO sysDepartPO = (SysDepartPO) sysDepartService.selectByPrimaryKey(sysStaffPO.getDepartId());
//			SysPositiPO sysPositiPO = (SysPositiPO) sysPositiService.selectByPrimaryKey(sysStaffPO.getPositiId());
//			Map obj = new HashMap();
//			obj.put("id", sysStaffPO.getStaffId());
//			obj.put("firstName", sysDepartPO.getName()+":"+sysPositiPO.getName());
//			obj.put("lastName", sysPersonPO.getName());
//			obj.put("email", sysPersonPO.getEmail());
//			obj.put("password", sysPersonPO.getLoginPwd());
//			synchWorkflowDataService.postStaff(obj);
		}else {
			sysPersonStaffService.update(sysStaffPO,sysStaffPositiSaveVO);
//			//同步工作流数据
//			SysDepartPO sysDepartPO = (SysDepartPO) sysDepartService.selectByPrimaryKey(sysStaffPO.getDepartId());
//			SysPositiPO sysPositiPO = (SysPositiPO) sysPositiService.selectByPrimaryKey(sysStaffPO.getPositiId());
//			Map obj = new HashMap();
//			obj.put("id", sysStaffPO.getStaffId());
//			obj.put("firstName", sysDepartPO.getName()+":"+sysPositiPO.getName());
//			obj.put("lastName", sysPersonPO.getName());
//			obj.put("email", sysPersonPO.getEmail());
//			obj.put("password", sysPersonPO.getLoginPwd());
//			synchWorkflowDataService.putStaff(obj);
		}
		return result(true, SAVE_SUCCESS);
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(HttpServletRequest request) {
		Map param = formatParam(request);
		sysPersonStaffService.deleteByPrimaryKeys(param.get("pkId").toString());
		//同步工作流数据
		synchWorkflowDataService.delStaff(param.get("pkId").toString());
		return result(true, DELETE_SUCCESS);
	}
	
	@RequestMapping("/changeDepart")
	@ResponseBody
	public String changeDepart(HttpServletRequest request) {
		Map param = formatParam(request);
		sysPersonStaffService.changeDepart(param.get("staffId").toString(), param.get("departId").toString());
		return result(true, "");
	}
	
	@RequestMapping("/searchList")
	@ResponseBody
	public String searchList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysPersonStaffVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager = sysPersonStaffService.selectByPageMap(jPager,param);
	   return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/searchStaffPositiList")
	@ResponseBody
	public String searchStaffPositiList(HttpServletRequest request,Model model) throws Exception {
		Map param = formatParam(request);
		Map<String, Object> map = getPerson(request, memcachedClient);
		param.put("staffId",  map.get("staffId"));
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager.setPage(0);
		jPager = sysPersonStaffService.selectByPageMap(jPager,param);
		return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
}
