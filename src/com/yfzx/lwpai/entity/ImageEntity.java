package com.yfzx.lwpai.entity;

/**
 * Created by Yang on 2015/7/16.
 */
public class ImageEntity {


    /**
     * message : 上传成功
     * result : {"ImgSrc":"1744_15ba3ee1083e487c8910d0a67d312fc4.jpg"}
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
         * ImgSrc : 1744_15ba3ee1083e487c8910d0a67d312fc4.jpg
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
