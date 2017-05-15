package com.yfzx.lwpai.entity;

import java.util.List;

public class LuckyGetLuckyProduct {

    /**
     * PT : [{"Id":"22","PeriodName":"最新期"},{"Id":"8","PeriodName":"第2期"},{"Id":"1","PeriodName":"第1期"}]
     * message : 成功获取该商品信息
     * result : [{"MarketPrice":4999,"LotteryResults":"","WinnerName":"cl**lj","CurrentCount":"164","UnitPrice":"1.0000","ProductId":302,"Fees":"1.0000","EndDate":"2015/4/19 21:50:00","LimitCount":"4999","StartDate":"2015/4/11 8:35:50","PeriodIndex":"1","WinnerNum":"02-03-33-12-28-14-07","Id":"1","MaxNumPercentage":"3000","ProductName":"0元拍iPhone6","OrderShareId":"4","IsFinish":"2"}]
     * PImgSrc : [{"ImgSrc":"http://img.lwpai.com/Storage/master/product/images/052c33629307418bb9ff9bb45804a334.jpg"},{"ImgSrc":"http://img.lwpai.com/Storage/master/product/images/62e4d4243d984303995d9284a759644e.jpg"},{"ImgSrc":"http://img.lwpai.com/Storage/master/product/images/8011af9dac674b128b87003bc6b50aac.jpg"},{"ImgSrc":"http://img.lwpai.com/Storage/master/product/images/52df574019964e749fc50eea38ed16ee.jpg"},{"ImgSrc":"http://img.lwpai.com/Storage/master/product/images/9d50886400fc4f988ecc66c594b9ad3f.jpg"}]
     * success : True
     */
    private List<PTEntity> PT;
    private String message;
    private List<ResultEntity> result;
    private List<PImgSrcEntity> PImgSrc;
    private String success;

    public void setPT(List<PTEntity> PT) {
        this.PT = PT;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public void setPImgSrc(List<PImgSrcEntity> PImgSrc) {
        this.PImgSrc = PImgSrc;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<PTEntity> getPT() {
        return PT;
    }

    public String getMessage() {
        return message;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public List<PImgSrcEntity> getPImgSrc() {
        return PImgSrc;
    }

    public String getSuccess() {
        return success;
    }

    public class PTEntity {
        /**
         * Id : 22
         * PeriodName : 最新期
         */
        private String Id;
        private String PeriodName;

        public void setId(String Id) {
            this.Id = Id;
        }

        public void setPeriodName(String PeriodName) {
            this.PeriodName = PeriodName;
        }

        public String getId() {
            return Id;
        }

        public String getPeriodName() {
            return PeriodName;
        }
    }

    public class ResultEntity {
        /**
         * MarketPrice : 4999
         * LotteryResults :
         * WinnerName : cl**lj
         * CurrentCount : 164
         * UnitPrice : 1.0000
         * ProductId : 302
         * Fees : 1.0000
         * EndDate : 2015/4/19 21:50:00
         * LimitCount : 4999
         * StartDate : 2015/4/11 8:35:50
         * PeriodIndex : 1
         * WinnerNum : 02-03-33-12-28-14-07
         * Id : 1
         * MaxNumPercentage : 3000
         * ProductName : 0元拍iPhone6
         * OrderShareId : 4
         * IsFinish : 2
         */
        private int MarketPrice;
        private String LotteryResults;
        private String WinnerName;
        private String CurrentCount;
        private String UnitPrice;
        private int ProductId;
        private String Fees;
        private String EndDate;
        private String LimitCount;
        private String StartDate;
        private String PeriodIndex;
        private String WinnerNum;
        private String Id;
        private String MaxNumPercentage;
        private String ProductName;
        private String OrderShareId;
        private String IsFinish;

        public void setMarketPrice(int MarketPrice) {
            this.MarketPrice = MarketPrice;
        }

        public void setLotteryResults(String LotteryResults) {
            this.LotteryResults = LotteryResults;
        }

        public void setWinnerName(String WinnerName) {
            this.WinnerName = WinnerName;
        }

        public void setCurrentCount(String CurrentCount) {
            this.CurrentCount = CurrentCount;
        }

        public void setUnitPrice(String UnitPrice) {
            this.UnitPrice = UnitPrice;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public void setFees(String Fees) {
            this.Fees = Fees;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public void setLimitCount(String LimitCount) {
            this.LimitCount = LimitCount;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public void setPeriodIndex(String PeriodIndex) {
            this.PeriodIndex = PeriodIndex;
        }

        public void setWinnerNum(String WinnerNum) {
            this.WinnerNum = WinnerNum;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public void setMaxNumPercentage(String MaxNumPercentage) {
            this.MaxNumPercentage = MaxNumPercentage;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public void setOrderShareId(String OrderShareId) {
            this.OrderShareId = OrderShareId;
        }

        public void setIsFinish(String IsFinish) {
            this.IsFinish = IsFinish;
        }

        public int getMarketPrice() {
            return MarketPrice;
        }

        public String getLotteryResults() {
            return LotteryResults;
        }

        public String getWinnerName() {
            return WinnerName;
        }

        public String getCurrentCount() {
            return CurrentCount;
        }

        public String getUnitPrice() {
            return UnitPrice;
        }

        public int getProductId() {
            return ProductId;
        }

        public String getFees() {
            return Fees;
        }

        public String getEndDate() {
            return EndDate;
        }

        public String getLimitCount() {
            return LimitCount;
        }

        public String getStartDate() {
            return StartDate;
        }

        public String getPeriodIndex() {
            return PeriodIndex;
        }

        public String getWinnerNum() {
            return WinnerNum;
        }

        public String getId() {
            return Id;
        }

        public String getMaxNumPercentage() {
            return MaxNumPercentage;
        }

        public String getProductName() {
            return ProductName;
        }

        public String getOrderShareId() {
            return OrderShareId;
        }

        public String getIsFinish() {
            return IsFinish;
        }
    }

    public class PImgSrcEntity {
        /**
         * ImgSrc : http://img.lwpai.com/Storage/master/product/images/052c33629307418bb9ff9bb45804a334.jpg
         */
        private String ImgSrc;

        public void setImgSrc(String ImgSrc) {
            this.ImgSrc = ImgSrc;
        }

        public String getImgSrc() {
            return ImgSrc;
        }
    }
}
