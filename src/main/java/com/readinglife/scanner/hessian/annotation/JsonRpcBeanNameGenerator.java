package com.readinglife.scanner.hessian.annotation;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

public final class JsonRpcBeanNameGenerator extends AnnotationBeanNameGenerator{
	 protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef,String annotationInterface) {
         AnnotationMetadata amd = annotatedDef.getMetadata();
         Set<String> types = amd.getAnnotationTypes();
         String beanName = null;
         for(String type:types){
             if(type.equals(annotationInterface)){
                 Map<String, Object> attributes = amd.getAnnotationAttributes(type);
                 String value = (String) attributes.get("value");
                 if (StringUtils.hasLength(value)) {
                     beanName = value;
                 }
             }
         }
         return beanName;
     }
}
