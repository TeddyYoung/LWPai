package com.yfzx.lwpai.entity;


/**
 * 手机号码返回结果
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月2日
 */
public class Phone {
	   /**
     * message : 
     * result : {}
     * success : False
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
    }
}
