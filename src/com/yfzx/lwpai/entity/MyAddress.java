package com.yfzx.lwpai.entity;

import java.util.ArrayList;
import java.util.List;
	


public class MyAddress {
	private String success;
	private String message;
	private List<Address> result = new ArrayList<Address>();
	
	public MyAddress() {
		super();
	}

	

	public String getSuccess() {
		return success;
	}



	public void setSuccess(String success) {
		this.success = success;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}


	

	public List<Address> getResult() {
		return result;
	}



	public void setResult(List<Address> result) {
		this.result = result;
	}


	
}


