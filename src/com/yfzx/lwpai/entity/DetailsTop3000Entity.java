package com.yfzx.lwpai.entity;

import java.io.Serializable;
import java.util.List;

/**
 * top3000
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月23日
 */
public class DetailsTop3000Entity {

    /**
     * message : 成功获取信息
     * result : [{"LuckyIp":"125.79.55.75","LuckyCity":"漳州市","LotteryNumbers":"2,3,33,12,28,14,7","UserName":"cl**lj","OfferTime":"2015/4/17 10:02:32","WinnerRanking":"1","FaceImage":"http://www.lwpai.com/utility/pics/tx.gif","LuckyProvince":"福建省","RemarkName":""}]
     * success : True
     * Total : 3000
     */
    private String message;
    private List<DetailsTop3000ResultEntity> result;
    private String success;
    private int Total;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(List<DetailsTop3000ResultEntity> result) {
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

    public List<DetailsTop3000ResultEntity> getResult() {
        return result;
    }

    public String getSuccess() {
        return success;
    }

    public int getTotal() {
        return Total;
    }
}
