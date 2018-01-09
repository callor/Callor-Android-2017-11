package com.callor.lession.myapp018;

/**
 * Created by callor on 2018-01-09.
 */

public class PhoneVO {

    private String strName;
    private String strPhone;
    private String strGroup;

    public PhoneVO() {
    }

    public PhoneVO(String strName, String strPhone, String strGroup) {
        this.strName = strName;
        this.strPhone = strPhone;
        this.strGroup = strGroup;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrPhone() {
        return strPhone;
    }

    public void setStrPhone(String strPhone) {
        this.strPhone = strPhone;
    }

    public String getStrGroup() {
        return strGroup;
    }

    public void setStrGroup(String strGroup) {
        this.strGroup = strGroup;
    }
}
