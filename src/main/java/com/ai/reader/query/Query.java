package com.ai.reader.query;

import java.util.Collection;

import com.ai.reader.common.UserRecord;
import com.ai.reader.query.stats.IStatsCollector;

/**
 * Queries record stream
 *
 * @param <T> Query parameter type
 * @param <R> Result output type
 */
public abstract class Query<T, R> {

	protected final IStatsCollector<T, R> statsCollector;

	protected Query(IStatsCollector<T, R> statsCollector) {
		this.statsCollector = statsCollector;
	}

	public void processRecords(Collection<UserRecord> records) {
		records.stream().filter(r -> {
			return include(r);
		}).forEach(r -> record(r));
	}

	public String getResults() {
		return statsCollector.getResults().toString();
	}

	/**
	 *
	 * @param record
	 * @return True if record should be included in the query
	 */
	protected abstract boolean include(UserRecord record);

	/**
	 * Run given user record through stats collector to have information recorded
	 *
	 * @param userRecord
	 */
	protected abstract void record(UserRecord userRecord);
}
