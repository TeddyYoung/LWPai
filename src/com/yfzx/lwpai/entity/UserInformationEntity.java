package com.yfzx.lwpai.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 我的账户
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-17
 */
public class UserInformationEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -481191185712785970L;
	/**
	 * message : 成功获取用户信息 result :
	 * [{"CellPhone":"18850176291","Username":"18850176291"
	 * ,"UserId":"17792","FaceImage"
	 * :"http://www.lwpai.com/utility/pics/tx.gif","BirthDate"
	 * :"","Sex":"NotSet"}] success : True
	 */
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
		/**
		 * CellPhone : 18850176291 Username : 18850176291 UserId : 17792
		 * FaceImage : http://www.lwpai.com/utility/pics/tx.gif BirthDate : Sex
		 * : NotSet
		 */
		private String CellPhone;
		private String Username;
		private String UserId;
		private String FaceImage;
		private String BirthDate;
		private String Nickname;
		private String Sex;

		public String getNickname() {
			return Nickname;
		}

		public void setNickname(String nickname) {
			Nickname = nickname;
		}

		public void setCellPhone(String CellPhone) {
			this.CellPhone = CellPhone;
		}

		public void setUsername(String Username) {
			this.Username = Username;
		}

		public void setUserId(String UserId) {
			this.UserId = UserId;
		}

		public void setFaceImage(String FaceImage) {
			this.FaceImage = FaceImage;
		}

		public void setBirthDate(String BirthDate) {
			this.BirthDate = BirthDate;
		}

		public void setSex(String Sex) {
			this.Sex = Sex;
		}

		public String getCellPhone() {
			return CellPhone;
		}

		public String getUsername() {
			return Username;
		}

		public String getUserId() {
			return UserId;
		}

		public String getFaceImage() {
			return FaceImage;
		}

		public String getBirthDate() {
			return BirthDate;
		}

		public String getSex() {
			return Sex;
		}
	}

}
