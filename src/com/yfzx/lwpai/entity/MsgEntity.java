package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class MsgEntity {

	/**
	 * message : 成功获取消息记录 result :
	 * [{"CategoryId":"2","MessageId":"34917","Date":
	 * "2015/7/3 11:32:43","IsRead"
	 * :"False","Content":"hhhh","Title":"ceshi"},{"CategoryId"
	 * :"1","MessageId":"17899"
	 * ,"Date":"2015/7/1 9:54:56","IsRead":"False","Content":
	 * "尊敬的乐网拍会员：\u201c0元拍宝马\u201c火热进行中，截止时间8月4日，速点此http://t.cn/R2Emfsg查看详情或关注微信号：lewangpai 回复\u201dBMW\u201d。"
	 * ,"Title":"\u201c0元拍宝马\u201c火热进行中"},{"CategoryId":"1","MessageId":"6540",
	 * "Date":"2015/5/8 17:00:46","IsRead":"True","Content":
	 * "我们很高兴的通知您，您订购的商品已经寄出，请您注意查收，订单号：201505072348458。【乐网拍】"
	 * ,"Title":"订单状态已改变为：卖家已发货"
	 * },{"CategoryId":"1","MessageId":"6539","Date":"2015/5/8 17:00:16"
	 * ,"IsRead":"True","Content":
	 * "我们很高兴的通知您，您订购的商品已经寄出，请您注意查收，订单号：201505076394812。【乐网拍】"
	 * ,"Title":"订单状态已改变为：卖家已发货"}] success : True Total : 4
	 */
	private String message;
	private List<ResultEntity> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<ResultEntity> result) {
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

	public List<ResultEntity> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}

	public class ResultEntity {
		/**
		 * CategoryId : 2 MessageId : 34917 Date : 2015/7/3 11:32:43 IsRead :
		 * False Content : hhhh Title : ceshi
		 */
		private String CategoryId;
		private String MessageId;
		private String Date;
		private String IsRead;
		private String Content;
		private String Title;

		public void setCategoryId(String CategoryId) {
			this.CategoryId = CategoryId;
		}

		public void setMessageId(String MessageId) {
			this.MessageId = MessageId;
		}

		public void setDate(String Date) {
			this.Date = Date;
		}

		public void setIsRead(String IsRead) {
			this.IsRead = IsRead;
		}

		public void setContent(String Content) {
			this.Content = Content;
		}

		public void setTitle(String Title) {
			this.Title = Title;
		}

		public String getCategoryId() {
			return CategoryId;
		}

		public String getMessageId() {
			return MessageId;
		}

		public String getDate() {
			return Date;
		}

		public String getIsRead() {
			return IsRead;
		}

		public String getContent() {
			return Content;
		}

		public String getTitle() {
			return Title;
		}
	}
}
