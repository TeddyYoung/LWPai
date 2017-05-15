package com.yfzx.lwpai.entity;

public class GoodDetail {
	private String success;
	private String message;
	private Result result;

	public GoodDetail() {
		super();
	}

	public GoodDetail(String success, String message, Result result) {
		super();
		this.success = success;
		this.message = message;
		this.result = result;
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

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public class Result {
		private String content;
		private String Title;
		private String AddedDate;

		public String getTitle() {
			return Title;
		}

		public void setTitle(String title) {
			Title = title;
		}

		public String getAddedDate() {
			return AddedDate;
		}

		public void setAddedDate(String addedDate) {
			AddedDate = addedDate;
		}

		public Result(String content) {
			super();
			this.content = content;
		}

		public Result() {
			super();
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

	}

}
