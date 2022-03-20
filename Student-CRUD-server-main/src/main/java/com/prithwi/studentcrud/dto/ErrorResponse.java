package com.prithwi.studentcrud.dto;

public class ErrorResponse {

private Throwable cause;
	
	private String message;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(Throwable cause, String message) {
		super();
		this.cause = cause;
		this.message = message;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [cause=" + cause + ", message=" + message + "]";
	}

}
