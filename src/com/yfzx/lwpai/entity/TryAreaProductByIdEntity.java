package com.yfzx.lwpai.entity;

import java.util.List;

public class TryAreaProductByIdEntity {
	 /**
     * message : 成功获取该商品信息
     * result : [{"MarketPrice":79,"LotteryResults":"9,10,19,21,23,32,8","WinnerName":"186****29","CurrentCount":"77","WinUserId":"1737","UnitPrice":"1.0000","ProductId":323,"Fees":"0.0000","EndDate":"2015/4/14 21:50:00","LimitCount":"0","SalePrice":0,"StartDate":"2015/4/11 9:00:00","PeriodIndex":"1","WinnerNum":"28-16-19-29-17-10-09","ProductName":"小米（MI） 移动电源 原厂正品10400mAh毫安铝合金外壳 银色","Id":"2","MaxNumPercentage":"0","OrderShareId":"13","IsFinish":"2"}]
     * PImgSrc : [{"ImgSrc":"http://img.lwpai.com/Storage/master/product/images/86cbf5d0c4a0445a9a638436abeedbef.jpg"},{"ImgSrc":"http://img.lwpai.com/Storage/master/product/images/a0fb5dbb31524f599c7f3278310477df.jpg"},{"ImgSrc":"http://img.lwpai.com/Storage/master/product/images/cccc9157d2b04d96a048b093f6b30f95.jpg"}]
     * success : True
     */
    private String message;
    private List<TryAreaProductByIdResult> result;
    private List<AdInfoEntity> PImgSrc;
    private String success;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(List<TryAreaProductByIdResult> result) {
        this.result = result;
    }

    public void setPImgSrc(List<AdInfoEntity> PImgSrc) {
        this.PImgSrc = PImgSrc;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public List<TryAreaProductByIdResult> getResult() {
        return result;
    }

    public List<AdInfoEntity> getPImgSrc() {
        return PImgSrc;
    }

    public String getSuccess() {
        return success;
    }
	
}
