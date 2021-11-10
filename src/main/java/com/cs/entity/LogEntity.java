package com.cs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs.pojo.LogEvent;

@Entity
@Table(name="LOGENTITY")
public class LogEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	public LogEntity(LogEvent logEvent) {
		super();
		this.eventId = logEvent.getId();
		this.eventDuration = logEvent.getDuration();
		this.type = logEvent.getType();
		this.host = logEvent.getHost();
		this.alert = logEvent.isAlert();
	}

	@Column(name = "eventid")
    private String eventId;
	
    @Column(name = "eventDuration")
    private long eventDuration;

    @Column(name = "type")
    private String type;

    @Column(name = "host")
    private String host;
    
    @Column(name = "alert")
    private Boolean alert;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public long getEventDuration() {
		return eventDuration;
	}

	public void setEventDuration(long eventDuration) {
		this.eventDuration = eventDuration;
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

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

}
