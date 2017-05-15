package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class BlankEntity {

	/**
	 * message : 暂无银行卡信息 result :
	 * [{"BankCardNumber":"**** **** 4545","CreateTime"
	 * :"2015/7/22 15:58:07","BankUserName"
	 * :"wx1744","BankName":"记录","Uid":"300"}] success : True
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
		 * BankCardNumber : **** **** 4545 CreateTime : 2015/7/22 15:58:07
		 * BankUserName : wx1744 BankName : 记录 Uid : 300
		 */
		private String BankCardNumber;
		private String CreateTime;
		private String BankUserName;
		private String BankName;
		private String Uid;

		public void setBankCardNumber(String BankCardNumber) {
			this.BankCardNumber = BankCardNumber;
		}

		public void setCreateTime(String CreateTime) {
			this.CreateTime = CreateTime;
		}

		public void setBankUserName(String BankUserName) {
			this.BankUserName = BankUserName;
		}

		public void setBankName(String BankName) {
			this.BankName = BankName;
		}

		public void setUid(String Uid) {
			this.Uid = Uid;
		}

		public String getBankCardNumber() {
			return BankCardNumber;
		}

		public String getCreateTime() {
			return CreateTime;
		}

		public String getBankUserName() {
			return BankUserName;
		}

		public String getBankName() {
			return BankName;
		}

		public String getUid() {
			return Uid;
		}
	}
}
