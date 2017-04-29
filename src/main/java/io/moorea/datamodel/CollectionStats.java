package io.moorea.datamodel;

import java.util.Dictionary;
import java.util.Hashtable;

public class CollectionStats {
	private String namespace;
	private int size;
	private int documentCount;
	private int avgObjectSize;
	private int storageSize;
	private boolean isCapped;
	/*
	 * aca faltan propiedades de la coleccion bastante especificas, por ahora ignorar
	 * */
	private int numIndexes;
	private int totalIndexSize;
	private Dictionary<String,Integer> lIndexSize = new Hashtable<String, Integer>();
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getDocumentCount() {
		return documentCount;
	}
	public void setDocumentCount(int documentCount) {
		this.documentCount = documentCount;
	}
	public int getAvgObjectSize() {
		return avgObjectSize;
	}
	public void setAvgObjectSize(int avgObjectSize) {
		this.avgObjectSize = avgObjectSize;
	}
	public int getStorageSize() {
		return storageSize;
	}
	public void setStorageSize(int storageSize) {
		this.storageSize = storageSize;
	}
	public boolean isCapped() {
		return isCapped;
	}
	public void setCapped(boolean isCapped) {
		this.isCapped = isCapped;
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
	public Dictionary<String, Integer> getlIndexSize() {
		return lIndexSize;
	}
	public void setlIndexSize(Dictionary<String, Integer> lIndexSize) {
		this.lIndexSize = lIndexSize;
	}
	public CollectionStats(String namespace, int size, int documentCount, int avgObjectSize, int storageSize,
			boolean isCapped, int numIndexes, int totalIndexSize, Dictionary<String, Integer> lIndexSize) {
		super();
		this.namespace = namespace;
		this.size = size;
		this.documentCount = documentCount;
		this.avgObjectSize = avgObjectSize;
		this.storageSize = storageSize;
		this.isCapped = isCapped;
		this.numIndexes = numIndexes;
		this.totalIndexSize = totalIndexSize;
		this.lIndexSize = lIndexSize;
	}
}
