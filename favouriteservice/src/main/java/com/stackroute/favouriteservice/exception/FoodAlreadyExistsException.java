package com.stackroute.favouriteservice.exception;

@SuppressWarnings("serial")
public class FoodAlreadyExistsException extends Exception {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FoodAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "FoodAlreadyExistsException [message=" + message + "]";
	}
	
}

