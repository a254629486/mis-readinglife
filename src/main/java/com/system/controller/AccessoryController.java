package com.system.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.readinglife.framework.web.BaseController;
import com.readinglife.tools.UUIDGenerator;
import com.system.model.po.SysAccessoryPO;
import com.system.service.AccessoryService;

@Controller
@RequestMapping("accessory")
public class AccessoryController extends BaseController{
	Logger logger = Logger.getLogger(AccessoryController.class);   
	@Autowired
	AccessoryService accessoryService;
	@Autowired
	MemcachedClient memcachedClient;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public ModelAndView  upload( HttpServletRequest request,String orderId, @RequestParam("filedata") MultipartFile file){
		logger.debug(">>>upload(request,orderId:"+orderId+",file)");
        try{  
        	String separator=System.getProperty("file.separator");
        	Map map = super.getPerson(request, memcachedClient);
    		Map personPO = (Map) map.get("personPO");
        	String loginName=(String) personPO.get("loginName");
        	String path=separator+"upload"+separator+loginName;
        	
            String uploadDir = request.getRealPath(path);   
            File dirPath = new File(uploadDir);   
            if (!dirPath.exists()) {   
                dirPath.mkdirs();   
            }   
            InputStream stream = file.getInputStream();   
            String fileName = file.getOriginalFilename();   
           // fileName = new String(fileName.getBytes(),"utf-8");   
            String suffix=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
            String name=fileName.substring(0,fileName.lastIndexOf("."));
            String accessoryId=UUIDGenerator.getUUID();
           
            String fileNameFull = uploadDir + separator + accessoryId+"."+suffix;  
            OutputStream bos = new FileOutputStream(fileNameFull);   
            int bytesRead = 0;   
            byte[] buffer = new byte[8192];   
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {   
                bos.write(buffer, 0, bytesRead);   
            }   
            bos.close();   
            // close the stream   
            stream.close();
            SysAccessoryPO sysAccessoryPO=new SysAccessoryPO();
            sysAccessoryPO.setAccessoryId(accessoryId);
            sysAccessoryPO.setOrderId(orderId);//order id
            sysAccessoryPO.setName(name);
            sysAccessoryPO.setSuffix(suffix);
            sysAccessoryPO.setPath(path);
            sysAccessoryPO.setSize(file.getSize());
            sysAccessoryPO.setUploadDate(new Date());
            this.accessoryService.saveSysAccessoryPO(sysAccessoryPO);
            
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView();  
 //       mv.setViewName("//bug/create");  
        mv.setViewName("//sys/data/testUpload");  
        logger.debug("<<<upload(request,orderId:"+orderId+",file)");
        return mv;  
	}
    @RequestMapping("/download/{accessoryId}")   
    public ModelAndView   download(@PathVariable("accessoryId") String accessoryId, HttpServletRequest request, HttpServletResponse response) throws Exception {   
        response.setContentType("text/html;charset=utf-8");   
        request.setCharacterEncoding("UTF-8");   
        java.io.BufferedInputStream bis = null;   
        java.io.BufferedOutputStream bos = null;   
        String separator=System.getProperty("file.separator");
        SysAccessoryPO sysAccessoryPO=this.accessoryService.geteSysAccessoryPO(accessoryId);
        
        String ctxPath = request.getSession().getServletContext().getRealPath("");   
        String downLoadPath = ctxPath +sysAccessoryPO.getPath() +separator+sysAccessoryPO.getAccessoryId()+"."+sysAccessoryPO.getSuffix();   
        try {   
            long fileLength = new File(downLoadPath).length();  
            String downloadFileName=sysAccessoryPO.getName()+"."+sysAccessoryPO.getSuffix();   
            response.setContentType("application/octet-stream;charset=UTF-8");   
            response.setHeader("Content-disposition", "attachment; filename="  
                    + new String(downloadFileName.getBytes("utf-8"), "ISO8859-1"));   
            response.setHeader("Content-Length", String.valueOf(fileLength));   
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));   
            bos = new BufferedOutputStream(response.getOutputStream());   
            byte[] buff = new byte[2048];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (bis != null)   
                bis.close();   
            if (bos != null)   
                bos.close();   
        }
        return null;
        
    }
	
	@ResponseBody
	@RequestMapping(value="/accessorys/json")
	public List<SysAccessoryPO> accessorys(HttpServletRequest request,String orderId){
		List<SysAccessoryPO> ls=this.accessoryService.getSysAccessoryPOList(orderId);
		return ls;
	}
	
	@ResponseBody
	@RequestMapping(value="/accessorys/{accessoryId}/delete")
	public String delete(@PathVariable String accessoryId,HttpServletRequest request){
		String separator=System.getProperty("file.separator");
		SysAccessoryPO sysAccessoryPO=this.accessoryService.geteSysAccessoryPO(accessoryId);
		String uploadDir = request.getRealPath(sysAccessoryPO.getPath()); 
		String fileNameFull = uploadDir + separator + accessoryId+"."+sysAccessoryPO.getSuffix();  
		File file = new File(fileNameFull); 
		if (file.exists()) {
			file.delete();
			this.accessoryService.deleteSysAccessoryPO(accessoryId);			
		} 
		return "success";
	}

	
}
