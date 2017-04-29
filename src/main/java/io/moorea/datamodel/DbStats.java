package io.moorea.datamodel;

public class DbStats {
	private String DbName;
	private int numCollections;
	private int numViews;
	private int numObjects;
	private int avgObjectSize;
	private int dataSize;
	private int storageSize;
	private int numExtents;
	private int numIndexes;
	private int totalIndexSize;
	public String getDbName() {
		return DbName;
	}
	public void setDbName(String dbName) {
		DbName = dbName;
	}
	public int getNumCollections() {
		return numCollections;
	}
	public void setNumCollections(int numCollections) {
		this.numCollections = numCollections;
	}
	public int getNumViews() {
		return numViews;
	}
	public void setNumViews(int numViews) {
		this.numViews = numViews;
	}
	public int getNumObjects() {
		return numObjects;
	}
	public void setNumObjects(int numObjects) {
		this.numObjects = numObjects;
	}
	public int getAvgObjectSize() {
		return avgObjectSize;
	}
	public void setAvgObjectSize(int avgObjectSize) {
		this.avgObjectSize = avgObjectSize;
	}
	public int getDataSize() {
		return dataSize;
	}
	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}
	public int getStorageSize() {
		return storageSize;
	}
	public void setStorageSize(int storageSize) {
		this.storageSize = storageSize;
	}
	public int getNumExtents() {
		return numExtents;
	}
	public void setNumExtents(int numExtents) {
		this.numExtents = numExtents;
	}
	public int getNumIndexes() {
		return numIndexes;
	}
	public void setNumIndexes(int numIndexes) {
		this.numIndexes = numIndexes;
	}
	public int getTotalIndexSize() {
		return totalIndexSize;
	}
	public void setTotalIndexSize(int totalIndexSize) {
		this.totalIndexSize = totalIndexSize;
	}
	public DbStats(String dbName, int numCollections, int numViews, int numObjects, int avgObjectSize, int dataSize,
			int storageSize, int numExtents, int numIndexes, int totalIndexSize) {
		super();
		DbName = dbName;
		this.numCollections = numCollections;
		this.numViews = numViews;
		this.numObjects = numObjects;
		this.avgObjectSize = avgObjectSize;
		this.dataSize = dataSize;
		this.storageSize = storageSize;
		this.numExtents = numExtents;
		this.numIndexes = numIndexes;
		this.totalIndexSize = totalIndexSize;
	}
}
