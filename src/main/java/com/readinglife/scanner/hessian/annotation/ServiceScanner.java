package com.readinglife.scanner.hessian.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Role;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.util.Assert;



public final class ServiceScanner  extends ClassPathBeanDefinitionScanner{
    private BeanNameGenerator beanNameGenerator = new  AnnotationBeanNameGenerator();

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
    private BeanDefinitionRegistry registry;

    public ServiceScanner(BeanDefinitionRegistry registry) {
        super(registry);
        this.registry = registry;
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
    	Assert.notEmpty(basePackages, "At least one base package must be specified");
		Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<BeanDefinitionHolder>();
		for (String basePackage : basePackages) {
			Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
			for (BeanDefinition candidate : candidates) {
				ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(candidate);
				candidate.setScope(scopeMetadata.getScopeName());
				String beanName = this.beanNameGenerator.generateBeanName(candidate, this.registry);
				if (candidate instanceof AbstractBeanDefinition) {
					postProcessBeanDefinition((AbstractBeanDefinition) candidate, beanName);
				}
				if (candidate instanceof AnnotatedBeanDefinition) {
					processCommonDefinitionAnnotations((AnnotatedBeanDefinition) candidate);
				}
				
					ScannedGenericBeanDefinition bd = (ScannedGenericBeanDefinition) candidate;//通用bean扫描
	                bd.setBeanClassName(HessianServiceExporter.class.getName());//设置bean名称
	                bd.setBeanClass(HessianServiceExporter.class);//设置bean类
	                bd.getPropertyValues().add("service", registry.getBeanDefinition(beanName));//设置属性 service
	                String[] interfaces = bd.getMetadata().getInterfaceNames();//获得元数据、接口名称
	     	       
	                if (interfaces == null || interfaces.length == 0)
	                    continue;
	                Class interf = null;
	                try {
	                    interf = Class.forName(interfaces[0]);//获得接口
	                } catch (ClassNotFoundException e) {
	                    continue;
	                }
	                bd.getPropertyValues().add("serviceInterface", interf);//设置属性 serviceInterface
	                candidate = bd;
	                BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(candidate,  "/" +   beanName );//新bean定义持有
	                definitionHolder = applyScopedProxyMode(scopeMetadata, definitionHolder, this.registry);//应用范围代理模式
	                beanDefinitions.add(definitionHolder);//将bean定义持有放入bean定义持有set中
	                registerBeanDefinition(definitionHolder, this.registry);//注册bean定义
			}						
		}
        
			    if (beanDefinitions.isEmpty()) {
		            System.out.println("not service be scaned");
		        } else {
		            for (BeanDefinitionHolder holder : beanDefinitions) {
		                AnnotatedBeanDefinition definition = (AnnotatedBeanDefinition) holder
		                        .getBeanDefinition();//注释的bean定义
		                System.out.println("service:"+holder.getBeanName());//注释的bean定义名称
		                System.out.println("service:"+definition.getMetadata().getAnnotationTypes());//注释的bean定义类型
		            }
		        }
			    
			    
        return beanDefinitions;

    }

    @Override
    protected void registerDefaultFilters() {//设置默认过滤器
        addIncludeFilter(new AnnotationTypeFilter(HessianService.class));
    }

    BeanDefinitionHolder applyScopedProxyMode(//应用范围代理模式
            ScopeMetadata metadata, BeanDefinitionHolder definition,
            BeanDefinitionRegistry registry) {

        ScopedProxyMode scopedProxyMode = metadata.getScopedProxyMode();//范围代理模式
        if (scopedProxyMode.equals(ScopedProxyMode.NO)) {
            return definition;
        }
        boolean proxyTargetClass = scopedProxyMode.equals(ScopedProxyMode.TARGET_CLASS);
        return ScopedProxyUtils.createScopedProxy(definition, registry, proxyTargetClass);//创建限定了作用域的代理


    }
    
    
    void processCommonDefinitionAnnotations(AnnotatedBeanDefinition abd) {
		AnnotationMetadata metadata = abd.getMetadata();
		if (metadata.isAnnotated(Primary.class.getName())) {
			abd.setPrimary(true);
		}
		if (metadata.isAnnotated(Lazy.class.getName())) {
			abd.setLazyInit(attributesFor(metadata, Lazy.class).getBoolean("value"));
		}
		if (metadata.isAnnotated(DependsOn.class.getName())) {
			abd.setDependsOn(attributesFor(metadata, DependsOn.class).getStringArray("value"));
		}
		if (abd instanceof AbstractBeanDefinition) {
			if (metadata.isAnnotated(Role.class.getName())) {
				Integer role = attributesFor(metadata, Role.class).getNumber("value");
				((AbstractBeanDefinition)abd).setRole(role);
			}
		}
	}
    
    
	public static AnnotationAttributes attributesFor(AnnotationMetadata metadata, Class<?> annoClass) {
		return attributesFor(metadata, annoClass.getName());
	}

	public static AnnotationAttributes attributesFor(AnnotationMetadata metadata, String annoClassName) {
		return AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(annoClassName, false));
	}
}
