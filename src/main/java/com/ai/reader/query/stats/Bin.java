package com.ai.reader.query.stats;

import java.util.Map;
import java.util.TreeMap;

/**
 * Collects records in a histogram fashion
 *
 * @param <V> Type of bin
 */
public class Bin<V> implements IStatsCollector<V, String> {

	private final Map<V, Integer> bins;

	public Bin() {
		bins = new TreeMap<>(); // keep in sorted order for pretty print convenience (otherwise hashmap is fine)
	}

	@Override
	public void readRecord(V record) {
		Integer entries = bins.get(record);
		if (entries == null)
			bins.put(record, 1);
		else
			bins.put(record, entries + 1);
	}

	@Override
	public String getResults() {
		return bins.toString();
	}

}
