package com.taozhu.common.util;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
@SuppressWarnings("unchecked")
public class SysPropertyUtil extends PropertyPlaceholderConfigurer {
	
	private static SysPropertyUtil instance = new SysPropertyUtil();
	private static Properties prop;

	public static SysPropertyUtil getInstance(){
		return instance;
	}
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		try {
			prop = mergeProperties();
			// Convert the merged properties, if necessary.
			convertProperties(prop);
			
			// Let the subclass process the properties.
			processProperties(beanFactory, prop);

		}
		catch (IOException ex) {
			ex.printStackTrace();
			throw new BeanInitializationException("Could not load properties", ex);
		}
	}
	public void setValue(String key,String value){
		instance.prop.put(key, value);
	}
	public String getValue(String key){
		String value = null;
		if(value == null) {
			value = instance.prop.getProperty(key);
		}
		return value;
	}

	public String getFilePath(String key){
		try {
			return this.files.get(key).getFile().getPath();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	private Map<String, Resource> files;
	
	public void setFiles(Map<String,Resource> files){
		this.files = files;
	}
	
	public File getFile(String key){
		try {
			return this.files.get(key).getFile();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Properties getProperties() {
		Properties p = new Properties();
		for(Object k : prop.keySet()) {
			String pk = k.toString();
			p.setProperty(pk, prop.getProperty(pk));
		}
		return p;
	}
	public Set getKeySet(){
		return this.getProperties().keySet();
	}
	public Resource getResource(String key){
		return this.files.get(key);
	}
	
}
