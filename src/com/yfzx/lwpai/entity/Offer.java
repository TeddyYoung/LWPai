package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/6/30.
 */
public class Offer {


    /**
     * message : 成功获取乐拍记录
     * result : [{"LuckyOfferNum":"0","TryAreaOfferNum":"0","QWOfferNum":"0","OneOfferNum":"0"}]
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
         * LuckyOfferNum : 0
         * TryAreaOfferNum : 0
         * QWOfferNum : 0
         * OneOfferNum : 0
         */
        private String LuckyOfferNum;
        private String TryAreaOfferNum;
        private String QWOfferNum;
        private String OneOfferNum;

        public void setLuckyOfferNum(String LuckyOfferNum) {
            this.LuckyOfferNum = LuckyOfferNum;
        }

        public void setTryAreaOfferNum(String TryAreaOfferNum) {
            this.TryAreaOfferNum = TryAreaOfferNum;
        }

        public void setQWOfferNum(String QWOfferNum) {
            this.QWOfferNum = QWOfferNum;
        }

        public void setOneOfferNum(String OneOfferNum) {
            this.OneOfferNum = OneOfferNum;
        }

        public String getLuckyOfferNum() {
            return LuckyOfferNum;
        }

        public String getTryAreaOfferNum() {
            return TryAreaOfferNum;
        }

        public String getQWOfferNum() {
            return QWOfferNum;
        }

        public String getOneOfferNum() {
            return OneOfferNum;
        }
    }
}
