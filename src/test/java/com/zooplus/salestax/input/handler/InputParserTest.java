package com.zooplus.salestax.input.handler;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.zooplus.salestax.model.Item;
import com.zooplus.salestax.utils.Constants;
import com.zooplus.salestax.utils.PropertiesReader;

public class InputParserTest {

	private static final String PROPERTIES_FILE_PATH = "src/test/resources/application.properties";

	@Test
	public void whenAllInputsValid_InputSizeShouldMatchValidItemsSize() throws IOException {
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		List<String> userInput = null;
		try (Reader fileReader = new FileReader("src/test/resources/UserInput1.txt")) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		InputParser inputParser = new InputParser(properties);
		Map<String, Object> inputMap = inputParser.parseUserInput(userInput);
		List<Item> validInput = (List<Item>) inputMap.get(Constants.VALID_INPUTS);
		List<String> invalidInput = (List<String>) inputMap.get(Constants.INVALID_INPUTS);
		assertEquals(userInput.size(), validInput.size());
		assertEquals(0, invalidInput.size());
	}
	
	@Test
	public void whenNotAllInputsValid_ShouldGetTheSizeOfInvalidInputs() throws IOException {
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		List<String> userInput = null;
		try (Reader fileReader = new FileReader("src/test/resources/UserInput3.txt")) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		InputParser inputParser = new InputParser(properties);
		Map<String, Object> inputMap = inputParser.parseUserInput(userInput);
		List<Item> validInput = (List<Item>) inputMap.get(Constants.VALID_INPUTS);
		List<String> invalidInput = (List<String>) inputMap.get(Constants.INVALID_INPUTS);
		assertEquals(4, validInput.size());
		assertEquals(3, invalidInput.size());
	}
	
	@Test
	public void whenExemptedWordIsMixedWithNonExemptedItem_ShouldCountAsNonExempted() throws IOException {
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		List<String> userInput = null;
		try (Reader fileReader = new FileReader("src/test/resources/UserInput4.txt")) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		InputParser inputParser = new InputParser(properties);
		Map<String, Object> inputMap = inputParser.parseUserInput(userInput);
		List<Item> validInput = (List<Item>) inputMap.get(Constants.VALID_INPUTS);
		List<String> invalidInput = (List<String>) inputMap.get(Constants.INVALID_INPUTS);
		assertEquals(1, validInput.size());
		assertEquals(0, invalidInput.size());
		long exemptedInputs = validInput.stream().filter(i -> i.isExempted()).count();
		assertEquals(0, exemptedInputs);
	}

}
