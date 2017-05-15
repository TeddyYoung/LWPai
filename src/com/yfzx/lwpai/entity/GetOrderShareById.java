package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 晒单详细页面
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-8-15
 */
public class GetOrderShareById {

	/**
	 * message : 成功获取晒单信息 result :
	 * [{"VistiCounts":6,"CreateTime":"2015/8/8 17:30:17"
	 * ,"UserName":"137****60",
	 * "Id":254,"FaceImage":"http://www.lwpai.com/utility/pics/tx.gif"
	 * ,"Content":"又一次中奖，希望好运常在<div class=\"ordershareimgdiv\"><img src=\
	 * "http://img.lwpai.com/Storage/master/user/shaidan/18111/1000_18111_d208ff67c9494751a0148a3776c14300.jpg\"/><\/div><d
	 * i v class=\"ordershareimgdiv\"><img src=\
	 * "http://img.lwpai.com/Storage/master/user/shaidan/18111/1000_18111_be8d3ff389154d05ab50dbec3700a04b.jpg\"/><\/div>","Title":"又中了","Zan":
	 * 9 } ] success : True
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
		 * VistiCounts : 6 CreateTime : 2015/8/8 17:30:17 UserName : 137****60
		 * Id : 254 FaceImage : http://www.lwpai.com/utility/pics/tx.gif Content
		 * : 又一次中奖，希望好运常在<div class="ordershareimgdiv"><img src=
		 * "http://img.lwpai.com/Storage/master/user/shaidan/18111/1000_18111_d208ff67c9494751a0148a3776c14300.jpg"
		 * /></div><div class="ordershareimgdiv"><img src=
		 * "http://img.lwpai.com/Storage/master/user/shaidan/18111/1000_18111_be8d3ff389154d05ab50dbec3700a04b.jpg"
		 * /></div> Title : 又中了 Zan : 9
		 */
		private int VistiCounts;
		private String CreateTime;
		private String UserName;
		private int Id;
		private String FaceImage;
		private String Content;
		private String Title;
		private int Zan;

		public void setVistiCounts(int VistiCounts) {
			this.VistiCounts = VistiCounts;
		}

		public void setCreateTime(String CreateTime) {
			this.CreateTime = CreateTime;
		}

		public void setUserName(String UserName) {
			this.UserName = UserName;
		}

		public void setId(int Id) {
			this.Id = Id;
		}

		public void setFaceImage(String FaceImage) {
			this.FaceImage = FaceImage;
		}

		public void setContent(String Content) {
			this.Content = Content;
		}

		public void setTitle(String Title) {
			this.Title = Title;
		}

		public void setZan(int Zan) {
			this.Zan = Zan;
		}

		public int getVistiCounts() {
			return VistiCounts;
		}

		public String getCreateTime() {
			return CreateTime;
		}

		public String getUserName() {
			return UserName;
		}

		public int getId() {
			return Id;
		}

		public String getFaceImage() {
			return FaceImage;
		}

		public String getContent() {
			return Content;
		}

		public String getTitle() {
			return Title;
		}

		public int getZan() {
			return Zan;
		}
	}

}
