package com.egoist.onioncollege.pojo.request;

import java.io.Serializable;

public class PublishCourseCommentRequest implements Serializable {
    private String orgId;

    private Short objType;

    private String remark;

    /**
     * 如果是评论别人的评论，就是别人评论的id
     */
    private String parentId;

    private String userId;

    /**
     * 课程id
     */
    private String objId;

    /**
     * @return orgId
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * @return objType
     */
    public Short getObjType() {
        return objType;
    }

    /**
     * @param objType objType
     */
    public void setObjType(Short objType) {
        this.objType = objType;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
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
     * @return objId
     */
    public String getObjId() {
        return objId;
    }

    /**
     * @param objId objId
     */
    public void setObjId(String objId) {
        this.objId = objId;
    }
}
