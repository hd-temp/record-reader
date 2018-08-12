package com.ai.reader.query.stats;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MedianTest {

	@Test(expected = IllegalStateException.class)
	public void testNoInput() {
		Median med = new Median();
		med.getResults();
	}

	@Test
	public void testMedianIncreasing() {
		Median med = new Median();
		for (int i = 0; i < 10; i++) {
			med.readRecord(i);
			Double result = med.getResults();
			assertEquals(i / 2.0, result, 1e-9);
		}
	}
}
