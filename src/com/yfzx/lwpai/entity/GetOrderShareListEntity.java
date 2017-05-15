package com.yfzx.lwpai.entity;

import java.util.List;

public class GetOrderShareListEntity {
	/**
	 * message : 成功获取晒单列表 result :
	 * [{"LotteryNumber":"12-33-22-10-25-14-15","ManagerMark"
	 * :"TryAreaGrace","OrderStatus"
	 * :5,"ImageUrl1":"/Storage/master/user/shaidan/100_1744_(null)"
	 * ,"WinningNumber"
	 * :"22-23-33-15-25-01-12","UserName":"158****83","ImageUrl3":
	 * "/Storage/master/user/shaidan/1744_f7fba183dc7b41419bd3ecacf4c2a431.png"
	 * ,"ImageUrl2":
	 * "/Storage/master/user/shaidan/1744_fbde14c018674fc5841f32b4ba9ebbba.png"
	 * ,"Zan"
	 * :0,"HongBaoItemId":0,"IsAudit":-1,"ManagerRemark":"3","IsEssence":0,
	 * "PeriodIndex":"2","ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_6e38288ae39746b499748e2c9e57f3e4.jpg"
	 * ,"OrderType":7,"ProductName":"20%中拍率 台湾脆片2罐【即时揭晓】","Content":
	 * "由于突然从 vv 规范"
	 * ,"RemarkUserName":"","CategoryName":"红包区","Meta_Keywords":"",
	 * "DisplaySequence"
	 * :207,"IsRecom":0,"OrderId":"201508066689804","UpdateTime"
	 * :"2015/8/14 15:56:38","UserId":1744,"FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1744.jpg"
	 * ,"OrderTypeProductId":394,"ProductId":754,"Title":"头晕眼花特","VistiCounts":0
	 * ,"CreateTime":"2015/8/6 8:53:05","Meta_Description":"","LotteryDate":
	 * "2015/8/6 8:53:05","ImageUrl5":"","Id":263,"ImageUrl4":
	 * "/Storage/master/user/shaidan/1744_022fbf709e8142128957bba94eaf6b44.png"
	 * ,"Remark":""},{"LotteryNumber":"02-12-29-31-20-24-09","ManagerMark":"",
	 * "OrderStatus":5,"ImageUrl1":
	 * "/Storage/master/user/shaidan/100_5236763b0483431f9250f14c49334f12.png"
	 * ,"WinningNumber"
	 * :"02-12-01-17-13-03-12","UserName":"158****83","ImageUrl3":
	 * "/Storage/master/user/shaidan/1744_425ba8e76b5545ada0723ebc76087726.png"
	 * ,"ImageUrl2":
	 * "/Storage/master/user/shaidan/1744_c43467483d91442babee2585b8c9d11e.png"
	 * ,"Zan":0,"HongBaoItemId":0,"IsAudit":-1,"ManagerRemark":"","IsEssence":0,
	 * "PeriodIndex":"1","ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_41eb754a8613417e8fd9232337f86043.jpg"
	 * ,"OrderType":6,"ProductName":"日本MINISO特警套装玩具1307","Content":"非法家家户户",
	 * "RemarkUserName"
	 * :"","CategoryName":"幸运拍","Meta_Keywords":"","DisplaySequence"
	 * :206,"IsRecom"
	 * :0,"OrderId":"201507304232458","UpdateTime":"2015/8/13 10:24:57"
	 * ,"UserId":1744,"FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1744.jpg"
	 * ,"OrderTypeProductId":339,"ProductId":727,"Title":"哈哈购房人","VistiCounts":0
	 * ,"CreateTime":"2015/7/30 21:50:00","Meta_Description":"","LotteryDate":
	 * "2015/7/30 21:50:00","ImageUrl5":"","Id":262,"ImageUrl4":
	 * "/Storage/master/user/shaidan/1744_436e8b90212e4127b6d4025b1d86cb59.png"
	 * ,"Remark":""},{"LotteryNumber":"02-04-11-16-25-26-12","ManagerMark":
	 * "TryAreaGrace","OrderStatus":5,"ImageUrl1":
	 * "/Storage/master/user/shaidan/100_bba43e3ecada481998da93f78ac31cc6.jpg"
	 * ,"WinningNumber"
	 * :"16-12-06-26-14-11-02","UserName":"158****83","ImageUrl3"
	 * :"","ImageUrl2":
	 * "","Zan":0,"HongBaoItemId":0,"IsAudit":-1,"ManagerRemark":
	 * "1","IsEssence":0,"PeriodIndex":"3","ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_9327e50e2c7d49a9a3a844fa0344e582.jpg"
	 * ,"OrderType":7,"ProductName":"优吉好米 五常稻花香米5kg","Content":"测试日方非法回复地方非常时尚",
	 * "RemarkUserName"
	 * :"","CategoryName":"红包区","Meta_Keywords":"","DisplaySequence"
	 * :205,"IsRecom"
	 * :0,"OrderId":"201505072348458","UpdateTime":"2015/8/13 10:04:39"
	 * ,"UserId":1744,"FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1744.jpg"
	 * ,"OrderTypeProductId":74,"ProductId":369,"Title":"测试天天高规格ugg",
	 * "VistiCounts"
	 * :0,"CreateTime":"2015/5/7 21:50:00","Meta_Description":"","LotteryDate"
	 * :"2015/5/7 21:50:00"
	 * ,"ImageUrl5":"","Id":258,"ImageUrl4":"","Remark":""},{
	 * "LotteryNumber":"02-04-11-16-25-26-12"
	 * ,"ManagerMark":"TryAreaGrace","OrderStatus":5,"ImageUrl1":
	 * "http://img.lwpai.com/Storage/master/user/shaidan/1744/100_1744_c510baf2941e4e4e823f318564137a78.jpg"
	 * ,"WinningNumber":"11-16-02-25-12-06-08","UserName":"158****83",
	 * "ImageUrl3":"","ImageUrl2":"","Zan":0,"HongBaoItemId":7514,"IsAudit":1,
	 * "ManagerRemark":"1","IsEssence":0,"PeriodIndex":"2","ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_9c7443e25876456cbc5b383ec2ef7b6a.jpg"
	 * ,"OrderType":7,"ProductName":"小米（MI）5000mAh 移动电源 红色","Content":
	 * "中拍了，好开心，第一次用红包就拿到东西了，正需要一个移动电源"
	 * ,"RemarkUserName":"","CategoryName":"红包区",
	 * "Meta_Keywords":"","DisplaySequence"
	 * :26,"IsRecom":0,"OrderId":"201505076394812"
	 * ,"UpdateTime":"2015/5/19 17:57:12","UserId":1744,"FaceImage":
	 * "http://www.lwpai.com/Storage/master/user/faceimages/thumbs200/200_1744.jpg"
	 * ,"OrderTypeProductId":72,"ProductId":375,"Title":"好开心终于中拍了，正需要一个移动电源",
	 * "VistiCounts"
	 * :96,"CreateTime":"2015/5/7 21:50:00","Meta_Description":"","LotteryDate"
	 * :"2015/5/7 21:50:00","ImageUrl5":"","Id":30,"ImageUrl4":"","Remark":""}]
	 * success : True Total : 4
	 */
	private String message;
	private List<GetOrderShareListResult> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<GetOrderShareListResult> result) {
		this.result = result;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public void setTotal(int Total) {
		this.Total = Total;
	}

	public String getMessage() {
		return message;
	}

	public List<GetOrderShareListResult> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}

}
