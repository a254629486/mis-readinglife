package com.readinglife.tools.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.readinglife.tools.date.DateUtil;
import com.readinglife.tools.string.StringUtil;


/**
 * <b>类名称：</b>BeanMapUtil<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月6日 上午11:34:57<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */
public class BeanMapUtil {

	/**
	 * @Title convertMap2Bean
	 * @Description TODO
	 * @param type 
	 * @param map
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年9月6日-上午11:35:20
	 * @update 
	 *
	 */
   @SuppressWarnings({ "rawtypes" })   
   public static Object convertMap2Bean(Class type, Map map) throws Exception {   
	   Object obj=null;
	   try{
	       BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性   
	       obj = type.newInstance(); // 创建 JavaBean 对象   
	       // 给 JavaBean 对象的属性赋值   
	       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();   
	       for (int i = 0; i < propertyDescriptors.length; i++) {   
	           PropertyDescriptor descriptor = propertyDescriptors[i];   
	           String propertyName = descriptor.getName();   
	 
	           if (map.containsKey(propertyName)) {   
	               // 下面一句可以 try 起来,这样当一个属性赋值失败的时候就不会影响其他属性赋值.   
	               Object value = map.get(propertyName);
	               if(null == value)continue;
	               Object[] args = new Object[1];   
	               Class propertyType = descriptor.getPropertyType(); 
	               args[0]=getPropertyTypeObj(propertyType,value); 
	               descriptor.getWriteMethod().invoke(obj, args);   
	           }   
	       }   
	   }catch(Exception e){
		   //解析错误时抛出服务器异常 记录日志
		   throw new Exception("从map转换为bean时异常!",e);
	   }
       return obj;   
   }  
   
   /**
    * 
    * @Title forBeanProperty
    * @Description TODO
    * @param type
    * @param map
    * @return
    * @throws Exception 
    * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
    * @date 2013年9月13日-下午1:41:34
    * @update 
    *
    */
   public static Map forBeanProperty(Class type, Map map) throws Exception {   
	   try{
	       BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性   
	       // 给 JavaBean 对象的属性赋值   
	       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();   
	       for (int i = 0; i < propertyDescriptors.length; i++) {   
	           PropertyDescriptor descriptor = propertyDescriptors[i];   
	           String propertyName = descriptor.getName();   
	           if (map.containsKey(propertyName)) {   
	               // 下面一句可以 try 起来,这样当一个属性赋值失败的时候就不会影响其他属性赋值.   
	               Object value = map.get(propertyName);
	               if(null == value)continue;
	               Class propertyType = descriptor.getPropertyType(); 
	               map.put(propertyName, getPropertyTypeObj(propertyType,value));
	           }   
	       }   
	   }catch(Exception e){
		   //解析错误时抛出服务器异常 记录日志
		   throw new Exception("从map转换为bean属性时异常!",e);
	   }
       return map;   
   }
   
 
   /**
    * 功能描述： 根据类型转
    * @Title getPropertyTypeObj
    * @Description TODO
    * @param propertyType
    * @param obj
    * @return 
    * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
    * @date 2013年9月6日-上午11:35:37
    * @update 
    */
	@SuppressWarnings("rawtypes")
	private static Object getPropertyTypeObj(Class propertyType,Object obj){
	   if(propertyType == String.class){
    	   return obj.toString();
       }else if(propertyType == Long.class){
    	   if(obj instanceof Long ) {
    		   return (Long)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return Long.valueOf(obj.toString());
       }else  if(propertyType == Integer.class){
    	   if(obj instanceof Integer ) {
    		   return (Integer)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return Integer.valueOf(obj.toString());
       }else  if(propertyType == Character.class){
    	   if(obj instanceof Character  ) {
    		   return (Character)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return (Character)obj;
       }else  if(propertyType == byte[].class){
    	   if(obj instanceof byte[]  ) {
    		   return (byte[])obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return (byte[])obj;
       }else  if(propertyType == Byte.class){
    	   if(obj instanceof Byte ) {
    		   return (Byte)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return Byte.valueOf(obj.toString());
       }else  if(propertyType == Short.class){
    	   if(obj instanceof Short ) {
    		   return (Short)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return Short.valueOf(obj.toString());
       }else  if(propertyType == java.math.BigInteger.class){
    	   if(obj instanceof java.math.BigInteger ) {
    		   return (java.math.BigInteger)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return new java.math.BigInteger(obj.toString());
       }else  if(propertyType == java.math.BigDecimal.class){
    	   if(obj instanceof java.math.BigDecimal ) {
    		   return (java.math.BigDecimal)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return new java.math.BigDecimal(obj.toString());
       }else  if(propertyType == Boolean.class){
    	   if(obj instanceof Boolean ) {
    		   return (Boolean)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return Boolean.valueOf(obj.toString());
       } else  if(propertyType == Float.class){
    	   if(obj instanceof Float ) {
    		   return (Float)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return Float.valueOf(obj.toString());
       }else  if(propertyType == Double.class){
    	   if(obj instanceof Double ) {
    		   return (Double)obj;
    	   }
    	   if("".equals(obj)){
    		   return null;
    	   }
    	   return Double.valueOf(obj.toString());
       }else  if(propertyType == Timestamp.class){
    	   if(obj instanceof Timestamp ) {
    		   return (Timestamp)obj;
    	   }
    	   String s = obj.toString();
    	   int dividingSpace = s.indexOf(' ');
    	   //日期中没有时间进行补齐操作
    		if (dividingSpace <= 0) {
    		     s+=" 00:00:00";
    		} 
    	   return Timestamp.valueOf(s);
       }else  if(propertyType == Date.class){
    	   if(obj instanceof Date ) {
    		   return (Date)obj;
    	   }
    	   String s = obj.toString();
    	   int dividingSpace = s.indexOf(' ');
    	   //日期中没有时间进行补齐操作
    		if (dividingSpace <= 0) {
    		     s+=" 00:00:00";
    		} 
    	   return DateUtil.StringToDate(s, "yyyy-mm-dd hh:MM:ss");
       }else  if(propertyType == java.sql.Time.class){
    	   if(obj instanceof java.sql.Time ) {
    		   return (java.sql.Time)obj;
    	   }
    	   String s = obj.toString();
    	   int dividingSpace = s.indexOf(' ');
    	   //日期中没有时间进行补齐操作
    		if (dividingSpace <= 0) {
    		     s+=" 00:00:00";
    		} 
    	   return java.sql.Time.valueOf(s);
       }
	   return null;
   }
   /**  
    * 将一个 JavaBean 对象转化为一个 Map  
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
   @SuppressWarnings({ "unchecked", "rawtypes" })   
   public static Map convertBean2Map(Object bean) throws Exception{   
	   Map returnMap =null;
	   try{
	       Class type = bean.getClass();   
	       returnMap = new HashMap();   
	       BeanInfo beanInfo = Introspector.getBeanInfo(type);   
	       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();   
	       for (int i = 0; i < propertyDescriptors.length; i++) {   
	           PropertyDescriptor descriptor = propertyDescriptors[i];   
	           String propertyName = descriptor.getName();   
	           if (!propertyName.equalsIgnoreCase("class")) {   
	               Method readMethod = descriptor.getReadMethod();   
	               Object result = readMethod.invoke(bean, new Object[0]);   
	               if (result != null) {   
	                   returnMap.put(propertyName, result);   
	               } else {   
	                   returnMap.put(propertyName, "");   
	               }   
	           }   
	       }   
	   }catch(Exception e){
		   //解析错误时抛出服务器异常 记录日志
		   throw new  Exception("从bean转换为map时异常!",e);
	   }
       return returnMap;   
   }   
 
   
   public static void main(String[] args) {
		
		Object object = getPropertyTypeObj(Boolean.class, true); 
	   System.out.print(object);
	}
   
}  
