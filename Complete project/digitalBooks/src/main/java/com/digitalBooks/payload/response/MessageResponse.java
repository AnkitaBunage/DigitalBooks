package com.digitalBooks.payload.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	 private HttpStatus httpStatus;

	    private LocalDateTime timestamp;

	    //private String errmessage;

	    private String details;
	    private int value;
	    public MessageResponse(HttpStatus httpStatus, String message, String details) {
	        this.httpStatus = httpStatus;
	        this.timestamp = LocalDateTime.now();
	        this.message = message;
	        this.details = details;
	    }
	    public MessageResponse(int value, String details) {
	        this.httpStatus = httpStatus;
	        this.timestamp = LocalDateTime.now();
	        this.message = message;
	        this.details = details;
	    }

		public HttpStatus getHttpStatus() {
			return httpStatus;
		}

		public void setHttpStatus(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
			this.details = details;
		}
}
