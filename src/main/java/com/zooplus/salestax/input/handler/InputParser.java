package com.zooplus.salestax.input.handler;

import static com.zooplus.salestax.utils.Constants.EXEMPTED_ITEMS;
import static com.zooplus.salestax.utils.Constants.INVALID_INPUTS;
import static com.zooplus.salestax.utils.Constants.VALID_INPUTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zooplus.salestax.model.Item;

/**
 * Casts the user input strings to item objects.
 * 
 * @author ankit
 *
 */
public class InputParser {

	Properties properties;

	public InputParser(Properties properties) {
		this.properties = properties;
	}

	private static final String INPUT_FORMAT = "^([\\d]+)([A-Za-z\\s]+)(\\sat\\s)([0-9\\.]+)$";

	public Map<String, Object> parseUserInput(List<String> userInput) {
		Map<String, Object> inputMap = new HashMap<>();
		List<Item> itemList = new ArrayList<>();
		List<String> invalidInput = new ArrayList<>();
		Pattern pattern = Pattern.compile(INPUT_FORMAT);
		userInput.stream().forEach(u -> {
			Matcher matcher = pattern.matcher(u.trim());
			if (matcher.find())
				addItemToList(matcher, itemList);
			else
				invalidInput.add(u);
		});
		inputMap.put(VALID_INPUTS, itemList);
		inputMap.put(INVALID_INPUTS, invalidInput);
		return inputMap;
	}

	private void addItemToList(Matcher matcher, List<Item> itemList) {
		int quantity = Integer.parseInt(matcher.group(1));
		String name = matcher.group(2);
		boolean isImported = name.contains("imported");
		boolean isExempted = Arrays.stream(properties.getProperty(EXEMPTED_ITEMS).split(","))
				.anyMatch(e -> name.contains(e.toLowerCase()));
		double cost = Double.parseDouble(matcher.group(4));
		itemList.add(new Item(quantity, name, cost, isImported, isExempted));
	}

}
