package com.ai.reader.query.stats;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class MeanTest {

	private static final Random random = new Random(1L);

	@Test
	public void noInput() {
		Mean mean = new Mean();
		assertEquals(0.0, mean.getResults(), 1e-9);
	}

	@Test
	public void gaussianDistribution() {
		Mean mean = new Mean();
		for (int i = 0; i < 10000; i++)
			mean.readRecord(random.nextGaussian() + 5.0); // normal distribution centered around 5

		assertEquals(5.0, mean.getResults(), 1e-2);
	}
}
