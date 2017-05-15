package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 用户订单实体
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-27
 */
public class UserOrderEntity {
	/**
	 * message : 成功获取用户订单信息 result :
	 * [{"OrderStatusName":"无收货信息","OrderStatus":"14"
	 * ,"OrderDate":"2015/7/16 21:50:12"
	 * ,"CategoryName":"幸运拍","OrderId":"201507165881376"
	 * ,"CloseReason":"","OrderTypeProductId"
	 * :"225","ProductId":"623","OrderType":"6","ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_4eedd95890e547bbaa1f1d859fc0418c.jpg"
	 * ,"ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_4eedd95890e547bbaa1f1d859fc0418c.jpg"
	 * ,"ProductName":"台湾食品 活力本味层层棒3袋","OrderShareId":"0"}] success : True Total
	 * : 9
	 */
	private String message;
	private List<UserOrderResult> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<UserOrderResult> result) {
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

	public List<UserOrderResult> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}
}
