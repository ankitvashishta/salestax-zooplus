package com.zooplus.salestax.utils;

import static com.zooplus.salestax.utils.Constants.BASIC_SALES_TAX;
import static com.zooplus.salestax.utils.Constants.DECIMAL_FORMAT;
import static com.zooplus.salestax.utils.Constants.EXEMPTED_ITEMS;
import static com.zooplus.salestax.utils.Constants.IMPORTED_SALES_TAX;
import static com.zooplus.salestax.utils.Constants.ROUND_OFF_FRACTION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

public class PropertiesReaderTest {

	private static final String PROPERTIES_FILE_PATH = "application.properties";

	@Test
	public void testReadProperties() {
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		double basicTax = Double.parseDouble(properties.getProperty(BASIC_SALES_TAX));
		double importedTax = Double.parseDouble(properties.getProperty(IMPORTED_SALES_TAX));
		String[] exemptedItems = properties.getProperty(EXEMPTED_ITEMS).split(",");
		int roundOfFraction = Integer.parseInt(properties.getProperty(ROUND_OFF_FRACTION));
		String decimalFormat = properties.getProperty(DECIMAL_FORMAT);

		assertTrue(10.0d == basicTax);
		assertTrue(5.0d == importedTax);
		assertEquals(3, exemptedItems.length);
		assertEquals(20, roundOfFraction);
		assertEquals("0.00", decimalFormat);
	}

}
