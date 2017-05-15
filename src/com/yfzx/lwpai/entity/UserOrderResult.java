package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class UserOrderResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4967723539695440098L;

	/**
	 * OrderStatusName : 无收货信息 OrderStatus : 14 OrderDate : 2015/7/16 21:50:12
	 * CategoryName : 幸运拍 OrderId : 201507165881376 CloseReason :
	 * OrderTypeProductId : 225 ProductId : 623 OrderType : 6 ThumbnailUrl100 :
	 * http://img.lwpai.com/Storage/master/product/thumbs100
	 * /100_4eedd95890e547bbaa1f1d859fc0418c.jpg ThumbnailUrl440 :
	 * http://img.lwpai.com/Storage/master/product/thumbs440/
	 * 440_4eedd95890e547bbaa1f1d859fc0418c.jpg ProductName : 台湾食品 活力本味层层棒3袋
	 * OrderShareId : 0
	 */
	private String OrderStatusName;
	private String OrderStatus;
	private String OrderDate;
	private String CategoryName;
	private String OrderId;
	private String CloseReason;
	private String OrderTypeProductId;
	private String ProductId;
	private String OrderType;
	private String ThumbnailUrl100;
	private String ThumbnailUrl440;
	private String ProductName;
	private String OrderShareId;

	public void setOrderStatusName(String OrderStatusName) {
		this.OrderStatusName = OrderStatusName;
	}

	public void setOrderStatus(String OrderStatus) {
		this.OrderStatus = OrderStatus;
	}

	public void setOrderDate(String OrderDate) {
		this.OrderDate = OrderDate;
	}

	public void setCategoryName(String CategoryName) {
		this.CategoryName = CategoryName;
	}

	public void setOrderId(String OrderId) {
		this.OrderId = OrderId;
	}

	public void setCloseReason(String CloseReason) {
		this.CloseReason = CloseReason;
	}

	public void setOrderTypeProductId(String OrderTypeProductId) {
		this.OrderTypeProductId = OrderTypeProductId;
	}

	public void setProductId(String ProductId) {
		this.ProductId = ProductId;
	}

	public void setOrderType(String OrderType) {
		this.OrderType = OrderType;
	}

	public void setThumbnailUrl100(String ThumbnailUrl100) {
		this.ThumbnailUrl100 = ThumbnailUrl100;
	}

	public void setThumbnailUrl440(String ThumbnailUrl440) {
		this.ThumbnailUrl440 = ThumbnailUrl440;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public void setOrderShareId(String OrderShareId) {
		this.OrderShareId = OrderShareId;
	}

	public String getOrderStatusName() {
		return OrderStatusName;
	}

	public String getOrderStatus() {
		return OrderStatus;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public String getOrderId() {
		return OrderId;
	}

	public String getCloseReason() {
		return CloseReason;
	}

	public String getOrderTypeProductId() {
		return OrderTypeProductId;
	}

	public String getProductId() {
		return ProductId;
	}

	public String getOrderType() {
		return OrderType;
	}

	public String getThumbnailUrl100() {
		return ThumbnailUrl100;
	}

	public String getThumbnailUrl440() {
		return ThumbnailUrl440;
	}

	public String getProductName() {
		return ProductName;
	}

	public String getOrderShareId() {
		return OrderShareId;
	}

}
