package com.yfzx.lwpai.entity;

import java.util.List;

public class LastOpen {

	   /**
     * message : 成功获取最新揭晓商品
     * result : [{"MarketPrice":0,"ProductShortTitle":"懒人手机支架","WinnerName":"a5**76","CurrentCount":"24","WinUserId":"5449","UnitPrice":"1.0000","ProductId":583,"Fees":"1.0000","EndDate":"2015/6/30 21:50:00","LimitCount":"50","StartDate":"2015/6/23 22:00:00","ThumbnailUrl440":"","ThumbnailUrl100":"","PeriodIndex":"1","WinnerNum":"06-33-30-31-18-12-12","Id":"152","MaxNumPercentage":"0","ProductName":"Homee 随意扭扭乐懒人手机支架（发货颜色随机）","IsFinish":"2"}]
     * success : True
     * Total : 11
     * SSQReuslt : {"SSQ":"06-11-13-19-21-32-04","OpenDate":" 2015-06-30"}
     */
    private String message;
    private List<NewestResult> result;
    private String success;
    private int Total;
    private SSQReusltEntity SSQReuslt;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(List<NewestResult> result) {
        this.result = result;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public void setSSQReuslt(SSQReusltEntity SSQReuslt) {
        this.SSQReuslt = SSQReuslt;
    }

    public String getMessage() {
        return message;
    }

    public List<NewestResult> getResult() {
        return result;
    }

    public String getSuccess() {
        return success;
    }

    public int getTotal() {
        return Total;
    }

    public SSQReusltEntity getSSQReuslt() {
        return SSQReuslt;
    }

   
    public class SSQReusltEntity {
        /**
         * SSQ : 06-11-13-19-21-32-04
         * OpenDate :  2015-06-30
         */
        private String SSQ;
        private String OpenDate;

        public void setSSQ(String SSQ) {
            this.SSQ = SSQ;
        }

        public void setOpenDate(String OpenDate) {
            this.OpenDate = OpenDate;
        }

        public String getSSQ() {
            return SSQ;
        }

        public String getOpenDate() {
            return OpenDate;
        }
    }
}
