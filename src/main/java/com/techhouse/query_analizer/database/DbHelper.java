package com.techhouse.query_analizer.database;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoIterable;
import com.techhouse.datamodel.CollectionStats;
import com.techhouse.datamodel.DbStats;
import com.techhouse.datamodel.ProfilingLevel;

public class DbHelper {
	public static MongoIterable<String> getDatabases() {
		try {
			return DbConnectionSingleton.getConnection().listDatabaseNames();
		} catch (Exception e) {
			return null;
		}
	}

	public static MongoIterable<String> getCollections(String dbName) {
		try {
			return DbConnectionSingleton.getConnection().getDatabase(dbName).listCollectionNames();
		} catch (Exception e) {
			return null;
		}
	}

	public static ListIndexesIterable<Document> getIndexes(String dbName, String collectionName) {
		try {
			return DbConnectionSingleton.getConnection().getDatabase(dbName).getCollection(collectionName)
					.listIndexes();
		} catch (Exception e) {
			return null;
		}
	}

	public static ProfilingLevel getProfilingLevel(String dbName) {
		try {
			BsonDocument bd = BsonDocument.parse("{profile:0}");
			Bson toRun = (Bson) bd;
			Document result = DbConnectionSingleton.getConnection().getDatabase(dbName).runCommand(toRun);
			int profLevel = result.getInteger("was");
			int slowMs = result.getInteger("slowms");
			bd = BsonDocument.parse("{profile:" + String.valueOf(profLevel) + "}");
			toRun = (Bson) bd;
			DbConnectionSingleton.getConnection().getDatabase(dbName).runCommand(toRun);
			return new ProfilingLevel(profLevel, slowMs);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean setProfilingLevel(String dbName, int level, int slowMs) {
		try {
			BsonDocument bd = BsonDocument.parse("{profile:" + level + ",slowms:" + slowMs + "}");
			Bson toRun = (Bson) bd;
			Document result = DbConnectionSingleton.getConnection().getDatabase(dbName).runCommand(toRun);
			if (result.getDouble("ok") == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean deleteProfilingInfo(String dbName) {
		try {
			ProfilingLevel plNow = getProfilingLevel(dbName);
			setProfilingLevel(dbName, 0, plNow.getSlowMs());
			DbConnectionSingleton.getConnection().getDatabase(dbName).getCollection("system.profile").drop();
			setProfilingLevel(dbName, plNow.getProfileLevel(), plNow.getSlowMs());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static DbStats getDbStats(String dbName) {
		DbStats result = null;
		try {
			BsonDocument bd = BsonDocument.parse("{ dbStats: 1, scale: 1 }");
			Bson toRun = (Bson) bd;
			Document rawResult = DbConnectionSingleton.getConnection().getDatabase(dbName).runCommand(toRun);
			if (rawResult != null)
				result = DBInfoResponseParser.parseDbStats(rawResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static CollectionStats getCollectionStats(String dbName, String collectionName) {
		CollectionStats result = null;
		try {
			BsonDocument col = BsonDocument.parse(" { collStats: '"+ collectionName +"' , scale: 1 , verbose: false }");
			Bson toRun = (Bson) col;
			Document rawResult = DbConnectionSingleton.getConnection().getDatabase(dbName).runCommand(toRun);
			if(rawResult!=null)
				result = DBInfoResponseParser.parseCollectionStats(rawResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
