package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class RedBagEntity {


    /**
     * message : 成功获取红包列表信息
     * result : [{"HongBaoName":"红包(积分兑换)","GetTime":"2015/7/10 11:55:31","UsedCount":0,"UserName":"WX1744","UserId":1744,"Parvalue":1,"IsUserd":false,"HongBaoItemId":18749,"HongBaoId":6,"UserdMoney":0,"Expr1":6,"Balance":1,"ExpiredTime":"2016/7/10 11:55:31","UrlId":"0"}]
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
         * HongBaoName : 红包(积分兑换)
         * GetTime : 2015/7/10 11:55:31
         * UsedCount : 0
         * UserName : WX1744
         * UserId : 1744
         * Parvalue : 1.0
         * IsUserd : false
         * HongBaoItemId : 18749
         * HongBaoId : 6
         * UserdMoney : 0.0
         * Expr1 : 6
         * Balance : 1.0
         * ExpiredTime : 2016/7/10 11:55:31
         * UrlId : 0
         */
        private String HongBaoName;
        private String GetTime;
        private int UsedCount;
        private String UserName;
        private int UserId;
        private double Parvalue;
        private boolean IsUserd;
        private int HongBaoItemId;
        private int HongBaoId;
        private double UserdMoney;
        private int Expr1;
        private double Balance;
        private String ExpiredTime;
        private String UrlId;

        public void setHongBaoName(String HongBaoName) {
            this.HongBaoName = HongBaoName;
        }

        public void setGetTime(String GetTime) {
            this.GetTime = GetTime;
        }

        public void setUsedCount(int UsedCount) {
            this.UsedCount = UsedCount;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public void setParvalue(double Parvalue) {
            this.Parvalue = Parvalue;
        }

        public void setIsUserd(boolean IsUserd) {
            this.IsUserd = IsUserd;
        }

        public void setHongBaoItemId(int HongBaoItemId) {
            this.HongBaoItemId = HongBaoItemId;
        }

        public void setHongBaoId(int HongBaoId) {
            this.HongBaoId = HongBaoId;
        }

        public void setUserdMoney(double UserdMoney) {
            this.UserdMoney = UserdMoney;
        }

        public void setExpr1(int Expr1) {
            this.Expr1 = Expr1;
        }

        public void setBalance(double Balance) {
            this.Balance = Balance;
        }

        public void setExpiredTime(String ExpiredTime) {
            this.ExpiredTime = ExpiredTime;
        }

        public void setUrlId(String UrlId) {
            this.UrlId = UrlId;
        }

        public String getHongBaoName() {
            return HongBaoName;
        }

        public String getGetTime() {
            return GetTime;
        }

        public int getUsedCount() {
            return UsedCount;
        }

        public String getUserName() {
            return UserName;
        }

        public int getUserId() {
            return UserId;
        }

        public double getParvalue() {
            return Parvalue;
        }

        public boolean isIsUserd() {
            return IsUserd;
        }

        public int getHongBaoItemId() {
            return HongBaoItemId;
        }

        public int getHongBaoId() {
            return HongBaoId;
        }

        public double getUserdMoney() {
            return UserdMoney;
        }

        public int getExpr1() {
            return Expr1;
        }

        public double getBalance() {
            return Balance;
        }

        public String getExpiredTime() {
            return ExpiredTime;
        }

        public String getUrlId() {
            return UrlId;
        }
    }
}
