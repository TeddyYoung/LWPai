package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class RedBagInfoEntity {


    /**
     * message : 成功获取用户红包信息
     * result : [{"HongBaoItemId":"18749","HongBaoName":"红包(积分兑换)","Balance":"1.0000"}]
     * success : True
     * Total : 1
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

    public class ResultEntity {
        /**
         * HongBaoItemId : 18749
         * HongBaoName : 红包(积分兑换)
         * Balance : 1.0000
         */
        private String HongBaoItemId;
        private String HongBaoName;
        private String Balance;

        public void setHongBaoItemId(String HongBaoItemId) {
            this.HongBaoItemId = HongBaoItemId;
        }

        public void setHongBaoName(String HongBaoName) {
            this.HongBaoName = HongBaoName;
        }

        public void setBalance(String Balance) {
            this.Balance = Balance;
        }

        public String getHongBaoItemId() {
            return HongBaoItemId;
        }

        public String getHongBaoName() {
            return HongBaoName;
        }

        public String getBalance() {
            return Balance;
        }
    }
}
