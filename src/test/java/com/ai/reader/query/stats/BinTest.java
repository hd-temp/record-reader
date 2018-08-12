package com.ai.reader.query.stats;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class BinTest {

	@Test
	public void testNoInput() {
		Bin<Integer> bin = new Bin<>();
		assertEquals("{}", bin.getResults());
	}

	@Test
	public void testStringBins() {
		String string1 = "string1";
		String string2 = "string2";

		Map<String, Integer> expected = new HashMap<>();
		expected.put(string1, 1);
		expected.put(string2, 2);
		String expectedResult = expected.toString();

		Bin<String> bin = new Bin<>();
		bin.readRecord(string1);
		bin.readRecord(string2);
		bin.readRecord(string2);

		assertEquals(expectedResult, bin.getResults());
	}
}
