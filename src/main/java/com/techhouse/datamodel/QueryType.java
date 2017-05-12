package com.techhouse.datamodel;

public enum QueryType {
	COMMAND("command"), COUNT("count"), DISTINCT("distinct"), GEO_NEAR("geoNear"), GET_MORE("getMore"), 
	GROUP("group"), INSERT("insert"), MAP_REDUCE("mapReduce"), QUERY("query"), REMOVE("remove"), UPDATE("update"), OTHER("other");

	private String type;

	QueryType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public static QueryType fromString(String text) {
		for (QueryType b : QueryType.values()) {
			if (b.type.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
}
