package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 幸运拍出价记录
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月17日
 */
public class LuckyGetLuckyOfferEntity {

	/**
	 * message : 成功获取出价记录 result :
	 * [{"LuckyIp":"124.72.71.114","LuckyCity":"厦门市",
	 * "OfferNum":"9","UserName":"ws**yh"
	 * ,"OfferTime":"2015/4/19 20:29:18","FaceImage"
	 * :"http://www.lwpai.com/utility/pics/tx.gif"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp"
	 * :"113.113.16.43","LuckyCity":"","OfferNum"
	 * :"1","UserName":"153****92","OfferTime":"2015/4/19 20:29:06","FaceImage":
	 * "http://www.lwpai.com/utility/pics/tx.gif"
	 * ,"LuckyProvince":"广东省"},{"LuckyIp"
	 * :"222.76.146.157","LuckyCity":"厦门市","OfferNum"
	 * :"1","UserName":"187****52","OfferTime":"2015/4/19 20:28:02","FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:27:52",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"218.85.114.70","LuckyCity":"厦门市",
	 * "OfferNum"
	 * :"99","UserName":"sk**22","OfferTime":"2015/4/19 20:27:49","FaceImage"
	 * :"http://www.lwpai.com/utility/pics/tx.gif"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp"
	 * :"124.72.71.114","LuckyCity":"厦门市","OfferNum"
	 * :"5","UserName":"ws**yh","OfferTime":"2015/4/19 20:27:46","FaceImage":
	 * "http://www.lwpai.com/utility/pics/tx.gif"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp"
	 * :"222.76.146.157","LuckyCity":"厦门市","OfferNum"
	 * :"1","UserName":"187****52","OfferTime":"2015/4/19 20:27:33","FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:27:14",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:27:04",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:26:54",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:26:34",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:26:24",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"110.84.40.115","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"159****37","OfferTime":"2015/4/19 20:25:35",
	 * "FaceImage" :"http://www.lwpai.com/utility/pics/tx.gif"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp"
	 * :"222.76.146.157","LuckyCity":"厦门市","OfferNum"
	 * :"1","UserName":"187****52","OfferTime":"2015/4/19 20:25:33","FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:25:09",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:25:00",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:24:48",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"124.72.71.114","LuckyCity":"厦门市",
	 * "OfferNum"
	 * :"1","UserName":"ws**yh","OfferTime":"2015/4/19 20:24:42","FaceImage"
	 * :"http://www.lwpai.com/utility/pics/tx.gif"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp"
	 * :"222.76.146.157","LuckyCity":"厦门市","OfferNum"
	 * :"1","UserName":"187****52","OfferTime":"2015/4/19 20:24:37","FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"},{"LuckyIp":"222.76.146.157","LuckyCity":"厦门市",
	 * "OfferNum" :"1","UserName":"187****52","OfferTime":"2015/4/19 20:24:25",
	 * "FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1210.jpg"
	 * ,"LuckyProvince":"福建省"}] success : True
	 */
	private String message;
	private List<DidResultEntity> result;
	private String success;
	private int Total;

	public int getTotal() {
		return Total;
	}

	public void setTotal(int total) {
		Total = total;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public List<DidResultEntity> getResult() {
		return result;
	}

	public void setResult(List<DidResultEntity> result) {
		this.result = result;
	}

	public String getSuccess() {
		return success;
	}

}
