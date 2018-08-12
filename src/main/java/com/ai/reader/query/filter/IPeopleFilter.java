package com.ai.reader.query.filter;


/**
 * Filters records based on whether they fulfill a given condition
 *
 * @param <T> Type to filter for
 */
public interface IPeopleFilter<T> {

	/**
	 *
	 * @param record
	 * @return True if record should be included
	 */
	public boolean passesFilter(T record);
}
