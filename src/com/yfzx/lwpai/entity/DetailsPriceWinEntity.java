package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 中拍记录实体
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月23日
 */
public class DetailsPriceWinEntity {
	/**
	 * message : 成功获取往期中拍记录 result :
	 * [{"LuckyIp":"125.79.55.75","SumCount":"16965"
	 * ,"winLotteryNum":"2,3,33,12,28,14,7"
	 * ,"EndDate":"2015/4/19 21:50:00","LuckyCity"
	 * :"漳州市","PeriodIndex":"1","ProductName"
	 * :"0元拍iPhone6","UserName":"cl**lj","FaceImage"
	 * :"http://www.lwpai.com/utility/pics/tx.gif","LuckyProvince":"福建省"}]
	 * success : True Total : 1
	 */
	private String message;
	private List<DetailsPriceResult> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<DetailsPriceResult> result) {
		this.result = result;
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

	public List<DetailsPriceResult> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}
}
