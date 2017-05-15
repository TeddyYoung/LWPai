package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * Created by Yang on 2015/7/16.
 */
public class HelperEntity {


    /**
     * message : 成功获取帮助中心列表
     * result : [{"Name":"新手指南","CategoryId":"2","DisplaySequence":"2","Id":"0"},{"Name":"服务保障","CategoryId":"3","DisplaySequence":"3","Id":"0"},{"Name":"商品配送","CategoryId":"4","DisplaySequence":"4","Id":"0"},{"Name":"关于我们","CategoryId":"5","DisplaySequence":"5","Id":"0"},{"Name":"新手上路","CategoryId":"2","DisplaySequence":"0","Id":"1"},{"Name":"竞拍说明","CategoryId":"2","DisplaySequence":"0","Id":"2"},{"Name":"常见问题","CategoryId":"2","DisplaySequence":"0","Id":"4"},{"Name":"保障体系","CategoryId":"3","DisplaySequence":"0","Id":"5"},{"Name":"安全支付","CategoryId":"3","DisplaySequence":"0","Id":"6"},{"Name":"隐私条款","CategoryId":"3","DisplaySequence":"0","Id":"7"},{"Name":"投诉建议","CategoryId":"5","DisplaySequence":"0","Id":"9"},{"Name":"商品验收","CategoryId":"4","DisplaySequence":"0","Id":"10"},{"Name":"退换货政策","CategoryId":"4","DisplaySequence":"0","Id":"11"},{"Name":"发票制度","CategoryId":"4","DisplaySequence":"0","Id":"13"},{"Name":"了解乐网拍","CategoryId":"5","DisplaySequence":"0","Id":"14"},{"Name":"联系我们","CategoryId":"5","DisplaySequence":"0","Id":"15"},{"Name":"商品配送","CategoryId":"4","DisplaySequence":"0","Id":"16"}]
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
         * Name : 新手指南
         * CategoryId : 2
         * DisplaySequence : 2
         * Id : 0
         */
        private String Name;
        private String CategoryId;
        private String DisplaySequence;
        private String Id;

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setCategoryId(String CategoryId) {
            this.CategoryId = CategoryId;
        }

        public void setDisplaySequence(String DisplaySequence) {
            this.DisplaySequence = DisplaySequence;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public String getCategoryId() {
            return CategoryId;
        }

        public String getDisplaySequence() {
            return DisplaySequence;
        }

        public String getId() {
            return Id;
        }
    }
}
