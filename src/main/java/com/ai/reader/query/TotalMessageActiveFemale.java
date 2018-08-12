package com.ai.reader.query;

import com.ai.reader.common.UserRecord;
import com.ai.reader.query.filter.EqualsFilter;
import com.ai.reader.query.stats.Total;

/**
 * Tracks total number of unread messages for active females
 */
public class TotalMessageActiveFemale extends Query<Integer, Integer> {

	private static final String MESSAGES_REGEX = "[^\\d]"; // remove all non numbers

	private static EqualsFilter<Boolean> activeFilter = new EqualsFilter<>(true);
	private static EqualsFilter<String> femaleFilter = new EqualsFilter<>("female");

	public TotalMessageActiveFemale() {
		super(new Total());
	}

	@Override
	public String getResults() {
		return "Total messages for active females: " + super.getResults();
	}

	@Override
	protected boolean include(UserRecord record) {
		if (!activeFilter.passesFilter(record.isActive()))
			return false;

		if (!femaleFilter.passesFilter(record.getGender()))
			return false;

		return true;
	}

	@Override
	protected void record(UserRecord userRecord) {
		String greeting = userRecord.getGreeting();
		Integer unreadMessages = Integer.parseInt(greeting.replaceAll(MESSAGES_REGEX, ""));
		statsCollector.readRecord(unreadMessages);
	}

}
