package com.system.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.readinglife.framework.web.JsonPager;

@Service("loktpeService")
public interface LoktpeService {
	JsonPager searchLoktpeByPageMap(JsonPager jPager, Map param);

}
