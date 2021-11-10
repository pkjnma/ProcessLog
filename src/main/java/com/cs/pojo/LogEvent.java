package com.cs.pojo;

import java.sql.Timestamp;

public class LogEvent {
	private String id;
	private String state;
	private String type;
	private String host;
	private Timestamp timestamp;
	private long duration;
	private boolean alert;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public boolean isAlert() {
		return alert;
	}
	public void setAlert(boolean alert) {
		this.alert = alert;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}

}
