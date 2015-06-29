 /**
 * <b>项目名：</b>readinglife-common<br/>
 * <b>包名：</b>com.readinglife.tools<br/>
 * <b>文件名：</b>ReflectUtil.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年9月4日-下午3:39:34<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.readinglife.tools.reflect;

import java.lang.reflect.Field;
import java.util.regex.Pattern;


 /**
 * <b>类名称：</b>ReflectUtil<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月4日 下午3:39:34<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class ReflectUtil {  
    /** 
     * 利用反射获取指定对象的指定属性 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @return 目标属性的值 
     */  
    public static Object getFieldValue(Object obj, String fieldName) {  
        Object result = null;  
        Field field = ReflectUtil.getField(obj, fieldName);  
        if (field != null) {  
           field.setAccessible(true);  
           try {  
               result = field.get(obj);  
           } catch (IllegalArgumentException e) {  
               // TODO Auto-generated catch block  
               e.printStackTrace();  
           } catch (IllegalAccessException e) {  
               // TODO Auto-generated catch block  
               e.printStackTrace();  
           }  
        }  
        return result;  
    }  
     
    /** 
     * 利用反射获取指定对象里面的指定属性 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @return 目标字段 
     */  
    private static Field getField(Object obj, String fieldName) {  
        Field field = null;  
       for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {  
           try {  
               field = clazz.getDeclaredField(fieldName);  
               break;  
           } catch (NoSuchFieldException e) {  
               //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。  
           }  
        }  
        return field;  
    }  

    /** 
     * 利用反射设置指定对象的指定属性为指定的值 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
      * @param fieldValue 目标值 
     */  
    public static void setFieldValue(Object obj, String fieldName,  
           String fieldValue) {  
        Field field = ReflectUtil.getField(obj, fieldName);  
        if (field != null) {  
           try {  
               field.setAccessible(true);  
               field.set(obj, fieldValue);  
           } catch (IllegalArgumentException e) {  
               // TODO Auto-generated catch block  
               e.printStackTrace();  
           } catch (IllegalAccessException e) {  
               // TODO Auto-generated catch block  
               e.printStackTrace();  
           }  
        }  
     }  
    
	public static String toJavaName(String name) {
		if(name!=null&&!"".equals(name)){
			String rs = "";
			name = name.toLowerCase();
			String[] names = name.split("_");
			for (String string : names) {
				rs += string.substring(0,1).toUpperCase()+string.substring(1,string.length());
			}
			return rs;
		}
		return name;
	}
	
	public static String toSqlName(String name) {
			if(name!=null&&!"".equals(name)){
				String rs = "";
				char c[] = name.toCharArray();
				for (int i = 0; i < c.length; i++) {
					if((c[i]+"").matches("[A-Z]")){
						rs+=("_"+c[i]).toLowerCase();
					}else {
						rs+=c[i];
					}
				}
				return rs;
			}
			return name;
	}
	
	public static String fristToLowerCase(String name) {
		if(name!=null&&!"".equals(name)){
			return name.substring(0,1).toLowerCase()+name.substring(1,name.length());
		}
		return name;
	}
	
	public static String fristToUpperCase(String name) {
		if(name!=null&&!"".equals(name)){
			return name.substring(0,1).toUpperCase()+name.substring(1,name.length());
		}
		return name;
	}
	
	
	public static void main(String[] args) {
		System.out.print(toSqlName("tId"));
	}
 }
