package com.callor.lession.myapp016;

/**
 * Created by callor on 2018-01-02.
 *
 * RecyclerView에 표시할 1개의 아이템을 담을 VO 객체
 *
 *
 */

// Alt+insert >> getter and setter 선택
public class ItemVO {

    private int imageName ;
    private String imageText ;


    public ItemVO(int imageName, String imageText) {
        this.imageName = imageName;
        this.imageText = imageText;
    }

    public int getImageName() {
        return imageName;
    }

    public void setImageName(int imageName) {
        this.imageName = imageName;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }
}
