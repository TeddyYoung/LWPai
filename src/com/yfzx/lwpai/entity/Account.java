package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 账号明细
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-7
 */
public class Account {

	/**
	 * message : 成功获取账户明细记录 result :
	 * [{"UseableBalance":"0.0000","Integral":"0","HongBao"
	 * :"5","RequestableBanlance":"0.0000"}] success : True
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
		 * UseableBalance : 0.0000 Integral : 0 HongBao : 5 RequestableBanlance
		 * : 0.0000
		 */
		private String UseableBalance;
		private String Integral;
		private String HongBao;
		private String RequestableBanlance;

		public void setUseableBalance(String UseableBalance) {
			this.UseableBalance = UseableBalance;
		}

		public void setIntegral(String Integral) {
			this.Integral = Integral;
		}

		public void setHongBao(String HongBao) {
			this.HongBao = HongBao;
		}

		public void setRequestableBanlance(String RequestableBanlance) {
			this.RequestableBanlance = RequestableBanlance;
		}

		public String getUseableBalance() {
			return UseableBalance;
		}

		public String getIntegral() {
			return Integral;
		}

		public String getHongBao() {
			return HongBao;
		}

		public String getRequestableBanlance() {
			return RequestableBanlance;
		}
	}
}
