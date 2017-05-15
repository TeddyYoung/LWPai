package com.yfzx.lwpai.entity;

public class GetAfficheListByIdEntity {
	/**
	 * message : 成功获取公告详情 result :
	 * {"Content":"","Title":"红包区部分拍品限时抢通知","AddedDate":"2015/8/5 9:24:38"}
	 * success : True
	 */
	private String message;
	private ResultEntity result;
	private String success;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(ResultEntity result) {
		this.result = result;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public ResultEntity getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public class ResultEntity {
		/**
		 * Content : Title : 红包区部分拍品限时抢通知 AddedDate : 2015/8/5 9:24:38
		 */
		private String Content;
		private String Title;
		private String AddedDate;

		public void setContent(String Content) {
			this.Content = Content;
		}

		public void setTitle(String Title) {
			this.Title = Title;
		}

		public void setAddedDate(String AddedDate) {
			this.AddedDate = AddedDate;
		}

		public String getContent() {
			return Content;
		}

		public String getTitle() {
			return Title;
		}

		public String getAddedDate() {
			return AddedDate;
		}
	}
}
