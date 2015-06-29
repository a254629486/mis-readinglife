 /**
 * <b>项目名：</b>readinglife-common<br/>
 * <b>包名：</b>com.readinglife.framework.exceptions.handler<br/>
 * <b>文件名：</b>ExceptionCommonHandler.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年9月24日-上午11:41:37<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.readinglife.framework.exceptions.handler;

import static com.readinglife.framework.exceptions.messages.Messages.getString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.core.Conventions;
import org.springframework.web.handler.ExtensionsSimpleMappingExceptionResolver;
import org.springframework.web.supper.Result;

import com.readinglife.framework.exceptions.MessageException;
import com.readinglife.framework.exceptions.ServerException;
import com.readinglife.framework.exceptions.SystemException;
import com.readinglife.framework.exceptions.supper.ExceptionType;
import com.readinglife.tools.json.JacksonMapper;
import com.readinglife.tools.string.StringUtil;

 /**
 * <b>类名称：</b>ExceptionCommonHandler<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月24日 上午11:41:37<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class ExceptionCommonHandler extends
		ExtensionsSimpleMappingExceptionResolver {

	@Override
	protected String writeValueAsString(HttpServletRequest request, HttpServletResponse response,
			Object object, Exception ex) throws Throwable {
		return JacksonMapper.getInstance().writeValueAsString(result); 
	}
	
	@Override
	protected void writeValueResult(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception ex) {
		super.writeValueResult(request, response, object, ex);
			if(ex instanceof MyBatisSystemException){
				ConstraintViolationException constraintViolationException = getConstraintViolationException(ex);
				if(null!=constraintViolationException){
					result.setMessage(constraintViolationException.getMessage());
					result.setState(ExceptionType.VALIDATOR.toString());
				}else {
					setMessage(ex, ExceptionType.ERROR);
				}
			}else if(ex instanceof MessageException){
				setMessage(ex, ExceptionType.MESSAGE);
			}else if(ex instanceof ServerException){
				setMessage(ex, ExceptionType.SERVER);
			}else if(ex instanceof SystemException){
				setMessage(ex, ExceptionType.SYSTEM);
			}else {
				setMessage(ex, ExceptionType.ERROR);
			}
	}
	
	private ConstraintViolationException getConstraintViolationException(Throwable ex){
		if(ex.getCause() instanceof ConstraintViolationException){
			return (ConstraintViolationException) ex.getCause();
		}else {
			return getConstraintViolationException(ex.getCause());
		}
	}
	
	private void setMessage(Exception ex,ExceptionType exceptionType) {
		if(StringUtil.isNotBlank(ex.getMessage())){
			if(ex.getMessage().equals((getString(ex.getMessage())))){
				result.setMessage(getString(exceptionType.toString()));
			}else {
				result.setMessage(ex.getMessage());
			}
			result.setState(exceptionType.toString());
		}else {
			result.setMessage(getString(exceptionType.toString()));
			result.setState(exceptionType.toString());
		}
	}
	
}
