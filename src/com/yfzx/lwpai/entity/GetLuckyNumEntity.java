package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 幸运码
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-8-9
 */
public class GetLuckyNumEntity {
	/**
	 * message : 成功获取幸运码 result : [{"Numbers":"16,12,6,26,14,11,2"}] success :
	 * True
	 */
	private String message;
	private List<ResultEntity> result;
	private String success;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<ResultEntity> result) {
		this.result = result;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public List<ResultEntity> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public class ResultEntity {
		/**
		 * Numbers : 16,12,6,26,14,11,2
		 */
		private String Numbers;

		public void setNumbers(String Numbers) {
			this.Numbers = Numbers;
		}

		public String getNumbers() {
			return Numbers;
		}
	}

}
