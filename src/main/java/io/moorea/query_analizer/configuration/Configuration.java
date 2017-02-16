package io.moorea.query_analizer.configuration;

public class Configuration {
	private static Configuration instance = null;

	private Configuration() {
	}

	public static Configuration getInstance() {
		if (instance == null) {
			synchronized (Configuration.class) {
				if (instance == null) {
					instance = new Configuration();
				}
			}
		}
		return instance;
	}

	private String dbName;
	private String dbConnUrl;
	private int dbConnPort;
	private String dbUser;
	private String dbPassword;
	private String dbAuthenticationName;
	private boolean dbUseAuthentication;

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbConnUrl() {
		return dbConnUrl;
	}

	public void setDbConnUrl(String dbConnUrl) {
		this.dbConnUrl = dbConnUrl;
	}

	public int getDbConnPort() {
		return dbConnPort;
	}

	public void setDbConnPort(int dbConnPort) {
		this.dbConnPort = dbConnPort;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbAuthenticationName() {
		return dbAuthenticationName;
	}

	public void setDbAuthenticationName(String dbAuthenticationName) {
		this.dbAuthenticationName = dbAuthenticationName;
	}

	public boolean isDbUseAuthentication() {
		return dbUseAuthentication;
	}

	public void setDbUseAuthentication(boolean dbUseAuthentication) {
		this.dbUseAuthentication = dbUseAuthentication;
	}
}
