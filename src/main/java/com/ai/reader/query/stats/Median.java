package com.ai.reader.query.stats;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Tracks median value
 */
public class Median implements IStatsCollector<Integer, Double> {

	private Queue<Integer> lowerHalf;
	private Queue<Integer> higherHalf;

	public Median() {
		lowerHalf = new PriorityQueue<>(Comparator.reverseOrder());
		higherHalf = new PriorityQueue<>();
	}

	@Override
	public void readRecord(Integer record) {
		if (lowerHalf.size() > higherHalf.size())
			higherHalf.add(record);
		else
			lowerHalf.add(record);

		balanceHalves();
	}

	@Override
	public Double getResults() {
		if (lowerHalf.isEmpty() && higherHalf.isEmpty())
			throw new IllegalStateException("No elements recorded");

		if (lowerHalf.size() == higherHalf.size()) {
			Integer highBound = higherHalf.peek();
			Integer lowBound = lowerHalf.peek();
			return (highBound - lowBound) / 2.0 + lowBound;
		}

		return lowerHalf.peek().doubleValue();
	}

	private void balanceHalves() {
		if (!lowerHalf.isEmpty() && !higherHalf.isEmpty() // at most one rebalance needed
				&& lowerHalf.peek() > higherHalf.peek()) {
			Integer larger = lowerHalf.poll();
			Integer smaller = higherHalf.poll();
			lowerHalf.add(smaller);
			higherHalf.add(larger);
		}
	}
}
