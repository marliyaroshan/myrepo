package com.springboot.example.springbootdemoproject.model;

public class ExceptionResponse {
	private String message;
	private String description;

	public ExceptionResponse(String message, String details) {
		this.message = message;
		this.description = details;
	}

	public ExceptionResponse() {
		super();
	}

	/**
	 * 
	 * @return the message
	 * 
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param message
	 *
	 * 
	 *            the message to set
	 * 
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 * @return the description
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *
	 * 
	 *            the description to set
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
