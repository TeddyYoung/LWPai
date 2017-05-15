package com.yfzx.lwpai.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 查看幸运码3级页面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年8月16日
 */
public class GetUserOfferByGuid implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7892745677985747107L;
	/**
	 * message : 成功获取幸运码 result :
	 * [{"Numbers":"11,33,27,15,4,3,1"},{"Numbers":"1,9,18,32,15,4,2"
	 * },{"Numbers"
	 * :"22,12,25,8,16,9,10"},{"Numbers":"27,8,32,29,11,3,7"},{"Numbers"
	 * :"30,23,19,3,1,5,6"
	 * },{"Numbers":"19,13,12,27,4,3,1"},{"Numbers":"27,5,18,24,26,9,11"
	 * },{"Numbers"
	 * :"24,23,3,22,15,14,13"},{"Numbers":"14,13,19,20,2,9,2"},{"Numbers"
	 * :"13,8,3,12,25,17,8"}] success : True
	 */
	private String message;
	private List<GetUserByGuidResultEntity> result;
	private String success;

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetUserByGuidResultEntity> getResult() {
		return result;
	}

	public void setResult(List<GetUserByGuidResultEntity> result) {
		this.result = result;
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

}
