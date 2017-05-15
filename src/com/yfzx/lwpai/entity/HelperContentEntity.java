package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class HelperContentEntity {

	private String message;
	private List<ResultEntity> result;
	private String success;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<ResultEntity> result) {
		this.result = result;
	}

	public void setSuccess(String success) {
		this.success = success;
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

	public class ResultEntity {
		private String Title;
		private String Content;

		public String getTitle() {
			return Title;
		}

		public void setTitle(String title) {
			Title = title;
		}

		public String getContent() {
			return Content;
		}

		public void setContent(String content) {
			Content = content;
		}
	}
}
