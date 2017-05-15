package com.yfzx.lwpai.entity;

public class GoodsIdEntity {

	private String time;
	private String goodid;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getGoodid() {
		return goodid;
	}

	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}

	public GoodsIdEntity(String time, String goodid) {
		super();
		this.time = time;
		this.goodid = goodid;
	}

}
