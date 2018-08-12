package com.ai.reader.query;

import com.ai.reader.common.UserRecord;
import com.ai.reader.query.stats.Median;

/**
 * Tracks median age of users
 */
public class MedianAge extends Query<Integer, Double> {

	public MedianAge() {
		super(new Median());
	}

	@Override
	public String getResults() {
		return "Median age: " + super.getResults();
	}

	@Override
	protected boolean include(UserRecord record) {
		return true;
	}

	@Override
	protected void record(UserRecord userRecord) {
		statsCollector.readRecord(userRecord.getAge());
	}

}
