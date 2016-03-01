package com.endava.tarapana.rest.exception;

public class ErrorInfo {

	private String error;

	public ErrorInfo() {

	}

	public ErrorInfo(String errorMessage) {
		this.error = errorMessage;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
