package com.yfzx.lwpai.entity;

import android.R.string;


/**
 * 省市区   key  value
 * @author Gy
 *
 */
public class ChooseAddressKeyValue {
	private String Key;
	private String Value;
	public ChooseAddressKeyValue(String key, String value) {
		super();
		Key = key;
		Value = value;
	}
	public ChooseAddressKeyValue() {
		super();
	}
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	
}
