package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 物流
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-8-9
 */
public class GetLogisticsEntity {
	/**
	 * message : 查询物流信息成功 result :
	 * {"nu":"9398823382","companytype":"jd","com":"jd"
	 * ,"updatetime":"2015-08-09 20:49:15"
	 * ,"signname":"","condition":"F00","status"
	 * :"200","codenumber":"9398823382",
	 * "data":[{"time":"2015-05-26 17:10:29","location"
	 * :"","context":"货物已完成配送，感谢您选择京东配送"
	 * ,"ftime":"2015-05-26 17:10:29"},{"time":"2015-05-26 14:15:24"
	 * ,"location":"","context":"配送员开始配送，请您准备收货，配送员，程记，手机号，15711558294","ftime":
	 * "2015-05-26 14:15:24"
	 * },{"time":"2015-05-26 14:05:58","location":"","context"
	 * :"货物已分配，等待配送","ftime"
	 * :"2015-05-26 14:05:58"},{"time":"2015-05-26 14:05:20"
	 * ,"location":"","context"
	 * :"货物已到达【厦门五通站】","ftime":"2015-05-26 14:05:20"},{"time"
	 * :"2015-05-26 10:41:57"
	 * ,"location":"","context":"货物已完成分拣，离开【厦门分拨中心】","ftime"
	 * :"2015-05-26 10:41:57"
	 * },{"time":"2015-05-26 10:34:24","location":"","context"
	 * :"货物已交付京东快递","ftime"
	 * :"2015-05-26 10:34:24"}],"signedtime":"","state":"3","addressee"
	 * :"","departure"
	 * :"","destination":"","message":"ok","ischeck":"1","pickuptime":""}
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
		 * nu : 9398823382 companytype : jd com : jd updatetime : 2015-08-09
		 * 20:49:15 signname : condition : F00 status : 200 codenumber :
		 * 9398823382 data :
		 * [{"time":"2015-05-26 17:10:29","location":"","context"
		 * :"货物已完成配送，感谢您选择京东配送"
		 * ,"ftime":"2015-05-26 17:10:29"},{"time":"2015-05-26 14:15:24"
		 * ,"location"
		 * :"","context":"配送员开始配送，请您准备收货，配送员，程记，手机号，15711558294","ftime"
		 * :"2015-05-26 14:15:24"
		 * },{"time":"2015-05-26 14:05:58","location":"","context"
		 * :"货物已分配，等待配送","ftime"
		 * :"2015-05-26 14:05:58"},{"time":"2015-05-26 14:05:20"
		 * ,"location":"","context"
		 * :"货物已到达【厦门五通站】","ftime":"2015-05-26 14:05:20"}
		 * ,{"time":"2015-05-26 10:41:57"
		 * ,"location":"","context":"货物已完成分拣，离开【厦门分拨中心】"
		 * ,"ftime":"2015-05-26 10:41:57"
		 * },{"time":"2015-05-26 10:34:24","location"
		 * :"","context":"货物已交付京东快递","ftime":"2015-05-26 10:34:24"}] signedtime
		 * : state : 3 addressee : departure : destination : message : ok
		 * ischeck : 1 pickuptime :
		 */
		private String nu;
		private String companytype;
		private String com;
		private String updatetime;
		private String signname;
		private String condition;
		private String status;
		private String codenumber;
		private List<GetLogisticsResult> data;
		private String signedtime;
		private String state;
		private String addressee;
		private String departure;
		private String destination;
		private String message;
		private String ischeck;
		private String pickuptime;

		public void setNu(String nu) {
			this.nu = nu;
		}

		public void setCompanytype(String companytype) {
			this.companytype = companytype;
		}

		public void setCom(String com) {
			this.com = com;
		}

		public void setUpdatetime(String updatetime) {
			this.updatetime = updatetime;
		}

		public void setSignname(String signname) {
			this.signname = signname;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public void setCodenumber(String codenumber) {
			this.codenumber = codenumber;
		}

		public void setData(List<GetLogisticsResult> data) {
			this.data = data;
		}

		public void setSignedtime(String signedtime) {
			this.signedtime = signedtime;
		}

		public void setState(String state) {
			this.state = state;
		}

		public void setAddressee(String addressee) {
			this.addressee = addressee;
		}

		public void setDeparture(String departure) {
			this.departure = departure;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public void setIscheck(String ischeck) {
			this.ischeck = ischeck;
		}

		public void setPickuptime(String pickuptime) {
			this.pickuptime = pickuptime;
		}

		public String getNu() {
			return nu;
		}

		public String getCompanytype() {
			return companytype;
		}

		public String getCom() {
			return com;
		}

		public String getUpdatetime() {
			return updatetime;
		}

		public String getSignname() {
			return signname;
		}

		public String getCondition() {
			return condition;
		}

		public String getStatus() {
			return status;
		}

		public String getCodenumber() {
			return codenumber;
		}

		public List<GetLogisticsResult> getData() {
			return data;
		}

		public String getSignedtime() {
			return signedtime;
		}

		public String getState() {
			return state;
		}

		public String getAddressee() {
			return addressee;
		}

		public String getDeparture() {
			return departure;
		}

		public String getDestination() {
			return destination;
		}

		public String getMessage() {
			return message;
		}

		public String getIscheck() {
			return ischeck;
		}

		public String getPickuptime() {
			return pickuptime;
		}

	}

}
