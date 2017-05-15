package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class TryAreaProductByIdResult implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -993453432970192261L;
	/**
     * MarketPrice : 79
     * LotteryResults : 9,10,19,21,23,32,8
     * WinnerName : 186****29
     * CurrentCount : 77
     * WinUserId : 1737
     * UnitPrice : 1.0000
     * ProductId : 323
     * Fees : 0.0000
     * EndDate : 2015/4/14 21:50:00
     * LimitCount : 0
     * SalePrice : 0
     * StartDate : 2015/4/11 9:00:00
     * PeriodIndex : 1
     * WinnerNum : 28-16-19-29-17-10-09
     * ProductName : 小米（MI） 移动电源 原厂正品10400mAh毫安铝合金外壳 银色
     * Id : 2
     * MaxNumPercentage : 0
     * OrderShareId : 13
     * IsFinish : 2
     */
    private int MarketPrice;
    private String LotteryResults;
    private String WinnerName;
    private String CurrentCount;
    private String WinUserId;
    private String UnitPrice;
    private int ProductId;
    private String Fees;
    private String EndDate;
    private String LimitCount;
    private int SalePrice;
    private String StartDate;
    private String PeriodIndex;
    private String WinnerNum;
    private String ProductName;
    private String Id;
    private String MaxNumPercentage;
    private String OrderShareId;
    private String IsFinish;

    public void setMarketPrice(int MarketPrice) {
        this.MarketPrice = MarketPrice;
    }

    public void setLotteryResults(String LotteryResults) {
        this.LotteryResults = LotteryResults;
    }

    public void setWinnerName(String WinnerName) {
        this.WinnerName = WinnerName;
    }

    public void setCurrentCount(String CurrentCount) {
        this.CurrentCount = CurrentCount;
    }

    public void setWinUserId(String WinUserId) {
        this.WinUserId = WinUserId;
    }

    public void setUnitPrice(String UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public void setFees(String Fees) {
        this.Fees = Fees;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public void setLimitCount(String LimitCount) {
        this.LimitCount = LimitCount;
    }

    public void setSalePrice(int SalePrice) {
        this.SalePrice = SalePrice;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public void setPeriodIndex(String PeriodIndex) {
        this.PeriodIndex = PeriodIndex;
    }

    public void setWinnerNum(String WinnerNum) {
        this.WinnerNum = WinnerNum;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setMaxNumPercentage(String MaxNumPercentage) {
        this.MaxNumPercentage = MaxNumPercentage;
    }

    public void setOrderShareId(String OrderShareId) {
        this.OrderShareId = OrderShareId;
    }

    public void setIsFinish(String IsFinish) {
        this.IsFinish = IsFinish;
    }

    public int getMarketPrice() {
        return MarketPrice;
    }

    public String getLotteryResults() {
        return LotteryResults;
    }

    public String getWinnerName() {
        return WinnerName;
    }

    public String getCurrentCount() {
        return CurrentCount;
    }

    public String getWinUserId() {
        return WinUserId;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public int getProductId() {
        return ProductId;
    }

    public String getFees() {
        return Fees;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getLimitCount() {
        return LimitCount;
    }

    public int getSalePrice() {
        return SalePrice;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getPeriodIndex() {
        return PeriodIndex;
    }

    public String getWinnerNum() {
        return WinnerNum;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getId() {
        return Id;
    }

    public String getMaxNumPercentage() {
        return MaxNumPercentage;
    }

    public String getOrderShareId() {
        return OrderShareId;
    }

    public String getIsFinish() {
        return IsFinish;
    }


}
