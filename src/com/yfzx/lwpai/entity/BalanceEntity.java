package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class BalanceEntity {

	/**
	 * message : 成功获取账户列表信息 result :
	 * [{"TradeDate":"2015/7/20 14:39:13","IncomeExpenses"
	 * :"-1.00","TradeTypeName"
	 * :"幸运拍冻结金"},{"TradeDate":"2015/7/20 14:39:13","IncomeExpenses"
	 * :"-1.00","TradeTypeName"
	 * :"幸运拍报名费"},{"TradeDate":"2015/7/20 14:33:58","IncomeExpenses"
	 * :"-1.00","TradeTypeName"
	 * :"幸运拍冻结金"},{"TradeDate":"2015/7/20 14:33:58","IncomeExpenses"
	 * :"-1.00","TradeTypeName"
	 * :"幸运拍报名费"},{"TradeDate":"2015/7/20 14:33:19","IncomeExpenses"
	 * :"-1.00","TradeTypeName"
	 * :"幸运拍冻结金"},{"TradeDate":"2015/7/20 14:33:19","IncomeExpenses"
	 * :"-1.00","TradeTypeName"
	 * :"幸运拍报名费"},{"TradeDate":"2015/7/20 14:27:50","IncomeExpenses"
	 * :"-1.00","TradeTypeName"
	 * :"幸运拍冻结金"},{"TradeDate":"2015/7/20 14:27:50","IncomeExpenses"
	 * :"-1.00","TradeTypeName"
	 * :"幸运拍报名费"},{"TradeDate":"2015/7/20 14:25:21","IncomeExpenses"
	 * :"-1.00","TradeTypeName"
	 * :"幸运拍冻结金"},{"TradeDate":"2015/7/20 14:25:21","IncomeExpenses"
	 * :"-0.00","TradeTypeName":"幸运拍报名费"}] UserBalance : 89.0 UserableBalance :
	 * 72.0 success : True Total : 136
	 */
	private String message;
	private List<ResultEntity> result;
	private double UserBalance;
	private double UserableBalance;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<ResultEntity> result) {
		this.result = result;
	}

	public void setUserBalance(double UserBalance) {
		this.UserBalance = UserBalance;
	}

	public void setUserableBalance(double UserableBalance) {
		this.UserableBalance = UserableBalance;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public void setTotal(int Total) {
		this.Total = Total;
	}

	public String getMessage() {
		return message;
	}

	public List<ResultEntity> getResult() {
		return result;
	}

	public double getUserBalance() {
		return UserBalance;
	}

	public double getUserableBalance() {
		return UserableBalance;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}

	public class ResultEntity {
		/**
		 * TradeDate : 2015/7/20 14:39:13 IncomeExpenses : -1.00 TradeTypeName :
		 * 幸运拍冻结金
		 */
		private String TradeDate;
		private String IncomeExpenses;
		private String TradeTypeName;
		private String Balance;

		public String getBalance() {
			return Balance;
		}

		public void setBalance(String balance) {
			Balance = balance;
		}

		public void setTradeDate(String TradeDate) {
			this.TradeDate = TradeDate;
		}

		public void setIncomeExpenses(String IncomeExpenses) {
			this.IncomeExpenses = IncomeExpenses;
		}

		public void setTradeTypeName(String TradeTypeName) {
			this.TradeTypeName = TradeTypeName;
		}

		public String getTradeDate() {
			return TradeDate;
		}

		public String getIncomeExpenses() {
			return IncomeExpenses;
		}

		public String getTradeTypeName() {
			return TradeTypeName;
		}
	}
}
