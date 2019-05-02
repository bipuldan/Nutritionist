package com.stackroute.favouriteservice.exception;

@SuppressWarnings("serial")
public class FoodNotFoundException extends Exception {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FoodNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "FoodNotFoundException [message=" + message + "]";
	}

}

