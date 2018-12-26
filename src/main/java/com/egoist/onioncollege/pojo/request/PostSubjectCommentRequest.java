package com.egoist.onioncollege.pojo.request;

import java.io.Serializable;
import java.util.List;

public class PostSubjectCommentRequest implements Serializable {
    private String voiceURL;

    private String content;

    private String userId;

    private List<String> imgURLs;

    private Short anonymousFlag;

    /**
     * @return voiceURL
     */
    public String getVoiceURL() {
        return voiceURL;
    }

    /**
     * @param voiceURL voiceURL
     */
    public void setVoiceURL(String voiceURL) {
        this.voiceURL = voiceURL;
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
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
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

    /**
     * @return anonymousFlag
     */
    public Short getAnonymousFlag() {
        return anonymousFlag;
    }

    /**
     * @param anonymousFlag anonymousFlag
     */
    public void setAnonymousFlag(Short anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }
}
