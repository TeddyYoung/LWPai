package com.yfzx.lwpai.entity;

public class GetVersionUrlEntity {
	/**
	 * message : 成功获取版本信息 result :
	 * {"DownLoadUrl":"http://www.lwpai.com/weixin/",
	 * "VersionNum":"0","CategoryId"
	 * :"3","Id":"0","UpdateDate":"","VersionContent":"0","IsUpdate":"0"}
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
		 * DownLoadUrl : http://www.lwpai.com/weixin/ VersionNum : 0 CategoryId
		 * : 3 Id : 0 UpdateDate : VersionContent : 0 IsUpdate : 0
		 */
		private String DownLoadUrl;
		private String VersionNum;
		private String CategoryId;
		private String Id;
		private String UpdateDate;
		private String VersionContent;
		private String IsUpdate;

		public void setDownLoadUrl(String DownLoadUrl) {
			this.DownLoadUrl = DownLoadUrl;
		}

		public void setVersionNum(String VersionNum) {
			this.VersionNum = VersionNum;
		}

		public void setCategoryId(String CategoryId) {
			this.CategoryId = CategoryId;
		}

		public void setId(String Id) {
			this.Id = Id;
		}

		public void setUpdateDate(String UpdateDate) {
			this.UpdateDate = UpdateDate;
		}

		public void setVersionContent(String VersionContent) {
			this.VersionContent = VersionContent;
		}

		public void setIsUpdate(String IsUpdate) {
			this.IsUpdate = IsUpdate;
		}

		public String getDownLoadUrl() {
			return DownLoadUrl;
		}

		public String getVersionNum() {
			return VersionNum;
		}

		public String getCategoryId() {
			return CategoryId;
		}

		public String getId() {
			return Id;
		}

		public String getUpdateDate() {
			return UpdateDate;
		}

		public String getVersionContent() {
			return VersionContent;
		}

		public String getIsUpdate() {
			return IsUpdate;
		}
	}

}
