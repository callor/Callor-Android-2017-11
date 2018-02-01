package com.callor.lession.navermovie.naver;

/**
 * Created by callor on 2018-02-01.
 */

public class Naver_Items {

    public static String RSS = "rss";	//-	디버그를 쉽게 하고 RSS 리더기만으로 이용할 수 있게 하기 위해 만든 RSS 포맷의 컨테이너이며 그 외의 특별한 의미는 없다.
    public static String CHANNEL = "channel"; // 	-	검색 결과를 포함하는 컨테이너이다. 이 안에 있는 title, link, description 등의 항목은 참고용으로 무시해도 무방하다.
    public static String LASTBUILDDATE  = "lastBuildDate" ; // lastBuildDate	datetime	검색 결과를 생성한 시간이다.
    public static String ITEM = "items"; //item

    public static class ITEMS {

        public static String TITLE = "title" ; //	    string	검색 결과 영화의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
        public static String LINK	= "link"; //        string	검색 결과 영화의 하이퍼텍스트 link를 나타낸다.
        public static String IMAGE = "image"; //	    string	검색 결과 영화의 썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타난다.
        public static String SUBTITLE = "subtitle"; //	    string	검색 결과 영화의 영문 제목이다.
        public static String PUBDATE = "pubDate"; // 	    date	검색 결과 영화의 제작년도이다.
        public static String DIRECTOR = "director"; //	    string	검색 결과 영화의 감독이다.
        public static String ACTOR = "actor";// 	    string	검색 결과 영화의 출연 배우이다.
        public static String USERRATING = "userRating" ; //	integer	검색 결과 영화에 대한 유저들의 평점이다.

    }

}
