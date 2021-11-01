package com.zooplus.salestax.input.handler;

import static com.zooplus.salestax.utils.Constants.INVALID_INPUTS;
import static com.zooplus.salestax.utils.Constants.VALID_INPUTS;
import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.zooplus.salestax.model.Item;
import com.zooplus.salestax.utils.PropertiesReader;

public class InputParserTest {

	private static final String PROPERTIES_FILE_PATH = "application.properties";

	@Test
	public void whenAllInputsValid_InputSizeShouldMatchValidItemsSize() throws IOException {
		Map<String, Object> inputMap = getParsedUserInput("src/test/resources/UserInput1.txt");
		List<Item> validInput = (List<Item>) inputMap.get(VALID_INPUTS);
		List<String> invalidInput = (List<String>) inputMap.get(INVALID_INPUTS);

		assertEquals(3, validInput.size());
		assertEquals(0, invalidInput.size());
	}

	@Test
	public void whenNotAllInputsValid_ShouldGetTheSizeOfInvalidInputs() throws IOException {
		Map<String, Object> inputMap = getParsedUserInput("src/test/resources/UserInput3.txt");
		List<Item> validInput = (List<Item>) inputMap.get(VALID_INPUTS);
		List<String> invalidInput = (List<String>) inputMap.get(INVALID_INPUTS);

		assertEquals(4, validInput.size());
		assertEquals(3, invalidInput.size());
	}

	@Test
	public void whenExemptedWordIsMixedWithNonExemptedItem_ShouldCountAsNonExempted() throws IOException {
		Map<String, Object> inputMap = getParsedUserInput("src/test/resources/UserInput4.txt");
		List<Item> validInput = (List<Item>) inputMap.get(VALID_INPUTS);
		List<String> invalidInput = (List<String>) inputMap.get(INVALID_INPUTS);
		long exemptedInputs = validInput.stream().filter(i -> i.isExempted()).count();

		assertEquals(1, validInput.size());
		assertEquals(0, invalidInput.size());
		assertEquals(0, exemptedInputs);
	}

	private Map<String, Object> getParsedUserInput(String path) throws IOException {
		Properties properties = new PropertiesReader().readProperties(PROPERTIES_FILE_PATH);
		List<String> userInput = null;
		try (Reader fileReader = new FileReader(path)) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		InputParser inputParser = new InputParser(properties);
		return inputParser.parseUserInput(userInput);
	}

}
