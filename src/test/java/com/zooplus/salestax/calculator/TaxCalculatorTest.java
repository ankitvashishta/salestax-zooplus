package com.zooplus.salestax.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.zooplus.salestax.input.handler.InputParser;
import com.zooplus.salestax.input.handler.InputReader;
import com.zooplus.salestax.model.BillAmount;
import com.zooplus.salestax.model.Item;
import com.zooplus.salestax.model.PurchasedItem;
import com.zooplus.salestax.utils.Constants;
import com.zooplus.salestax.utils.PropertiesReader;

public class TaxCalculatorTest {

	private static final String PROPERTIES_FILE_PATH = "src/test/resources/application.properties";

	@Test
	public void testCalculateTaxOnNonImportedItems() throws IOException {
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		List<String> userInput = null;
		try (Reader fileReader = new FileReader("src/test/resources/UserInput1.txt")) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		InputParser inputParser = new InputParser(properties);
		Map<String, Object> inputMap = inputParser.parseUserInput(userInput);
		List<Item> validInput = (List<Item>) inputMap.get(Constants.VALID_INPUTS);
		DecimalFormat df = new DecimalFormat(properties.getProperty(Constants.DECIMAL_FORMAT));
		BillAmount billAmount = new BillAmount(0, 0, properties.getProperty(Constants.DECIMAL_FORMAT));
		TaxCalculator taxCalculator = new TaxCalculator(properties);
		List<PurchasedItem> purchasedItems = taxCalculator.calculateTax(validInput, billAmount);
		assertEquals(3, purchasedItems.size());
		assertEquals("1.50", df.format(billAmount.getSalesTaxes()));
		assertEquals("29.83", df.format(billAmount.getTotal()));
	}

	@Test
	public void testCalculateTaxOnImportedItems() throws IOException {
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		List<String> userInput = null;
		try (Reader fileReader = new FileReader("src/test/resources/UserInput2.txt")) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		InputParser inputParser = new InputParser(properties);
		Map<String, Object> inputMap = inputParser.parseUserInput(userInput);
		List<Item> validInput = (List<Item>) inputMap.get(Constants.VALID_INPUTS);
		DecimalFormat df = new DecimalFormat(properties.getProperty(Constants.DECIMAL_FORMAT));
		BillAmount billAmount = new BillAmount(0, 0, properties.getProperty(Constants.DECIMAL_FORMAT));
		TaxCalculator taxCalculator = new TaxCalculator(properties);
		List<PurchasedItem> purchasedItems = taxCalculator.calculateTax(validInput, billAmount);
		assertEquals(2, purchasedItems.size());
		assertEquals("7.65", df.format(billAmount.getSalesTaxes()));
		assertEquals("65.15", df.format(billAmount.getTotal()));
	}

	@Test
	public void testCalculateTaxOnMixedItems() throws IOException {
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		List<String> userInput = null;
		try (Reader fileReader = new FileReader("src/test/resources/UserInput5.txt")) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		InputParser inputParser = new InputParser(properties);
		Map<String, Object> inputMap = inputParser.parseUserInput(userInput);
		List<Item> validInput = (List<Item>) inputMap.get(Constants.VALID_INPUTS);
		DecimalFormat df = new DecimalFormat(properties.getProperty(Constants.DECIMAL_FORMAT));
		BillAmount billAmount = new BillAmount(0, 0, properties.getProperty(Constants.DECIMAL_FORMAT));
		TaxCalculator taxCalculator = new TaxCalculator(properties);
		List<PurchasedItem> purchasedItems = taxCalculator.calculateTax(validInput, billAmount);
		assertEquals(4, purchasedItems.size());
		assertEquals("6.65", df.format(billAmount.getSalesTaxes()));
		assertEquals("74.63", df.format(billAmount.getTotal()));
	}

}
