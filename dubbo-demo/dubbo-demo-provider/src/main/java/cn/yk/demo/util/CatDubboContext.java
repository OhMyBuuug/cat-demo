package cn.yk.demo.util;

import java.util.HashMap;
import java.util.Map;

import com.dianping.cat.Cat.Context;

public class CatDubboContext implements Context{
	
	private Map<String, String> properties = new HashMap<String,String>();
 	
	@Override
	public void addProperty(String key, String value) {
		properties.put(key, value);
	}

	@Override
	public String getProperty(String key) {
		return properties.get(key);
	}
	

}
