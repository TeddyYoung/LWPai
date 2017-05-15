package com.yfzx.lwpai.entity;

import java.io.Serializable;


/**
 * 首页红包区Result
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-14
 */
public class HomeRedResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3048253629654411581L;
	/**
	 * EndDate : 2015-07-21 21:50:00 Count : 3358 AuctionProductsId : 227
	 * StartDate : 2015-06-17 22:00:00 SalePrice : 4788 ThumbnailUrl440 :
	 * http://img.lwpai.com/Storage/master/product/thumbs440/440_03364
	 * a47fee74bf89b9a970941626e7e.jpg ThumbnailUrl100 :
	 * http://img.lwpai.com/Storage/master/product/thumbs100/100_03364
	 * a47fee74bf89b9a970941626e7e.jpg ProductName : iPhone6
	 */
	private String EndDate;
	private int Count;
	private int AuctionProductsId;
	private String StartDate;
	private int SalePrice;
	private String ThumbnailUrl440;
	private String ThumbnailUrl100;
	private String ProductName;

	public void setEndDate(String EndDate) {
		this.EndDate = EndDate;
	}

	public void setCount(int Count) {
		this.Count = Count;
	}

	public void setAuctionProductsId(int AuctionProductsId) {
		this.AuctionProductsId = AuctionProductsId;
	}

	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}

	public void setSalePrice(int SalePrice) {
		this.SalePrice = SalePrice;
	}

	public void setThumbnailUrl440(String ThumbnailUrl440) {
		this.ThumbnailUrl440 = ThumbnailUrl440;
	}

	public void setThumbnailUrl100(String ThumbnailUrl100) {
		this.ThumbnailUrl100 = ThumbnailUrl100;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public String getEndDate() {
		return EndDate;
	}

	public int getCount() {
		return Count;
	}

	public int getAuctionProductsId() {
		return AuctionProductsId;
	}

	public String getStartDate() {
		return StartDate;
	}

	public int getSalePrice() {
		return SalePrice;
	}

	public String getThumbnailUrl440() {
		return ThumbnailUrl440;
	}

	public String getThumbnailUrl100() {
		return ThumbnailUrl100;
	}

	public String getProductName() {
		return ProductName;
	}
}
