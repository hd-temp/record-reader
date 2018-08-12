package com.ai.reader.query.filter;

/**
 * Checks for exact matching of field
 *
 * @param <T> Type of field to be checked for equivalency
 */
public class EqualsFilter<T> implements IPeopleFilter<T> {

	private T matchField;

	public EqualsFilter(T matchField) {
		this.matchField = matchField;
	}

	@Override
	public boolean passesFilter(T record) {
		return record.equals(matchField);
	}
}
