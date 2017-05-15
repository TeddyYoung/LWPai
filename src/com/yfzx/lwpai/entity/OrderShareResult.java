package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class OrderShareResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7746228244491939448L;

	/**
	 * VistiCounts : 18 CreateTime : 2015/7/7 2:20:07 ImageUrl1 :
	 * http://img.lwpai.com/Storage/master/user/shaidan/8317/
	 * 100_8317_ca84f0e3c1e54cb991cee4f1b1095c0e.jpg UserName : mi**02 Id : 147
	 * FaceImage : http://www.lwpai.com/Storage/master/user/faceimages/thumbs200
	 * /200_8317.jpg Content : 夏天到了，风扇就来了。PS：空调啥时候来耶 Title : 五片落地扇 Zan : 0
	 */
	private int VistiCounts;
	private String CreateTime;
	private String ImageUrl1;
	private String UserName;
	private int Id;
	private String FaceImage;
	private String Content;
	private String Title;
	private int Zan;

	public void setVistiCounts(int VistiCounts) {
		this.VistiCounts = VistiCounts;
	}

	public void setCreateTime(String CreateTime) {
		this.CreateTime = CreateTime;
	}

	public void setImageUrl1(String ImageUrl1) {
		this.ImageUrl1 = ImageUrl1;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public void setFaceImage(String FaceImage) {
		this.FaceImage = FaceImage;
	}

	public void setContent(String Content) {
		this.Content = Content;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public void setZan(int Zan) {
		this.Zan = Zan;
	}

	public int getVistiCounts() {
		return VistiCounts;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public String getImageUrl1() {
		return ImageUrl1;
	}

	public String getUserName() {
		return UserName;
	}

	public int getId() {
		return Id;
	}

	public String getFaceImage() {
		return FaceImage;
	}

	public String getContent() {
		return Content;
	}

	public String getTitle() {
		return Title;
	}

	public int getZan() {
		return Zan;
	}

}
