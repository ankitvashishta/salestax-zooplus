package com.zooplus.salestax.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads the properties from application.properties file.
 * 
 * @author ankit
 *
 */
public class PropertiesReader {

	public Properties readProperties(String path) {
		Properties properties = null;
		this.getClass().getClassLoader();
		try (InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream(path)) {
			properties = new Properties();
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return properties;
	}

}
