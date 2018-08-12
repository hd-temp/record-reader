package com.ai.reader.query;

import com.ai.reader.common.UserRecord;
import com.ai.reader.query.stats.Median;

/**
 * Tracks median number of friends
 */
public class MedianFriends extends Query<Integer, Double> {

	public MedianFriends() {
		super(new Median());
	}

	@Override
	public String getResults() {
		return "Median friends: " + super.getResults();
	}

	@Override
	protected boolean include(UserRecord record) {
		return true;
	}

	@Override
	protected void record(UserRecord userRecord) {
		statsCollector.readRecord(userRecord.getFriends().length);
	}
}
