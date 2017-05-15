package com.yfzx.lwpai.entity;

import java.io.Serializable;

/**
 * 物流
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-8-9
 */
public class GetLogisticsResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3496097938744703183L;
	/**
	 * time : 2015-05-26 17:10:29 location : context : 货物已完成配送，感谢您选择京东配送 ftime :
	 * 2015-05-26 17:10:29
	 */
	private String time;
	private String location;
	private String context;
	private String ftime;
	private String message;
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setFtime(String ftime) {
		this.ftime = ftime;
	}

	public String getTime() {
		return time;
	}

	public String getLocation() {
		return location;
	}

	public String getContext() {
		return context;
	}

	public String getFtime() {
		return ftime;
	}

}
