package com.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.readinglife.framework.web.BaseController;
import com.readinglife.tools.UUIDGenerator;
import com.system.model.po.SysDataRolePO;
import com.system.service.SysDataRoleService;

@Controller
@RequestMapping("/SysDataRoleController")
public class SysDataRoleController extends BaseController{
	
	@Autowired
	SysDataRoleService sysDataRoleService;
	
	@RequestMapping("/SysDataRoleList")
    public String calendar(HttpServletRequest request) {
        return "/sys/role/sys_data_role";
    }
	
	@ResponseBody
	@RequestMapping("/dataRoleList")
	public Map dataRoleList(HttpServletRequest request,int page ,int rows){
		Map map=this.formatParam(request);
		map.put("page", (page-1)*rows);
		map.put("rows", rows);
		List<SysDataRolePO> dataRoleList=this.sysDataRoleService.getDataRoleList(map);
		int total=this.sysDataRoleService.getDataRoleListTotal(map);
		Map returnmap = new HashMap();
		returnmap.put("rows", dataRoleList);
		returnmap.put("total", total);
		return returnmap;
	}
	@ResponseBody
	@RequestMapping("/addsave")
	public String addsave(HttpServletRequest request){
		String dataRoleId=request.getParameter("dataRoleId");
		SysDataRolePO sysDataRolePO=new SysDataRolePO();
		String code1=request.getParameter("code");
		String code=code1.trim();
		String name1=request.getParameter("name");
		String name=name1.trim();
		sysDataRolePO.setCode(code);
		sysDataRolePO.setName(name);
		sysDataRolePO.setStatus(request.getParameter("status"));
		if (dataRoleId==null||dataRoleId.equals("")){
			sysDataRolePO.setDataRoleId(UUIDGenerator.getUUID());
			int result=this.sysDataRoleService.addDataRole(sysDataRolePO);
		}
		else{
			sysDataRolePO.setDataRoleId(dataRoleId);
			int result=this.sysDataRoleService.update(sysDataRolePO);
		}
		return "success";
		
	}
	@ResponseBody
	@RequestMapping("/dataremove/{dataroleid}")
	public String dataremove(@PathVariable String dataroleid){
		int result = 0;
		try {
			result = sysDataRoleService.dataremove(dataroleid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (result == 1) {
			return result(true, DELETE_SUCCESS);
		}else{
			return result(false, DELETE_ERROR);
		}
		
	}
}