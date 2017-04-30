package com.techhouse.query_analizer.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {
	
	public boolean getConfigurationFromParameters() {
		boolean result = true;
		try {
			Configuration conf = Configuration.getInstance();
			String dbName = System.getProperty("dbName");
			String dbConnUrl = System.getProperty("dbConnUrl");
			String dbConnPort = System.getProperty("dbConnPort");
			String dbUser = System.getProperty("dbUser");
			String dbPassword = System.getProperty("dbPassword");
			String dbAuthenticationName = System.getProperty("dbAuthenticationName");
			String dbUseAuthentication = System.getProperty("dbUseAuthentication");
			if (dbName != null)
				conf.setDbName(dbName);
			else
				result = false;
			if (dbConnUrl != null)
				conf.setDbConnUrl(dbConnUrl);
			else
				result = false;
			
			if (dbConnPort != null)
				conf.setDbConnPort(Integer.valueOf(dbConnPort));
			else
				result = false;
			if (dbPassword != null)
				conf.setDbPassword(dbPassword);
			else
				result = true;
			if (dbUser != null)
				conf.setDbUser(dbUser);
			else
				result = false;
			if (dbUseAuthentication != null)
				conf.setDbUseAuthentication(Boolean.parseBoolean(dbUseAuthentication));
			else
				result = false;
			if (dbAuthenticationName != null) {
				conf.setDbAuthenticationName(dbAuthenticationName);
			}
			else
				conf.setDbAuthenticationName("admin");
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean getConfigurationFromFile() {
		boolean result = false;
		InputStream inputStream = null;
		try {
			Configuration conf = Configuration.getInstance();
			Properties prop = new Properties();
			String propFileName = "query_analyzer.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			}
			String dbName = prop.getProperty("dbName");
			String dbConnUrl = prop.getProperty("dbConnUrl");
			String dbConnPort = prop.getProperty("dbConnPort");
			String dbUser = prop.getProperty("dbUser");
			String dbPassword = prop.getProperty("dbPassword");
			conf.setDbName(dbName);
			conf.setDbConnUrl(dbConnUrl);
			conf.setDbConnPort(Integer.valueOf(dbConnPort));
			conf.setDbPassword(dbPassword);
			conf.setDbUser(dbUser);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
