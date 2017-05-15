package com.yfzx.lwpai.entity;

/**
 * 用户注册
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-1
 */
public class Register {
	/**
	 * message : 用户名已被注册 result : {} success : False
	 */
	private String message;
	private ResultEntity result;
	private String success;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(ResultEntity result) {
		this.result = result;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public ResultEntity getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public class ResultEntity {
	}
}
