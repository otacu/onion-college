package com.egoist.onioncollege.pojo.response;

import java.io.Serializable;
import java.util.List;

/**
 * 课程阶段，例如某个月份
 */
public class Stage implements Serializable {
    /**
     * 状态：2进行中，3已结束
     */
    private Short state;

    private String stateName;

    private Long startTime;

    private Long endTime;

    private String courseStageName;

    private Integer courseItemsCount;

    private Short isExpand;

    private List<CourseItem> courseItems;

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
     * @return stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName stateName
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * @return startTime
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * @param startTime startTime
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
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
     * @return courseStageName
     */
    public String getCourseStageName() {
        return courseStageName;
    }

    /**
     * @param courseStageName courseStageName
     */
    public void setCourseStageName(String courseStageName) {
        this.courseStageName = courseStageName;
    }

    /**
     * @return courseItemsCount
     */
    public Integer getCourseItemsCount() {
        return courseItemsCount;
    }

    /**
     * @param courseItemsCount courseItemsCount
     */
    public void setCourseItemsCount(Integer courseItemsCount) {
        this.courseItemsCount = courseItemsCount;
    }

    /**
     * @return isExpand
     */
    public Short getIsExpand() {
        return isExpand;
    }

    /**
     * @param isExpand isExpand
     */
    public void setIsExpand(Short isExpand) {
        this.isExpand = isExpand;
    }

    /**
     * @return courseItems
     */
    public List<CourseItem> getCourseItems() {
        return courseItems;
    }

    /**
     * @param courseItems courseItems
     */
    public void setCourseItems(List<CourseItem> courseItems) {
        this.courseItems = courseItems;
    }
}
