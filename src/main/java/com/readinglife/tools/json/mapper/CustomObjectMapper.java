package com.readinglife.tools.json.mapper;



import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;



public class CustomObjectMapper extends ObjectMapper{
	private static final Logger logger = Logger.getLogger(CustomObjectMapper.class);
	public CustomObjectMapper() {
		logger.debug(">>> JsonObjectMapper()");
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);  
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        setDateFormat(df);
         logger.debug("<<< JsonObjectMapper()");
	}
}
