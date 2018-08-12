package com.ai.reader.query.stats;

/**
 * Tracks mean value
 */
public class Mean implements IStatsCollector<Double, Double> {

	private double sum;
	private double numberOfRecords;

	public Mean() {
		sum = 0.0;
		numberOfRecords = 0;
	}

	@Override
	public void readRecord(Double record) {
		sum += record;
		numberOfRecords++;
	}

	@Override
	public Double getResults() {
		if (numberOfRecords == 0)
			return 0.0;

		return sum / numberOfRecords;
	}

}
