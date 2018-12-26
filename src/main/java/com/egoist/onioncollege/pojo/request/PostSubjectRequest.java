package com.egoist.onioncollege.pojo.request;

import java.io.Serializable;
import java.util.List;

public class PostSubjectRequest implements Serializable {
    private String content;

    private Short anonymousFlag;

    private String title;

    private String categoryId;

    private String attachCourses;

    private String inviteeUserIds;

    private List<String> imgURLs;

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
     * @return categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId categoryId
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return attachCourses
     */
    public String getAttachCourses() {
        return attachCourses;
    }

    /**
     * @param attachCourses attachCourses
     */
    public void setAttachCourses(String attachCourses) {
        this.attachCourses = attachCourses;
    }

    /**
     * @return inviteeUserIds
     */
    public String getInviteeUserIds() {
        return inviteeUserIds;
    }

    /**
     * @param inviteeUserIds inviteeUserIds
     */
    public void setInviteeUserIds(String inviteeUserIds) {
        this.inviteeUserIds = inviteeUserIds;
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
