package com.ai.reader.query.stats;

/**
 * Tracks a property of records given to this object.
 *
 * @param <V> Type of input
 * @Param <R> Type of result
 */
public interface IStatsCollector<I, R> {

	public void readRecord(I record);

	public R getResults();
}
