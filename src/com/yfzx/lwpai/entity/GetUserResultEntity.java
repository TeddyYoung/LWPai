package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class GetUserResultEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3704840826588255150L;
	/**
	 * OfferCount : 1 ResultState : 1 PeriodIndex : ProductName : Id : 34997
	 * OfferTime : 2015/7/25 23:57:39 LuckyGUID :
	 * 7963b077ebb94c66a5ea4aa27951fb59 LuckyId : 2
	 */
	private String OfferCount;
	private String ResultState;
	private String PeriodIndex;
	private String ProductName;
	private String Id;
	private String OfferTime;
	private String LuckyGUID;
	private String LuckyId;
	private String Numbers;

	public String getNumbers() {
		return Numbers;
	}

	public void setNumbers(String numbers) {
		Numbers = numbers;
	}

	public void setOfferCount(String OfferCount) {
		this.OfferCount = OfferCount;
	}

	public void setResultState(String ResultState) {
		this.ResultState = ResultState;
	}

	public void setPeriodIndex(String PeriodIndex) {
		this.PeriodIndex = PeriodIndex;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public void setOfferTime(String OfferTime) {
		this.OfferTime = OfferTime;
	}

	public void setLuckyGUID(String LuckyGUID) {
		this.LuckyGUID = LuckyGUID;
	}

	public void setLuckyId(String LuckyId) {
		this.LuckyId = LuckyId;
	}

	public String getOfferCount() {
		return OfferCount;
	}

	public String getResultState() {
		return ResultState;
	}

	public String getPeriodIndex() {
		return PeriodIndex;
	}

	public String getProductName() {
		return ProductName;
	}

	public String getId() {
		return Id;
	}

	public String getOfferTime() {
		return OfferTime;
	}

	public String getLuckyGUID() {
		return LuckyGUID;
	}

	public String getLuckyId() {
		return LuckyId;
	}
}
