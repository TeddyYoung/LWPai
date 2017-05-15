/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: AppIndexDate.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年2月11日
 */
package com.yfzx.lwpai.entity;

import java.io.Serializable;

/**
 * 首页轮番数据
 * 
 * @author: huiyang.yu
 * @version Revision: 0.0.1
 * @Date: 2015年2月11日
 */
public class MpIndexData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5814307711399631031L;

	/**
	 * 
	 */

	private SysPicture pictureId;// 图片ID

	private String type;// 类别

	private String url;// URL

	private String state;// 状态

	private String sysId;

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public SysPicture getPictureId() {
		return pictureId;
	}

	public void setPictureId(SysPicture pictureId) {
		this.pictureId = pictureId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
