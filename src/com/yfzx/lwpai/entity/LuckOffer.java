package com.yfzx.lwpai.entity;

import java.util.List;

public class LuckOffer {
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
         * LuckyIp : 124.72.71.114
         * LuckyCity : 厦门市
         * OfferNum : 9
         * UserName : ws**yh
         * OfferTime : 2015/4/19 20:29:18
         * FaceImage : http://www.lwpai.com/utility/pics/tx.gif
         * LuckyProvince : 福建省
         */
        private String LuckyIp;
        private String LuckyCity;
        private String OfferNum;
        private String UserName;
        private String OfferTime;
        private String FaceImage;
        private String LuckyProvince;

        public void setLuckyIp(String LuckyIp) {
            this.LuckyIp = LuckyIp;
        }

        public void setLuckyCity(String LuckyCity) {
            this.LuckyCity = LuckyCity;
        }

        public void setOfferNum(String OfferNum) {
            this.OfferNum = OfferNum;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setOfferTime(String OfferTime) {
            this.OfferTime = OfferTime;
        }

        public void setFaceImage(String FaceImage) {
            this.FaceImage = FaceImage;
        }

        public void setLuckyProvince(String LuckyProvince) {
            this.LuckyProvince = LuckyProvince;
        }

        public String getLuckyIp() {
            return LuckyIp;
        }

        public String getLuckyCity() {
            return LuckyCity;
        }

        public String getOfferNum() {
            return OfferNum;
        }

        public String getUserName() {
            return UserName;
        }

        public String getOfferTime() {
            return OfferTime;
        }

        public String getFaceImage() {
            return FaceImage;
        }

        public String getLuckyProvince() {
            return LuckyProvince;
        }
    }
}
