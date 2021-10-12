package com.springBoot.mongo.exception;

public class TodoCoollectionException extends Exception {

	public TodoCoollectionException(String message) {
		super(message);
	}

	public static String NotFoundException(String id) {
		return "Todo with " + id + " not Found!";
	}

	public static String TodoAlreadyExists() {
		return "Todo with given name alredy exists";
	}
}
