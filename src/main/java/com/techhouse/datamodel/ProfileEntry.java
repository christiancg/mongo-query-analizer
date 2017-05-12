package com.techhouse.datamodel;

import java.util.Calendar;
import java.util.List;

public class ProfileEntry {
	private String nameSpace;
	private int milliseconds;
	private String clientIp;
	private String user;
	private QueryType type;
	private Calendar timeStamp;
	private UsesIndex indexed;
	private List<String> lSearchFields;
	private int responseLength;
	private int numDocsReturned;
	private int keysExamined;
	private int docsExamined;
	private int numDeleted;
	private int numInserted;
	private int numMatched;
	private int numModified;
	private int keysInserted;
	private int keysDeleted;
	private int writeConflicts;
	private int numYield;

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public int getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public QueryType getType() {
		return type;
	}

	public void setType(QueryType type) {
		this.type = type;
	}

	public Calendar getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Calendar timeStamp) {
		this.timeStamp = timeStamp;
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

	public int getResponseLength() {
		return responseLength;
	}

	public void setResponseLength(int responseLength) {
		this.responseLength = responseLength;
	}

	public int getNumDocsReturned() {
		return numDocsReturned;
	}

	public void setNumDocsReturned(int numDocsReturned) {
		this.numDocsReturned = numDocsReturned;
	}

	public int getKeysExamined() {
		return keysExamined;
	}

	public void setKeysExamined(int keysExamined) {
		this.keysExamined = keysExamined;
	}

	public int getDocsExamined() {
		return docsExamined;
	}

	public void setDocsExamined(int docsExamined) {
		this.docsExamined = docsExamined;
	}

	public int getNumDeleted() {
		return numDeleted;
	}

	public void setNumDeleted(int numDeleted) {
		this.numDeleted = numDeleted;
	}

	public int getNumInserted() {
		return numInserted;
	}

	public void setNumInserted(int numInserted) {
		this.numInserted = numInserted;
	}

	public int getNumMatched() {
		return numMatched;
	}

	public void setNumMatched(int numMatched) {
		this.numMatched = numMatched;
	}

	public int getNumModified() {
		return numModified;
	}

	public void setNumModified(int numModified) {
		this.numModified = numModified;
	}

	public int getKeysInserted() {
		return keysInserted;
	}

	public void setKeysInserted(int keysInserted) {
		this.keysInserted = keysInserted;
	}

	public int getKeysDeleted() {
		return keysDeleted;
	}

	public void setKeysDeleted(int keysDeleted) {
		this.keysDeleted = keysDeleted;
	}

	public int getWriteConflicts() {
		return writeConflicts;
	}

	public void setWriteConflicts(int writeConflicts) {
		this.writeConflicts = writeConflicts;
	}

	public int getNumYield() {
		return numYield;
	}

	public void setNumYield(int numYield) {
		this.numYield = numYield;
	}

	public ProfileEntry(String nameSpace, int milliseconds, String clientIp, String user, QueryType type,
			Calendar timeStamp, UsesIndex indexed, List<String> lSearchFields, int responseLength, int numDocsReturned,
			int keysExamined, int docsExamined, int numDeleted, int numInserted, int numMatched, int numModified,
			int keysInserted, int keysDeleted, int writeConflicts, int numYield) {
		super();
		this.nameSpace = nameSpace;
		this.milliseconds = milliseconds;
		this.clientIp = clientIp;
		this.user = user;
		this.type = type;
		this.timeStamp = timeStamp;
		this.indexed = indexed;
		this.lSearchFields = lSearchFields;
		this.responseLength = responseLength;
		this.numDocsReturned = numDocsReturned;
		this.keysExamined = keysExamined;
		this.docsExamined = docsExamined;
		this.numDeleted = numDeleted;
		this.numInserted = numInserted;
		this.numMatched = numMatched;
		this.numModified = numModified;
		this.keysInserted = keysInserted;
		this.keysDeleted = keysDeleted;
		this.writeConflicts = writeConflicts;
		this.numYield = numYield;
	}
}
