package com.zooplus.salestax.input.handler;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads the user input strings.
 * 
 * @author ankit
 *
 */
public class InputReader {

	private Reader reader;

	public InputReader(Reader reader) {
		this.reader = reader;
	}

	public List<String> readInput() {
		List<String> userInput = new ArrayList<>();
		try (BufferedReader buffer = new BufferedReader(reader)) {
			String line;
			while ((line = buffer.readLine()) != null && !(line.equals("")))
				userInput.add(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInput;
	}

}
