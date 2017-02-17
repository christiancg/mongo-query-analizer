package io.moorea.query_analizer.database;

import org.bson.Document;

import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoIterable;

public class DbHelper {
	public static MongoIterable<String> getDatabases(){
		try {
			return DbConnectionSingleton.getConnection().listDatabaseNames();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static MongoIterable<String> getCollections(String dbName){
		try {
			return DbConnectionSingleton.getConnection().getDatabase(dbName).listCollectionNames();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ListIndexesIterable<Document> getIndexes(String dbName,String collectionName){
		try {
			return DbConnectionSingleton.getConnection().getDatabase(dbName).getCollection(collectionName).listIndexes();
		} catch (Exception e) {
			return null;
		}
	}
}
