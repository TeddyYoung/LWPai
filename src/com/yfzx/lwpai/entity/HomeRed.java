package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 首页红包区实体
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-14
 */
public class HomeRed {

	/**
	 * message : 成功获取商品信息 result :
	 * [{"EndDate":"2015-07-21 21:50:00","Count":3358
	 * ,"AuctionProductsId":227,"StartDate"
	 * :"2015-06-17 22:00:00","SalePrice":4788,"ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_03364a47fee74bf89b9a970941626e7e.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_03364a47fee74bf89b9a970941626e7e.jpg"
	 * ,"ProductName":"iPhone6"}] success : True Total : 1
	 */
	private String message;
	private List<HomeRedResult> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<HomeRedResult> result) {
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

	public List<HomeRedResult> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}
}
