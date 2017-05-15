package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 4656354223041409870L;
	private String RegionName;
	private String CellPhone;
	private String RegionId;
	private String Address;
	private String Id;
	private String AddressDefault;
	private String Zipcode;
	private String ShipTo;
	private String TelPhone;

	public String getRegionId() {
		return RegionId;
	}

	public void setRegionId(String regionId) {
		RegionId = regionId;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCellPhone() {
		return CellPhone;
	}

	public void setCellPhone(String cellPhone) {
		CellPhone = cellPhone;
	}

	public String getRegionName() {
		return RegionName;
	}

	public void setRegionName(String regionName) {
		RegionName = regionName;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getShipTo() {
		return ShipTo;
	}

	public void setShipTo(String shipTo) {
		ShipTo = shipTo;
	}

	public String getZipcode() {
		return Zipcode;
	}

	public void setZipcode(String zipcode) {
		Zipcode = zipcode;
	}

	public String getTelPhone() {
		return TelPhone;
	}

	public void setTelPhone(String telPhone) {
		TelPhone = telPhone;
	}

	public String getAddressDefault() {
		return AddressDefault;
	}

	public void setAddressDefault(String addressDefault) {
		AddressDefault = addressDefault;
	}

	public Address() {
		super();
	}

}