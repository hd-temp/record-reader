package com.ai.reader.query.stats;

/**
 * Tracks total value
 */
public class Total implements IStatsCollector<Integer, Integer> {

	private int sum;

	public Total() {
		sum = 0;
	}

	@Override
	public void readRecord(Integer record) {
		sum += record;
	}

	@Override
	public Integer getResults() {
		return sum;
	}
}
