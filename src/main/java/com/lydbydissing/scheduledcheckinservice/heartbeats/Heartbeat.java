package com.lydbydissing.scheduledcheckinservice.heartbeats;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Heartbeat {

	private String location;
	private Date localDate;
	private URI uri;
	
	public Heartbeat(URI uri, String location) throws URISyntaxException {
		// configure URL
		this.uri = uri;
		
		//configure location
		this.location = location;
		
		//set epoch
		this.localDate = new Date();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setLocalData(Date date) {
		this.localDate = date;
	}
	
	public Date getLocalDate() {
		return this.localDate;
	}

	public URI getUri() {
		return this.uri;
	}
	
	@Override
	public String toString() {
		return "Heartbeat{" + "location='" + location + '\'' + ", epoch=" + localDate + '}';
	}
}
