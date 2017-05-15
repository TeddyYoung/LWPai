package com.yfzx.library.core;

public class BaseResponse {

	private String success;
	private String message;

	/**
	 * @author: bangwei.yang
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}

	/**
	 * @author: bangwei.yang
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(String success) {
		this.success = success;
	}

	/**
	 * @author: bangwei.yang
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @author: bangwei.yang
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
