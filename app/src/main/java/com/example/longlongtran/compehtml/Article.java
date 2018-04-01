package com.example.longlongtran.compehtml;

import java.io.Serializable;

class Article implements Serializable {
    private String title;
    private String thumnail;
    private String url;
    private String decription;

    public Article() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}