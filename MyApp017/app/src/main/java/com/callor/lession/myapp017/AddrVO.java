package com.callor.lession.myapp017;

/**
 * Created by callor on 2018-01-09.
 */

public class AddrVO {
    private int intImage = 0 ;
    private String strName = "";
    private String strRemark = "" ;

    public AddrVO(){

    }

    public AddrVO(int intImage, String strName, String strRemark) {
        this.intImage = intImage;
        this.strName = strName;
        this.strRemark = strRemark;
    }


    public int getIntImage() {
        return intImage;
    }

    public void setIntImage(int intImage) {
        this.intImage = intImage;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrRemark() {
        return strRemark;
    }

    public void setStrRemark(String strRemark) {
        this.strRemark = strRemark;
    }
}
