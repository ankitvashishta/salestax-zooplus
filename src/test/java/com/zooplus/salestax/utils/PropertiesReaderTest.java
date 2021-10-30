package com.zooplus.salestax.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

public class PropertiesReaderTest {

	private static final String PROPERTIES_FILE_PATH = "src/test/resources/application.properties";

	@Test
	public void testReadProperties() {
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		double basicTax = Double.parseDouble(properties.getProperty(Constants.BASIC_SALES_TAX));
		double importedTax = Double.parseDouble(properties.getProperty(Constants.IMPORTED_SALES_TAX));
		String[] exemptedItems = properties.getProperty(Constants.EXEMPTED_ITEMS).split(",");

		assertTrue(10.0d == basicTax);
		assertTrue(5.0d == importedTax);
		assertEquals(3, exemptedItems.length);
	}

}
