package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class RedBagDetailsEntity {

	/**
	 * message : 成功获取红包使用信息 result :
	 * [{"HongBaoName":"晒单奖励","TryAreaProductId":"120"
	 * ,"GetTime":"2015/5/19 17:57:12"
	 * ,"PeriodIndex":"1","UsedMoney":"10.0000","ProductName"
	 * :"日本tiger/虎牌MMW-A48C标准型不锈钢保温杯0.48L（发货颜色随机）"
	 * ,"UsedTime":"2015/5/19 18:00:59","ExpiredTime":"2015/8/19 17:57:12"}]
	 * Parvalue : 10.0000 Balance : 0.0000 success : True
	 */
	private String message;
	private List<ResultEntity> result;
	private String Parvalue;
	private String Balance;
	private String success;
	private String HongBaoName;

	public String getHongBaoName() {
		return HongBaoName;
	}

	public void setHongBaoName(String hongBaoName) {
		HongBaoName = hongBaoName;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<ResultEntity> result) {
		this.result = result;
	}

	public void setParvalue(String Parvalue) {
		this.Parvalue = Parvalue;
	}

	public void setBalance(String Balance) {
		this.Balance = Balance;
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

	public String getParvalue() {
		return Parvalue;
	}

	public String getBalance() {
		return Balance;
	}

	public String getSuccess() {
		return success;
	}

	public class ResultEntity {
		/**
		 * HongBaoName : 晒单奖励 TryAreaProductId : 120 GetTime : 2015/5/19
		 * 17:57:12 PeriodIndex : 1 UsedMoney : 10.0000 ProductName :
		 * 日本tiger/虎牌MMW-A48C标准型不锈钢保温杯0.48L（发货颜色随机） UsedTime : 2015/5/19
		 * 18:00:59 ExpiredTime : 2015/8/19 17:57:12
		 */
		private String HongBaoName;
		private String TryAreaProductId;
		private String GetTime;
		private String PeriodIndex;
		private String UsedMoney;
		private String ProductName;
		private String UsedTime;
		private String ExpiredTime;

		public void setHongBaoName(String HongBaoName) {
			this.HongBaoName = HongBaoName;
		}

		public void setTryAreaProductId(String TryAreaProductId) {
			this.TryAreaProductId = TryAreaProductId;
		}

		public void setGetTime(String GetTime) {
			this.GetTime = GetTime;
		}

		public void setPeriodIndex(String PeriodIndex) {
			this.PeriodIndex = PeriodIndex;
		}

		public void setUsedMoney(String UsedMoney) {
			this.UsedMoney = UsedMoney;
		}

		public void setProductName(String ProductName) {
			this.ProductName = ProductName;
		}

		public void setUsedTime(String UsedTime) {
			this.UsedTime = UsedTime;
		}

		public void setExpiredTime(String ExpiredTime) {
			this.ExpiredTime = ExpiredTime;
		}

		public String getHongBaoName() {
			return HongBaoName;
		}

		public String getTryAreaProductId() {
			return TryAreaProductId;
		}

		public String getGetTime() {
			return GetTime;
		}

		public String getPeriodIndex() {
			return PeriodIndex;
		}

		public String getUsedMoney() {
			return UsedMoney;
		}

		public String getProductName() {
			return ProductName;
		}

		public String getUsedTime() {
			return UsedTime;
		}

		public String getExpiredTime() {
			return ExpiredTime;
		}
	}
}
