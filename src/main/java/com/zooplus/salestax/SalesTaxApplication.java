package com.zooplus.salestax;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.zooplus.salestax.calculator.TaxCalculator;
import com.zooplus.salestax.input.handler.InputParser;
import com.zooplus.salestax.input.handler.InputReader;
import com.zooplus.salestax.model.BillAmount;
import com.zooplus.salestax.model.Item;
import com.zooplus.salestax.model.PurchasedItem;
import com.zooplus.salestax.utils.Constants;
import com.zooplus.salestax.utils.PropertiesReader;

public class SalesTaxApplication {

	private static final String PROPERTIES_FILE_PATH = "src/main/resources/application.properties";

	public static void main(String args[]) {

		// Step 1. Read properties file.
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);

		// Step 2. Read user's input
		List<String> userInput = null;
		try (InputStreamReader in = new InputStreamReader(System.in)) {
			InputReader inputReader = new InputReader(in);
			userInput = inputReader.readInput();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Step 3. Parse user input.
		InputParser inputParser = new InputParser(properties);
		Map<String, Object> inputMap = inputParser.parseUserInput(userInput);
		handleInvalidInputs((List<String>) inputMap.get(Constants.INVALID_INPUTS));

		// Step 4. Calculate the taxes.
		BillAmount billAmount = new BillAmount(0, 0, properties.getProperty(Constants.DECIMAL_FORMAT));
		TaxCalculator taxCalculator = new TaxCalculator(properties);
		List<PurchasedItem> purchasedItems = taxCalculator
				.calculateTax(((List<Item>) inputMap.get(Constants.VALID_INPUTS)), billAmount);
		purchasedItems.stream().forEach(System.out::println);
		System.out.println(billAmount);

	}

	private static void handleInvalidInputs(List<String> invalidInput) {
		if(invalidInput.size()==0)
			return;
		System.out.println("Following inputs were not valid : ");
		invalidInput.stream().forEach(System.out::println);
	}

}
