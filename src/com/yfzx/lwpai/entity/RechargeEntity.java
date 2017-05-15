package com.yfzx.lwpai.entity;

/**
 * Created by Yang on 2015/7/16.
 */
public class RechargeEntity {

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
		
		private String partner;
		private String urlSing;
		private String url;

		public void setPartner(String partner) {
			this.partner = partner;
		}

		public void setUrlSing(String urlSing) {
			this.urlSing = urlSing;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getPartner() {
			return partner;
		}

		public String getUrlSing() {
			return urlSing;
		}

		public String getUrl() {
			return url;
		}
	}
}
