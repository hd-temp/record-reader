package com.ai.reader.query;

import com.ai.reader.common.UserRecord;
import com.ai.reader.query.stats.Mean;

/**
 * Tracks mean balance
 */
public class MeanBalance extends Query<Double, Double> {

	private static final String BALANCE_REGEX = "[^\\d.]"; // remove all non numbers and decimals

	public MeanBalance() {
		super(new Mean());
	}

	@Override
	public String getResults() {
		return "Mean balance: " + super.getResults();
	}

	@Override
	protected boolean include(UserRecord record) {
		return true;
	}

	@Override
	protected void record(UserRecord userRecord) {
		String balance = userRecord.getBalance();
		Double balanceValue = Double.parseDouble(balance.replaceAll(BALANCE_REGEX, ""));
		statsCollector.readRecord(balanceValue);
	}
}
