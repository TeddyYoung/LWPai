package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class GetUserByGuidResultEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4966829410758093146L;
	/**
	 * Numbers : 11,33,27,15,4,3,1
	 */
	private String Numbers;

	public void setNumbers(String Numbers) {
		this.Numbers = Numbers;
	}

	public String getNumbers() {
		return Numbers;
	}
}
