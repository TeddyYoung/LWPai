package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 登录返回结果
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-29
 */
public class Login {
	/**
	 * message : 登录成功 result :
	 * [{"CellPhone":"15960360865","Username":"15960360865"
	 * ,"UserId":"14297","FaceImage"
	 * :"http://www.lwpai.com/utility/pics/tx.gif"}] success : True
	 */
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

		/**
		 * CellPhone : 15960360865 Username : 15960360865 UserId : 14297
		 * FaceImage : http://www.lwpai.com/utility/pics/tx.gif
		 */
		private String CellPhone;
		private String Username;
		private String UserId;
		private String FaceImage;
		private String RealName;
		

		public String getRealName() {
			return RealName;
		}

		public void setRealName(String realName) {
			RealName = realName;
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
	}
}
