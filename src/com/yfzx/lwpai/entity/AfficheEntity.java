package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class AfficheEntity {

    /**
     * message : 成功获取公告信息
     * result : [{"Title":"关于刷小号屡禁不止行为处理声明","AddedDate":"2015/6/10 10:00:10","AfficheId":"55"},{"Title":"第一批小号封停名单公告","AddedDate":"2015/7/15 15:58:20","AfficheId":"62"},{"Title":"近期关于会员恶意作弊处理公告","AddedDate":"2015/7/15 15:38:48","AfficheId":"61"}]
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
         * Title : 关于刷小号屡禁不止行为处理声明
         * AddedDate : 2015/6/10 10:00:10
         * AfficheId : 55
         */
        private String Title;
        private String AddedDate;
        private String AfficheId;

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public void setAddedDate(String AddedDate) {
            this.AddedDate = AddedDate;
        }

        public void setAfficheId(String AfficheId) {
            this.AfficheId = AfficheId;
        }

        public String getTitle() {
            return Title;
        }

        public String getAddedDate() {
            return AddedDate;
        }

        public String getAfficheId() {
            return AfficheId;
        }
    }
}
