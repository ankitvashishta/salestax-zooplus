package com.zooplus.salestax.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public Properties readProperties(String path) {
		Properties properties = null;
		try (InputStream input = new FileInputStream(path)) {
			properties = new Properties();
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return properties;
	}

}
