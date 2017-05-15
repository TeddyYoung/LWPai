package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 商品一元拍实体
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
public class GoodsOneYuanEntity {
	 /**
     * message : 成功获取商品信息
     * result : [{"Percentage":49.28,"SalePrice":556,"OnePurchasePTId":0,"OnePurchasePId":53,"CurrentPurchaseNum":274,"PeriodIndex":1,"ThumbnailUrl440":"http://img.lwpai.com/Storage/master/product/thumbs440/440_e8e92c9bba134d6e911520fd1629c475.jpg","ThumbnailUrl100":"http://img.lwpai.com/Storage/master/product/thumbs100/100_e8e92c9bba134d6e911520fd1629c475.jpg","ProductName":"虎牌儿童型真空杯","OnePurchaseNum":556,"ProductId":480,"LastPurchaseNum":282}]
     * success : True
     * Total : 52
     */
    private String message;
    private List<GoodsOneYuanResult> result;
    private String success;
    private int Total;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(List<GoodsOneYuanResult> result) {
        this.result = result;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public String getMessage() {
        return message;
    }

    public List<GoodsOneYuanResult> getResult() {
        return result;
    }

    public String getSuccess() {
        return success;
    }

    public int getTotal() {
        return Total;
    }

}
