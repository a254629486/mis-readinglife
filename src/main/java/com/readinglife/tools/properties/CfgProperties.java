package com.readinglife.tools.properties;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;



public class CfgProperties {
	public static String get(String key){
		String value="";
		ClassPathResource cp = new ClassPathResource("cfg.properties");
	       Properties properties = new OrderedProperties();
	       try{
	           properties.load(cp.getInputStream());
	       } catch(IOException e) {
	           throw new RuntimeException("load cfg.properties error!");
	       }
	       for(Iterator its = properties.keySet().iterator();its.hasNext();) {
	           String zkey = (String)its.next();
	           if(zkey.equals(key)){
	        	    value=properties.getProperty(zkey).trim();
	        	    break;
	           }
	       }
		return value;
	}
}
