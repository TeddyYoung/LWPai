package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 首页头部广告
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-3
 */
public class HeadAdList {
	 /**
     * message : 成功获取
     * result : {"AdInfo":[{"EndDate":"2015-08-04 21:50:00","Target":"1","CategoryId":"2","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0723001.png","Id":"2"},{"EndDate":"","Target":"5","CategoryId":"3","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0723002.png","Id":"0"},{"EndDate":"","Target":"2","CategoryId":"4","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0723003.png","Id":"325"},{"EndDate":"","Target":"1","CategoryId":"1","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0504001.jpg","Id":"2"},{"EndDate":"","Target":"10","CategoryId":"1","Imgsrc":"http://img.lwpai.com/Storage/ad/wxhbanner15070702.jpg","Id":"0"},{"EndDate":"","Target":"8","CategoryId":"1","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0504002.jpg","Id":"0"}]}
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
         * AdInfo : [{"EndDate":"2015-08-04 21:50:00","Target":"1","CategoryId":"2","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0723001.png","Id":"2"},{"EndDate":"","Target":"5","CategoryId":"3","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0723002.png","Id":"0"},{"EndDate":"","Target":"2","CategoryId":"4","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0723003.png","Id":"325"},{"EndDate":"","Target":"1","CategoryId":"1","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0504001.jpg","Id":"2"},{"EndDate":"","Target":"10","CategoryId":"1","Imgsrc":"http://img.lwpai.com/Storage/ad/wxhbanner15070702.jpg","Id":"0"},{"EndDate":"","Target":"8","CategoryId":"1","Imgsrc":"http://img.lwpai.com/Storage/ad/weixin/wxhbanner0504002.jpg","Id":"0"}]
         */
        private List<AdInfoEntity> AdInfo;

        public void setAdInfo(List<AdInfoEntity> AdInfo) {
            this.AdInfo = AdInfo;
        }

        public List<AdInfoEntity> getAdInfo() {
            return AdInfo;
        }
    }

}
