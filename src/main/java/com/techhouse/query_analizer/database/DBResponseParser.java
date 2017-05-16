package com.techhouse.query_analizer.database;

import java.util.ArrayList;
import java.util.Calendar;
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

	public static List<ProfileEntry> parseProfileEntries(FindIterable<Document> results) {
		List<ProfileEntry> parsed = null;
		try {
			parsed = new ArrayList<ProfileEntry>();
			for (Document entry : results) {
				List<String> lSearchFields = DocumentParser.searchOperators(entry);
				QueryType type = QueryType.fromString(entry.getString("op"));
				if (type == null)
					type = QueryType.OTHER;
				Calendar ts = Calendar.getInstance();
				ts.setTime(entry.getDate("ts"));
				String clientIp = entry.getString("client");
				String user = entry.getString("user");
				int responseLength = entry.getInteger("responseLength");
				int numDocsReturned = entry.getInteger("nreturned");
				int milliseconds = entry.getInteger("millis");
				String nameSpace = entry.getString("ns");
				int keysExamined = entry.getInteger("keysExamined");
				int docsExamined = entry.getInteger("docsExamined");
				int numDeleted = 0;
				if (entry.get("ndeleted") != null)
					numDeleted = entry.getInteger("ndeleted");
				int numInserted = 0;
				if (entry.get("ninserted") != null)
					numInserted = entry.getInteger("ninserted");
				int numMatched = 0;
				if (entry.get("nMatched") != null)
					numMatched = entry.getInteger("nMatched");
				int numModified = 0;
				if (entry.get("nModified") != null)
					numModified = entry.getInteger("nModified");
				int keysInserted = 0;
				if (entry.get("keysInserted") != null)
					keysInserted = entry.getInteger("keysInserted");
				int keysDeleted = 0;
				if (entry.get("keysDeleted") != null)
					keysDeleted = entry.getInteger("keysDeleted");
				int writeConflicts = 0;
				if (entry.get("writeConflicts") != null)
					writeConflicts = entry.getInteger("writeConflicts");
				int numYield = entry.getInteger("numYield");
				UsesIndex indxType = keysExamined == docsExamined ? UsesIndex.YES
						: keysExamined == 0 ? UsesIndex.NO : UsesIndex.PARTIAL;
				parsed.add(new ProfileEntry(nameSpace, milliseconds, clientIp, user, type, ts, indxType, lSearchFields,
						responseLength, numDocsReturned, keysExamined, docsExamined, numDeleted, numInserted,
						numMatched, numModified, keysInserted, keysDeleted, writeConflicts, numYield));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parsed;
	}
}
