package com.yfzx.lwpai.entity;

import java.io.Serializable;

/**
 * 用户信息
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-29
 */
public class User implements Serializable {

	private static final long serialVersionUID = -2351739752019432717L;
	private String userId;// id
	private String userName;// 昵称
	private String faceImage;// 头像
	private String cellPhone;// 电话

	private String account;// 账户
	private String password;// 密码
	private String paymentpassword;// 支付密码
	private String realName;// 支付密码

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFaceImage() {
		return faceImage;
	}

	public void setFaceImage(String faceImage) {
		this.faceImage = faceImage;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaymentpassword() {
		return paymentpassword;
	}

	public void setPaymentpassword(String paymentpassword) {
		this.paymentpassword = paymentpassword;
	}

}
