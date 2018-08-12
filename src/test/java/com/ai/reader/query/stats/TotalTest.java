package com.ai.reader.query.stats;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class TotalTest {

	public static final Random random = new Random(1L);

	@Test
	public void testNoInput() {
		Total total = new Total();
		assertEquals("0" ,total.getResults());
	}

	@Test
	public void testRandomIntegers() {
		int expected = 0;
		Total total = new Total();

		for (int i = 0; i < 10000; i++) {
			int next = random.nextInt(5);
			expected += next;
			total.readRecord(next);
		}

		assertEquals(String.valueOf(expected), total.getResults());
	}
}
