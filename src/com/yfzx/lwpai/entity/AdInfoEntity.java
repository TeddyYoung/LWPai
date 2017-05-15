package com.yfzx.lwpai.entity;

import java.io.Serializable;


public class AdInfoEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2511549280530198057L;
	/**
     * EndDate : 2015-08-04 21:50:00
     * Target : 1
     * CategoryId : 2
     * Imgsrc : http://img.lwpai.com/Storage/ad/weixin/wxhbanner0723001.png
     * Id : 2
     */
    private String EndDate;
    private String Target;
    private String CategoryId;
    private String Imgsrc;
    private String Id;

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public void setTarget(String Target) {
        this.Target = Target;
    }

    public void setCategoryId(String CategoryId) {
        this.CategoryId = CategoryId;
    }

    public void setImgsrc(String Imgsrc) {
        this.Imgsrc = Imgsrc;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getTarget() {
        return Target;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public String getImgsrc() {
        return Imgsrc;
    }

    public String getId() {
        return Id;
    }

}
