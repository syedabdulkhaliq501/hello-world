package com.woobloo.exception;

public class AppException  {

	private static final long serialVersionUID = -8999932578270387947L;

	/**
	 * contains redundantly the HTTP status of the response sent back to the
	 * client in case of error, so that the developer does not have to look into
	 * the response headers. If null a default
	 */
	String status;

	/** application specific error code */
	int code;

	/** detailed error description for developers */
	String message;

	/**
	 * 
	 * @param status
	 * @param code
	 * @param message
	 */
	public AppException(String status, int code, String message) {
		
		this.status = status;
		this.code = code;
		this.message = message;

	}

	public AppException() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}