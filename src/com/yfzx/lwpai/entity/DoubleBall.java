package com.yfzx.lwpai.entity;

public class DoubleBall {
	/**
	 * message : 成功获取双色球信息 result :
	 * {"SSQ":"06-11-13-19-21-32-04","OpenDate":" 2015-06-30"} success : True
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
		/**
		 * SSQ : 06-11-13-19-21-32-04 OpenDate : 2015-06-30
		 */
		private String SSQ;
		private String OpenDate;

		public void setSSQ(String SSQ) {
			this.SSQ = SSQ;
		}

		public void setOpenDate(String OpenDate) {
			this.OpenDate = OpenDate;
		}

		public String getSSQ() {
			return SSQ;
		}

		public String getOpenDate() {
			return OpenDate;
		}
	}
}
