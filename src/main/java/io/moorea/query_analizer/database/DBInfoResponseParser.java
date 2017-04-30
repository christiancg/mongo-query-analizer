package io.moorea.query_analizer.database;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.bson.Document;
import io.moorea.datamodel.CollectionStats;
import io.moorea.datamodel.DbStats;

public class DBInfoResponseParser {
	public static DbStats parseDbStats(Document toParse) {
		DbStats result = null;
		try {
			result = new DbStats(toParse.getString("db"), toParse.getInteger("collections"),
					toParse.getInteger("views"), toParse.getInteger("objects"), toParse.getInteger("avgObjSize"),
					toParse.getInteger("dataSize"), toParse.getInteger("storageSize"), toParse.getInteger("numExtents"),
					toParse.getInteger("indexes"), toParse.getInteger("indexSize"));
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
}
