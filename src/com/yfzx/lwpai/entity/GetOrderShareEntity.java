package com.yfzx.lwpai.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 晒单信息
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月16日
 */
public class GetOrderShareEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8321880109579149755L;
	/**
	 * message : 成功获取晒单信息 result :
	 * [{"CreateTime":"2015/6/30 22:40:33","ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/user/shaidan/5449/440_5449_1aa6d09516364317909bcf9138e6c85f.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/user/shaidan/5449/100_5449_1aa6d09516364317909bcf9138e6c85f.jpg"
	 * ,"UserName":"a5**76","Id":137,"FaceImage":
	 * "http://www.lwpai.com/utility/pics/tx.gif"
	 * ,"Content":"希望能有更好、更多好玩的东西！！","Title":"样子很嘻哈"}] success : True Total : 1
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

	public class ResultEntity implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6876216870509330510L;
		/**
		 * CreateTime : 2015/6/30 22:40:33 ThumbnailUrl440 :
		 * http://img.lwpai.com/Storage/master/user/shaidan/5449/440_5449_1
		 * aa6d09516364317909bcf9138e6c85f.jpg ThumbnailUrl100 :
		 * http://img.lwpai.com/Storage/master/user/shaidan/5449/100_5449_1
		 * aa6d09516364317909bcf9138e6c85f.jpg UserName : a5**76 Id : 137
		 * FaceImage : http://www.lwpai.com/utility/pics/tx.gif Content :
		 * 希望能有更好、更多好玩的东西！！ Title : 样子很嘻哈
		 */
		private String CreateTime;
		private String ThumbnailUrl440;
		private String ThumbnailUrl100;
		private String UserName;
		private int Id;
		private String FaceImage;
		private String Content;
		private String Title;

		public void setCreateTime(String CreateTime) {
			this.CreateTime = CreateTime;
		}

		public void setThumbnailUrl440(String ThumbnailUrl440) {
			this.ThumbnailUrl440 = ThumbnailUrl440;
		}

		public void setThumbnailUrl100(String ThumbnailUrl100) {
			this.ThumbnailUrl100 = ThumbnailUrl100;
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

		public String getCreateTime() {
			return CreateTime;
		}

		public String getThumbnailUrl440() {
			return ThumbnailUrl440;
		}

		public String getThumbnailUrl100() {
			return ThumbnailUrl100;
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
	}
}
