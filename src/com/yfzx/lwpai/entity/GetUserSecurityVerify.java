package com.yfzx.lwpai.entity;

import java.io.Serializable;
import java.util.List;

public class GetUserSecurityVerify implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3783379834941020152L;
	/**
     * message : 成功获取安全设置信息
     * result : [{"IsVerifyDentity":"0","IsOpenBalance":"True","IsVerifyPhone":"1"}]
     * success : True
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
         * IsVerifyDentity : 0
         * IsOpenBalance : True
         * IsVerifyPhone : 1
         */
        private String IsVerifyDentity;
        private String IsOpenBalance;
        private String IsVerifyPhone;

        public void setIsVerifyDentity(String IsVerifyDentity) {
            this.IsVerifyDentity = IsVerifyDentity;
        }

        public void setIsOpenBalance(String IsOpenBalance) {
            this.IsOpenBalance = IsOpenBalance;
        }

        public void setIsVerifyPhone(String IsVerifyPhone) {
            this.IsVerifyPhone = IsVerifyPhone;
        }

        public String getIsVerifyDentity() {
            return IsVerifyDentity;
        }

        public String getIsOpenBalance() {
            return IsOpenBalance;
        }

        public String getIsVerifyPhone() {
            return IsVerifyPhone;
        }
    }
}
