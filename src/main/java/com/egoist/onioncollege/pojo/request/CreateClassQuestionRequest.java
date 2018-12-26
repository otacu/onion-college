package com.egoist.onioncollege.pojo.request;

import java.io.Serializable;
import java.util.List;

public class CreateClassQuestionRequest implements Serializable {
    private String title;

    private String content;

    private List<String> imgURLs;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return imgURLs
     */
    public List<String> getImgURLs() {
        return imgURLs;
    }

    /**
     * @param imgURLs imgURLs
     */
    public void setImgURLs(List<String> imgURLs) {
        this.imgURLs = imgURLs;
    }
}
