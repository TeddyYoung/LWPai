package com.yfzx.lwpai.entity;

import java.io.Serializable;
import java.util.List;

public class GetUserOfferById implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 268870129148173681L;
	/**
	 * message : 成功获取用户出价信息 result :
	 * [{"OfferCount":"1","ResultState":"1","PeriodIndex"
	 * :"","ProductName":"","Id"
	 * :"34997","OfferTime":"2015/7/25 23:57:39","LuckyGUID"
	 * :"7963b077ebb94c66a5ea4aa27951fb59","LuckyId":"2"}] PeriodIndex : 1
	 * ProductName : “0元拍”宝马 您买车我出钱 success : True Total : 9
	 */
	private String message;
	private List<GetUserResultEntity> result;
	private String PeriodIndex;
	private String ProductName;

	public List<GetUserResultEntity> getResult() {
		return result;
	}

	public void setResult(List<GetUserResultEntity> result) {
		this.result = result;
	}

	private String success;
	private String Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPeriodIndex(String PeriodIndex) {
		this.PeriodIndex = PeriodIndex;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public void setTotal(String Total) {
		this.Total = Total;
	}

	public String getMessage() {
		return message;
	}

	public String getPeriodIndex() {
		return PeriodIndex;
	}

	public String getProductName() {
		return ProductName;
	}

	public String getSuccess() {
		return success;
	}

	public String getTotal() {
		return Total;
	}
}
