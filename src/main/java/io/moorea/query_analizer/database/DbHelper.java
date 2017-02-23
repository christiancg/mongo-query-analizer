package io.moorea.query_analizer.database;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

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
	
	public static ProfilingLevel getProfilingLevel(String dbName){
		try {
			BsonDocument bd = BsonDocument.parse("{profile:0}");
			Bson toRun = (Bson)bd;
			Document result = DbConnectionSingleton.getConnection().getDatabase(dbName).runCommand(toRun);
			int profLevel = (int) result.get("was");
			int slowMs = (int) result.get("slowms");
			bd = BsonDocument.parse("{profile:"+ String.valueOf(profLevel) +"}");
			toRun = (Bson)bd;
			DbConnectionSingleton.getConnection().getDatabase(dbName).runCommand(toRun);
			return new ProfilingLevel(profLevel, slowMs);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean setProfilingLevel(String dbName,int level, int slowMs){
		try {
			BsonDocument bd = BsonDocument.parse("{profile:"+level+",slowms:"+slowMs+"}");
			Bson toRun = (Bson)bd;
			Document result = DbConnectionSingleton.getConnection().getDatabase(dbName).runCommand(toRun);
			if(((int)result.get("ok"))==1)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean deleteProfilingInfo(String dbName){
		try {
			DbConnectionSingleton.getConnection().getDatabase(dbName).getCollection("system.profile").drop();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
