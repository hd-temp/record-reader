package com.ai.reader.query;

import com.ai.reader.common.UserRecord;
import com.ai.reader.query.stats.Bin;

/**
 * Tracks number of users registered per year
 */
public class BinRegistrationYear extends Query<Integer, String> {

	public BinRegistrationYear() {
		super(new Bin<Integer>());
	}

	@Override
	public String getResults() {
		return "Registration years: " + super.getResults();
	}

	@Override
	protected boolean include(UserRecord record) {
		return true;
	}

	@Override
	protected void record(UserRecord userRecord) {
		String dateRegistered = userRecord.getRegistered();

		// Assumes valid input, so we can just grab the year. Otherwise we can use java Date, for example.
		int year = Integer.parseInt(dateRegistered.substring(0, 4));

		statsCollector.readRecord(year);
	}
}
