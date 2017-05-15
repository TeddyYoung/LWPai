package com.yfzx.lwpai.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 乐拍记录 幸运拍
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月20日
 */
public class MineLuckyAutionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1783473144179228620L;

	/**
	 * message : 成功获取用户出价信息 result :
	 * [{"SumCount":"1","EndDate":"2015/7/23 21:50:00"
	 * ,"StartDate":"","LotteryResults"
	 * :"","WinnerName":"","PeriodIndex":"3","ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_c050d5b29614495c9dcc327af6e7da40.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_c050d5b29614495c9dcc327af6e7da40.jpg"
	 * ,"WinnerNum":"","ProductName":"进口食品 曲奇饼米果组合","Id":"6705","LuckyId":"275"}
	 * ,{"SumCount":"1","EndDate":"2015/7/23 21:50:00","StartDate":"",
	 * "LotteryResults":"","WinnerName":"","PeriodIndex":"3","ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_1d69154fe5304e8798f860615f4ecd0b.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_1d69154fe5304e8798f860615f4ecd0b.jpg"
	 * ,"WinnerNum":"","ProductName":"台湾进口 活力本味综合果仁地瓜酥2盒","Id":"6704","LuckyId":
	 * "272"},{"SumCount":"1","EndDate":"2015/7/23 21:50:00","StartDate":"",
	 * "LotteryResults":"","WinnerName":"","PeriodIndex":"3","ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_a1636f011c564bafaeb83fe10ad34b43.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_a1636f011c564bafaeb83fe10ad34b43.jpg"
	 * ,"WinnerNum":"","ProductName":"台湾进口 仙贝米果组合","Id":"6703","LuckyId":"273"},
	 * {"SumCount":"1","EndDate":"2015/7/23 21:50:00","StartDate":"",
	 * "LotteryResults":"","WinnerName":"","PeriodIndex":"3","ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_4eedd95890e547bbaa1f1d859fc0418c.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_4eedd95890e547bbaa1f1d859fc0418c.jpg"
	 * ,"WinnerNum":"","ProductName":"台湾食品 活力本味层层棒3袋","Id":"6702","LuckyId":
	 * "274"},{"SumCount":"1","EndDate":"2015/7/23 21:50:00","StartDate":"",
	 * "LotteryResults":"","WinnerName":"","PeriodIndex":"1","ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_87a0a11862cb4f0891b08eb2c1ad7585.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_87a0a11862cb4f0891b08eb2c1ad7585.jpg"
	 * ,"WinnerNum":"","ProductName":"日本MINISO猪形煎锅1214","Id":"6701","LuckyId":
	 * "268"},{"SumCount":"1","EndDate":"2015/7/23 21:50:00","StartDate":"",
	 * "LotteryResults":"","WinnerName":"","PeriodIndex":"1","ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_3573522c3c7741a3a39325e5c1e0fd7f.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_3573522c3c7741a3a39325e5c1e0fd7f.jpg"
	 * ,"WinnerNum":"","ProductName":"日本MINISO无线蓝牙便携小音箱1111(发货颜色随机）","Id":"6700"
	 * ,"LuckyId":"267"},{"SumCount":"1","EndDate":"2015/7/23 21:50:00",
	 * "StartDate":"","LotteryResults":"","WinnerName":"","PeriodIndex":"1",
	 * "ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_2c1d15812db14279b9d940251338875a.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_2c1d15812db14279b9d940251338875a.jpg"
	 * ,"WinnerNum":"","ProductName":"日本MINISO简约家居双层玻璃茶隔杯1010","Id":"6699",
	 * "LuckyId"
	 * :"264"},{"SumCount":"10","EndDate":"2015/7/23 21:50:00","StartDate"
	 * :"","LotteryResults"
	 * :"","WinnerName":"","PeriodIndex":"2","ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_80910112ddf04aab9256cf31243997a9.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_80910112ddf04aab9256cf31243997a9.jpg"
	 * ,"WinnerNum":"","ProductName":"日本MINISO三星二合一充电器升级版1116","Id":"6698",
	 * "LuckyId":"266"}] success : True Total : 8
	 */
	private String message;
	private List<MineLuckEntity> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
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

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}

	public List<MineLuckEntity> getResult() {
		return result;
	}

	public void setResult(List<MineLuckEntity> result) {
		this.result = result;
	}

}
