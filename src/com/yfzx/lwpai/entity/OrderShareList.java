package com.yfzx.lwpai.entity;

import java.util.List;

public class OrderShareList {
	/**
	 * message : 成功获取晒单列表 result :
	 * [{"VistiCounts":18,"CreateTime":"2015/7/7 2:20:07","ImageUrl1":
	 * "http://img.lwpai.com/Storage/master/user/shaidan/8317/100_8317_ca84f0e3c1e54cb991cee4f1b1095c0e.jpg"
	 * ,"UserName":"mi**02","Id":147,"FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_8317.jpg"
	 * ,"Content":"夏天到了，风扇就来了。PS：空调啥时候来耶","Title":"五片落地扇","Zan":0}] success :
	 * True Total : 88
	 */
	private String message;
	private List<OrderShareResult> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<OrderShareResult> result) {
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

	public List<OrderShareResult> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}
}
