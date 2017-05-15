package com.yfzx.lwpai.entity;

public class OrderOperationEntity {
	/**
	 * message : 订单201507211877256 地址修改成功！ result : {} success : True
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
