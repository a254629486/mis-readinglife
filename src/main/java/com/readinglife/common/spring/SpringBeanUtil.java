package com.readinglife.common.spring;
import org.springframework.beans.BeansException;  
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
    
public class SpringBeanUtil implements ApplicationContextAware {  
    private static ApplicationContext context;// 
    
    private static SpringBeanUtil servlocator = new SpringBeanUtil();
    
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
	
    /** 
    * 创建读取Bean服务类实例(从spring.xml中加载) 
    */  
    public static SpringBeanUtil getInstance() {  
        return servlocator;  
    }  
   
    /** 
    * 根据提供的bean名称得到相应的服务类      
    * @param servName bean名称      
    */  
    public static Object getBean(String beanId) {  
     return context.getBean(beanId);  
       // return beanFactory.getBean(servName);  
    }  
   
    /** 
    * 根据提供的bean名称得到对应于指定类型的服务类 
    * @param servName bean名称 
    * @param clazz 返回的bean类型,若类型不匹配,将抛出异常 
    */  
    public static Object getBean(String beanId, Class clazz) {  
        return context.getBean(beanId, clazz);  
    }  
     
      
     /** 
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true  
     * @param name 
     * @return boolean 
     */  
     public static boolean containsBean(String beanId) {  
       return context.containsBean(beanId);  
     }  
      
     /** 
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）    
     * @param name 
     * @return boolean 
     * @throws NoSuchBeanDefinitionException 
     */  
     public static boolean isSingleton(String beanId)  {  
       return context.isSingleton(beanId);  
     }  
      
     /** 
     * @param name 
     * @return Class 注册对象的类型 
     * @throws NoSuchBeanDefinitionException 
     */  
     public static Class getType(String beanId) {  
       return context.getType(beanId);  
     }  
      
     /** 
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名    
     * @param name 
     * @return 
     * @throws NoSuchBeanDefinitionException 
     */  
     public static String[] getAliases(String beanId) {  
       return context.getAliases(beanId);  
     }

  
  
}  