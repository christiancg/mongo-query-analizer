package io.moorea.query_analizer.database;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import io.moorea.query_analizer.configuration.Configuration;

public class DbConnectionSingleton {
	private static DbConnectionSingleton instance = null;
	private static MongoClient client = null;
	
	private DbConnectionSingleton(){}
	public static MongoClient getConnection(){
		synchronized (DbConnectionSingleton.class) {
			if (instance == null) {
				instance = new DbConnectionSingleton();
				establishConnection();
			}
			return client;
		}
	}
	
	private static void establishConnection(){
		MongoCredential credentia = null;
		List<MongoCredential> credentialsList = null;
		ServerAddress addr = null;
		try {
			addr = new ServerAddress(Configuration.getInstance().getDbConnUrl(),
					Configuration.getInstance().getDbConnPort());
			if (Configuration.getInstance().isDbUseAuthentication()) {
				credentialsList = new ArrayList<MongoCredential>();
				credentia = MongoCredential.createCredential(Configuration.getInstance().getDbUser(),
						Configuration.getInstance().getDbAuthenticationName(),
						Configuration.getInstance().getDbPassword().toCharArray());
				credentialsList.add(credentia);
				client = new MongoClient(addr, credentialsList);
			} else {
				client = new MongoClient(addr);
			}
		} catch (Exception e) {
			instance = null;
			client = null;
		}
	}
}
