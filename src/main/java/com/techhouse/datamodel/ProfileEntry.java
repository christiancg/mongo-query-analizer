package com.techhouse.datamodel;

import java.util.List;

public class ProfileEntry {
	private int milliseconds;
	private QueryType type;
	private UsesIndex indexed;
	private List<String> lSearchFields;

	public int getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public QueryType getType() {
		return type;
	}

	public void setType(QueryType type) {
		this.type = type;
	}

	public UsesIndex getIndexed() {
		return indexed;
	}

	public void setIndexed(UsesIndex indexed) {
		this.indexed = indexed;
	}

	public List<String> getlSearchFields() {
		return lSearchFields;
	}

	public void setlSearchFields(List<String> lSearchFields) {
		this.lSearchFields = lSearchFields;
	}

	public ProfileEntry(int milliseconds, QueryType type, UsesIndex indexed, List<String> lSearchFields) {
		super();
		this.milliseconds = milliseconds;
		this.type = type;
		this.indexed = indexed;
		this.lSearchFields = lSearchFields;
	}
}
