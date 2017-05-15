package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class IntegralEntity {


    /**
     * message : 成功获取积分记录
     * result : [{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"13579","Remark":"幸运拍返还积分","Points":"20","Reduced":"0","Increased":"1"},{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"13578","Remark":"幸运拍返还积分","Points":"19","Reduced":"0","Increased":"1"},{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"13577","Remark":"幸运拍返还积分","Points":"18","Reduced":"0","Increased":"1"},{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"13576","Remark":"幸运拍返还积分","Points":"17","Reduced":"0","Increased":"1"},{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"13574","Remark":"幸运拍返还积分","Points":"16","Reduced":"0","Increased":"1"},{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"13572","Remark":"幸运拍返还积分","Points":"15","Reduced":"0","Increased":"1"},{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"13114","Remark":"幸运拍结束返还积分","Points":"14","Reduced":"0","Increased":"1"},{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"12435","Remark":"幸运拍结束返还积分","Points":"13","Reduced":"0","Increased":"1"},{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"12402","Remark":"幸运拍结束返还积分","Points":"12","Reduced":"0","Increased":"1"},{"TradeDate":"","TradeType":"幸运拍","JournalNumber":"12371","Remark":"幸运拍结束返还积分","Points":"11","Reduced":"0","Increased":"1"}]
     * success : True
     * Total : 30
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
         * TradeDate :
         * TradeType : 幸运拍
         * JournalNumber : 13579
         * Remark : 幸运拍返还积分
         * Points : 20
         * Reduced : 0
         * Increased : 1
         */
        private String TradeDate;
        private String TradeType;
        private String JournalNumber;
        private String Remark;
        private String Points;
        private String Reduced;
        private String Increased;

        public void setTradeDate(String TradeDate) {
            this.TradeDate = TradeDate;
        }

        public void setTradeType(String TradeType) {
            this.TradeType = TradeType;
        }

        public void setJournalNumber(String JournalNumber) {
            this.JournalNumber = JournalNumber;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public void setPoints(String Points) {
            this.Points = Points;
        }

        public void setReduced(String Reduced) {
            this.Reduced = Reduced;
        }

        public void setIncreased(String Increased) {
            this.Increased = Increased;
        }

        public String getTradeDate() {
            return TradeDate;
        }

        public String getTradeType() {
            return TradeType;
        }

        public String getJournalNumber() {
            return JournalNumber;
        }

        public String getRemark() {
            return Remark;
        }

        public String getPoints() {
            return Points;
        }

        public String getReduced() {
            return Reduced;
        }

        public String getIncreased() {
            return Increased;
        }
    }
}
