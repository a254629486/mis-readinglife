 /**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.constants<br/>
 * <b>文件名：</b>UserToken.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年11月11日-下午1:16:40<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.constants;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.util.DigestUtils;

 /**
 * <b>类名称：</b>UserToken<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年11月11日 下午1:16:40<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class UserToken extends UsernamePasswordToken {
	private static final long serialVersionUID = -4636029993163145985L;
	
	private String staffId;
	
	private String JSESSIONID;

	
	public UserToken(String username,char[] password) {
		super(username, password);
	}
	
	public UserToken(String username,String password) {
		super(username, password);
	}
	
	public UserToken(String username,char[] password,String staffId) {
		super(username, password);
		this.staffId = staffId;
	}
	
	public UserToken(String username,String password,String staffId) {
		super(username, password);
		this.staffId = staffId;
	}
	
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	public String getJSESSIONID() {
		return JSESSIONID;
	}
	
	/**
	 * 用户安全信息策略
	 * @Title initJSESSIONID
	 * @Description TODO
	 * @param userLoginName 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年12月13日-下午4:53:41
	 * @update 
	 *
	 */
	public void initJSESSIONID() {
		 JSESSIONID = DigestUtils.md5DigestAsHex((RandomUtils.nextInt(1000)+getUsername()+System.currentTimeMillis()).getBytes());
	}

	
}
