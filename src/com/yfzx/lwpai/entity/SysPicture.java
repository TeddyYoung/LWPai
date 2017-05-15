/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: SysPicture.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月11日
 */
package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class SysPicture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5693787537960464019L;

	private String updDate;

	private String updTime;

	private String sysId;// 图片描述

	private String pictureDesc;// 图片描述

	private String bigPicture;// 大图

	private String smallPicture;// 小图

	private String type;// 类别

	private String updUser;

	public String getUpdUser() {
		return updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getPictureDesc() {
		return pictureDesc;
	}

	public void setPictureDesc(String pictureDesc) {
		this.pictureDesc = pictureDesc;
	}

	public String getBigPicture() {
		return bigPicture;
	}

	public void setBigPicture(String bigPicture) {
		this.bigPicture = bigPicture;
	}

	public String getSmallPicture() {
		return smallPicture;
	}

	public void setSmallPicture(String smallPicture) {
		this.smallPicture = smallPicture;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

}