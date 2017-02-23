package io.moorea.query_analizer.database;

public class ProfilingLevel {
	private int profileLevel;
	private int slowMs;
	public int getProfileLevel() {
		return profileLevel;
	}
	public void setProfileLevel(int profileLevel) {
		this.profileLevel = profileLevel;
	}
	public int getSlowMs() {
		return slowMs;
	}
	public void setSlowMs(int slowMs) {
		this.slowMs = slowMs;
	}
	public ProfilingLevel(int profileLevel, int slowMs) {
		super();
		this.profileLevel = profileLevel;
		this.slowMs = slowMs;
	}
}
