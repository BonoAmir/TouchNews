package com.example.android.touchnews.Object;

public class News {

    private String title ;
    private String source;
    private String img;
    private String desc;
    private String time;
   private String url_link;


    public News(String title, String source, String img, String desc, String time, String url_link) {
        this.title = title;
        this.source = source;
        this.img = img;
        this.desc = desc;
        this.time = time;
        this.url_link = url_link;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getImg() {
        return img;
    }

    public String getDesc() {
        return desc;
    }

    public String getTime() {
        return time;
    }

    public  String getUrl_link() {
        return url_link;
    }
}
