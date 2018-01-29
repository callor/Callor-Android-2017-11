package com.callor.lession.naverfrend.data;

import java.util.List;

/**
 * Created by callor on 2018-01-29.
 */

public class BookVO {

    private String lastBuilData ;
    private int total ;
    private int start;
    private int display;
    private List<BookItemVO> items ;


    public String getLastBuilData() {
        return lastBuilData;
    }

    public void setLastBuilData(String lastBuilData) {
        this.lastBuilData = lastBuilData;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public List<BookItemVO> getItems() {
        return items;
    }

    public void setItems(List<BookItemVO> items) {
        this.items = items;
    }

    public static class BookItemVO {

        private String title;
        private String link;
        private String image ;
        private String author;
        private String price ;
        private String discount;
        private String publisher;
        private String isbn;
        private String desciption;

        // 임의의 생성자를 만들면 빈 생성자도 반드시 만들어야 한다.
        public BookItemVO() {
        }

        public BookItemVO(String title, String link, String image, String author, String price) {
            this.title = title;
            this.link = link;
            this.image = image;
            this.author = author;
            this.price = price;
        }

        public BookItemVO(String title, String link, String image, String author, String price, String discount, String publisher, String isbn, String desciption) {
            this.title = title;
            this.link = link;
            this.image = image;
            this.author = author;
            this.price = price;
            this.discount = discount;
            this.publisher = publisher;
            this.isbn = isbn;
            this.desciption = desciption;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getDesciption() {
            return desciption;
        }

        public void setDesciption(String desciption) {
            this.desciption = desciption;
        }
    }

}
