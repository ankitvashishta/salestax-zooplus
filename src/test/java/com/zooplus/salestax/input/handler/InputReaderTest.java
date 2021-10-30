package com.zooplus.salestax.input.handler;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.junit.Test;

public class InputReaderTest {

	@Test
	public void whenProvidedEmptyUserInput_ShouldReturnEmptyRecords() throws IOException {
		List<String> userInput = null;
		try (Reader fileReader = new FileReader("src/test/resources/EmptyInput.txt")) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		assertEquals(0, userInput.size());
	}

	@Test
	public void whenProvidedNonEmptyUserInput_ShouldRead() throws IOException {
		List<String> userInput = null;
		try (Reader fileReader = new FileReader("src/test/resources/UserInput1.txt")) {
			InputReader inputReader = new InputReader(fileReader);
			userInput = inputReader.readInput();
		}
		assertEquals(3, userInput.size());
	}

}
