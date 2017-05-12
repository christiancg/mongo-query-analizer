package com.techhouse.query_analizer.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.techhouse.datamodel.CollectionStats;
import com.techhouse.datamodel.DbStats;
import com.techhouse.datamodel.ProfileEntry;
import com.techhouse.datamodel.QueryType;
import com.techhouse.datamodel.UsesIndex;

public class DBResponseParser {
	public static DbStats parseDbStats(Document toParse) {
		DbStats result = null;
		try {
			result = new DbStats(toParse.getString("db"), toParse.getInteger("collections"),
					toParse.getInteger("views"), toParse.getInteger("objects"), toParse.getDouble("avgObjSize"),
					toParse.getDouble("dataSize"), toParse.getDouble("storageSize"), toParse.getInteger("numExtents"),
					toParse.getInteger("indexes"), toParse.getDouble("indexSize"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static CollectionStats parseCollectionStats(Document toParse) {
		CollectionStats result = null;
		try {
			Map<String, Integer> indxSizes = new HashMap<String, Integer>();
			Document auxDoc = (Document) toParse.get("indexSizes");
			if (auxDoc != null)
				for (String key : auxDoc.keySet())
					indxSizes.put(key, auxDoc.getInteger(key));
			Integer avgObjSize = toParse.getInteger("avgObjSize") == null ? 0 : toParse.getInteger("avgObjSize");
			result = new CollectionStats(toParse.getString("ns"), toParse.getInteger("size"),
					toParse.getInteger("count"), avgObjSize, toParse.getInteger("storageSize"),
					toParse.getBoolean("capped"), toParse.getInteger("nindexes"), toParse.getInteger("totalIndexSize"),
					indxSizes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<ProfileEntry> parseProfileEntries(FindIterable<Document> results){
		List<ProfileEntry> parsed = null;
		try {
			parsed = new ArrayList<ProfileEntry>();
			for (Document entry : results) {
				List<String> lSearchFields = DocumentParser.searchOperators(entry);
				parsed.add(new ProfileEntry(entry.getInteger("millis"), QueryType.FIND, UsesIndex.NO, lSearchFields));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parsed;
	}
}
