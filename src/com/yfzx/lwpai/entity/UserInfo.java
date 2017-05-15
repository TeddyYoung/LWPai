package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 我的信息
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-5
 */
public class UserInfo {
	private String message;
	private List<ResultEntity> result;
	private String success;

	/**
	 * @author: bangwei.yang
	 * @return the result
	 */
	public List<ResultEntity> getResult() {
		return result;
	}

	/**
	 * @author: bangwei.yang
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<ResultEntity> result) {
		this.result = result;
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

	public String getSuccess() {
		return success;
	}

	public class ResultEntity {
		private String UserId;// 用户id
		private String Username;// 用户名
		private String FaceImage;// 头像
		private String CellPhone;// 电话
		private String UserRank;// 用户类别
		

		public String getUserRank() {
			return UserRank;
		}

		public void setUserRank(String userRank) {
			UserRank = userRank;
		}

		public String getUserId() {
			return UserId;
		}

		public void setUserId(String userId) {
			UserId = userId;
		}

		public String getUsername() {
			return Username;
		}

		public void setUsername(String username) {
			Username = username;
		}

		public String getFaceImage() {
			return FaceImage;
		}

		public void setFaceImage(String faceImage) {
			FaceImage = faceImage;
		}

		public String getCellPhone() {
			return CellPhone;
		}

		public void setCellPhone(String cellPhone) {
			CellPhone = cellPhone;
		}
	}

}
