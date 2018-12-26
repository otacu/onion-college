package com.egoist.onioncollege.pojo.response;

import java.io.Serializable;

public class CourseItem implements Serializable {
    private Integer id;

    private Short resourceType;

    private Integer resourceId;

    private String resourceName;

    private Integer courseId;

    private Short sequence;

    private Integer userCount;

    private String resourceTypeName;

    private String isCompulsory;

    private Short state;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return resourceType
     */
    public Short getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType resourceType
     */
    public void setResourceType(Short resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return resourceId
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId resourceId
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName resourceName
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return courseId
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * @param courseId courseId
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * @return sequence
     */
    public Short getSequence() {
        return sequence;
    }

    /**
     * @param sequence sequence
     */
    public void setSequence(Short sequence) {
        this.sequence = sequence;
    }

    /**
     * @return userCount
     */
    public Integer getUserCount() {
        return userCount;
    }

    /**
     * @param userCount userCount
     */
    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    /**
     * @return resourceTypeName
     */
    public String getResourceTypeName() {
        return resourceTypeName;
    }

    /**
     * @param resourceTypeName resourceTypeName
     */
    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    /**
     * @return isCompulsory
     */
    public String getIsCompulsory() {
        return isCompulsory;
    }

    /**
     * @param isCompulsory isCompulsory
     */
    public void setIsCompulsory(String isCompulsory) {
        this.isCompulsory = isCompulsory;
    }

    /**
     * @return state
     */
    public Short getState() {
        return state;
    }

    /**
     * @param state state
     */
    public void setState(Short state) {
        this.state = state;
    }
}
