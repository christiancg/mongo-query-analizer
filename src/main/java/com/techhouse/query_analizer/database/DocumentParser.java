package com.techhouse.query_analizer.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DocumentParser {

	public static List<String> searchOperators(Document toSearch) {
		List<String> result = null;
		try {
			result = new ArrayList<String>();
			Object query = toSearch.get("query");
			if (query != null) {
				JsonObject auxDoc = new Gson().fromJson(toSearch.toJson(), JsonObject.class);
				if (auxDoc.has("filter"))
					for (Entry<String, JsonElement> elem : auxDoc.get("filter").getAsJsonObject().entrySet())
						result.add(elem.getKey());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
