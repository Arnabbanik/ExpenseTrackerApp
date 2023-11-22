package com.example.expensetracerapi.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorObject {

	private int statusCode;

	private String message;
	
	private Date timeStamp;
	
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
}
