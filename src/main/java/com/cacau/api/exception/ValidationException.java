package com.cacau.api.exception;


public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;

	public ValidationException(String error) {
		super(error);
	}

	public ValidationException(Exception e) {
		super(e.getMessage());
	}

	public ValidationException(String error, Exception e) {
		super(error, e);
	}

}
