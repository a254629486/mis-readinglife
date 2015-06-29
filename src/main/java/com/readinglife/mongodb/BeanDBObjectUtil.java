package com.readinglife.mongodb;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

//import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


/**
 * <b>类名称：</b>BeanMapUtil<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月6日 上午11:34:57<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */
public class BeanDBObjectUtil {

   /**  
    * 将一个 JavaBean 对象转化为一个 DBObject  
    *   
    * @param bean  
    *            要转化的JavaBean 对象  
    * @return 转化出来的 Map 对象  
    * @throws Exception 
    * @throws IntrospectionException  
    *             如果分析类属性失败  
    * @throws IllegalAccessException  
    *             如果实例化 JavaBean 失败  
    * @throws InvocationTargetException  
    *             如果调用属性的 setter 方法失败  
    * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
    * @date 2013年9月6日-上午11:35:37
    * @update 
    */  
   /*@SuppressWarnings({"rawtypes" })   
   public static DBObject convertBean2DBObject(Object bean) throws Exception{   
	   DBObject dbObject =null;
	   try{
	       Class type = bean.getClass();   
	       dbObject = new BasicDBObject();
	       BeanInfo beanInfo = Introspector.getBeanInfo(type);   
	       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();   
	       for (int i = 0; i < propertyDescriptors.length; i++) {   
	           PropertyDescriptor descriptor = propertyDescriptors[i];   
	           String propertyName = descriptor.getName();   
	           if (!propertyName.equalsIgnoreCase("class")) {   
	               Method readMethod = descriptor.getReadMethod();   
	               Object result = readMethod.invoke(bean, new Object[0]);   
	               if (result != null) {   
	            	   if(result instanceof BigDecimal){
	            		   result = result==null?"":result.toString();
	            	   }
	            	   dbObject.put(propertyName, result);   
	               } else {   
	            	   dbObject.put(propertyName, null);   
	               }   
	           }   
	       }   
	   }catch(Exception e){
		   //解析错误时抛出服务器异常 记录日志
		   throw new  Exception("从bean转换为DBObject时异常!",e);
	   }
       return dbObject;   
   }   */
   
}  
