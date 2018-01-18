package com.callor.lession.todolist;

/**
 * Created by callor on 2018-01-18.
 */

public class MemoVO {

    private String strDate ;
    private String strTime ;
    private String strMemo ;


    public MemoVO() {

    }

    public MemoVO(String strDate, String strTime, String strMemo) {
        this.strDate = strDate;
        this.strTime = strTime;
        this.strMemo = strMemo;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getStrMemo() {
        return strMemo;
    }

    public void setStrMemo(String strMemo) {
        this.strMemo = strMemo;
    }
}
