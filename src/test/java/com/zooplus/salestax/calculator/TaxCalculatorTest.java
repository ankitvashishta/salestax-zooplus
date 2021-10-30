package com.zooplus.salestax.calculator;

import static com.zooplus.salestax.utils.Constants.DECIMAL_FORMAT;
import static com.zooplus.salestax.utils.Constants.VALID_INPUTS;
import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.zooplus.salestax.input.handler.InputParser;
import com.zooplus.salestax.input.handler.InputReader;
import com.zooplus.salestax.model.BillAmount;
import com.zooplus.salestax.model.Item;
import com.zooplus.salestax.model.PurchasedItem;
import com.zooplus.salestax.utils.PropertiesReader;

public class TaxCalculatorTest {

	private static final String PROPERTIES_FILE_PATH = "src/test/resources/application.properties";

	private Properties properties;
	DecimalFormat df;
	TaxCalculator taxCalculator;

	@Before
	public void setup() {
		properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		df = new DecimalFormat(properties.getProperty(DECIMAL_FORMAT));
		taxCalculator = new TaxCalculator(properties);
	}

	@Test
	public void testCalculateTaxOnNonImportedItems() throws IOException {
		List<Item> validInput = getValidUserInput("src/test/resources/UserInput1.txt");
		BillAmount billAmount = new BillAmount(0, 0, properties.getProperty(DECIMAL_FORMAT));
		List<PurchasedItem> purchasedItems = taxCalculator.calculateTax(validInput, billAmount);
		
		assertEquals(3, purchasedItems.size());
		assertEquals("1.50", df.format(billAmount.getSalesTaxes()));
		assertEquals("29.83", df.format(billAmount.getTotal()));
	}

	@Test
	public void testCalculateTaxOnImportedItems() throws IOException {
		List<Item> validInput = getValidUserInput("src/test/resources/UserInput2.txt");
		BillAmount billAmount = new BillAmount(0, 0, properties.getProperty(DECIMAL_FORMAT));
		List<PurchasedItem> purchasedItems = taxCalculator.calculateTax(validInput, billAmount);
		
		assertEquals(2, purchasedItems.size());
		assertEquals("7.65", df.format(billAmount.getSalesTaxes()));
		assertEquals("65.15", df.format(billAmount.getTotal()));
	}

	@Test
	public void testCalculateTaxOnMixedItems() throws IOException {
		List<Item> validInput = getValidUserInput("src/test/resources/UserInput5.txt");
		BillAmount billAmount = new BillAmount(0, 0, properties.getProperty(DECIMAL_FORMAT));
		List<PurchasedItem> purchasedItems = taxCalculator.calculateTax(validInput, billAmount);
		
		assertEquals(4, purchasedItems.size());
		assertEquals("6.65", df.format(billAmount.getSalesTaxes()));
		assertEquals("74.63", df.format(billAmount.getTotal()));
	}

	private List<Item> getValidUserInput(String path) throws IOException {
		List<String> userInput = null;
		try (Reader fileReader = new FileReader(path)) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		InputParser inputParser = new InputParser(properties);
		Map<String, Object> inputMap = inputParser.parseUserInput(userInput);
		return (List<Item>) inputMap.get(VALID_INPUTS);
	}

}
