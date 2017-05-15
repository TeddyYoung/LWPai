package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/6/30.
 */
public class Order {


    /**
     * message : 成功获取中拍商品记录
     * result : [{"WaitForShare":"1","WaitForSend":"4","WaitForReceive":"0"}]
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
         * WaitForShare : 1
         * WaitForSend : 4
         * WaitForReceive : 0
         */
        private String WaitForShare;
        private String WaitForSend;
        private String WaitForReceive;

        public void setWaitForShare(String WaitForShare) {
            this.WaitForShare = WaitForShare;
        }

        public void setWaitForSend(String WaitForSend) {
            this.WaitForSend = WaitForSend;
        }

        public void setWaitForReceive(String WaitForReceive) {
            this.WaitForReceive = WaitForReceive;
        }

        public String getWaitForShare() {
            return WaitForShare;
        }

        public String getWaitForSend() {
            return WaitForSend;
        }

        public String getWaitForReceive() {
            return WaitForReceive;
        }
    }
}
