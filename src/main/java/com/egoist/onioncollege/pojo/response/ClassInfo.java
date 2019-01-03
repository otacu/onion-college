package com.egoist.onioncollege.pojo.response;

import java.io.Serializable;

public class ClassInfo implements Serializable {
    private Integer id;
    private Integer classId;
    private String className;
    private Integer schemeId;
    private String schemeName;
    private String description;
    private String address;
    private String imageUrl;
    private Long beginTime;
    private Long endTime;
    private Short state;
    private Integer joinUserCount;
    private Short joinState;
    private Short classType;
    private String qCodeUrl;
    private Integer userCounts;

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
     * @return classId
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * @param classId classId
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * @return className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return schemeId
     */
    public Integer getSchemeId() {
        return schemeId;
    }

    /**
     * @param schemeId schemeId
     */
    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    /**
     * @return schemeName
     */
    public String getSchemeName() {
        return schemeName;
    }

    /**
     * @param schemeName schemeName
     */
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return beginTime
     */
    public Long getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime beginTime
     */
    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return endTime
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * @param endTime endTime
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
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

    /**
     * @return joinUserCount
     */
    public Integer getJoinUserCount() {
        return joinUserCount;
    }

    /**
     * @param joinUserCount joinUserCount
     */
    public void setJoinUserCount(Integer joinUserCount) {
        this.joinUserCount = joinUserCount;
    }

    /**
     * @return joinState
     */
    public Short getJoinState() {
        return joinState;
    }

    /**
     * @param joinState joinState
     */
    public void setJoinState(Short joinState) {
        this.joinState = joinState;
    }

    /**
     * @return classType
     */
    public Short getClassType() {
        return classType;
    }

    /**
     * @param classType classType
     */
    public void setClassType(Short classType) {
        this.classType = classType;
    }

    /**
     * @return qCodeUrl
     */
    public String getqCodeUrl() {
        return qCodeUrl;
    }

    /**
     * @param qCodeUrl qCodeUrl
     */
    public void setqCodeUrl(String qCodeUrl) {
        this.qCodeUrl = qCodeUrl;
    }

    /**
     * @return userCounts
     */
    public Integer getUserCounts() {
        return userCounts;
    }

    /**
     * @param userCounts userCounts
     */
    public void setUserCounts(Integer userCounts) {
        this.userCounts = userCounts;
    }
}
