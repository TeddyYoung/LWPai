package com.yfzx.lwpai.entity;

/**
 * Created by Yang on 2015/7/16.
 */
public class TimeEntity {


    /**
     * message : 成功获取系统时间
     * result : {"Time":"2015-07-24 09:34:21"}
     * success : True
     */
    private String message;
    private ResultEntity result;
    private String success;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public ResultEntity getResult() {
        return result;
    }

    public String getSuccess() {
        return success;
    }

    public class ResultEntity {
        /**
         * Time : 2015-07-24 09:34:21
         */
        private String Time;

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getTime() {
            return Time;
        }
    }
}
