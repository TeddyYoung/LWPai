package com.yfzx.lwpai.entity;

import java.util.List;

public class TryAreaDateEntity {
	 /**
     * message : 成功获取日期记录
     * result : [{"EndTime":"2015-7-21","IsStart":"1"},{"EndTime":"2015-7-23","IsStart":"1"}]
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
         * EndTime : 2015-7-21
         * IsStart : 1
         */
        private String EndTime;
        private String IsStart;

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public void setIsStart(String IsStart) {
            this.IsStart = IsStart;
        }

        public String getEndTime() {
            return EndTime;
        }

        public String getIsStart() {
            return IsStart;
        }
    }
}
