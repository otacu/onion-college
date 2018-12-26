package com.egoist.onioncollege.pojo.request;

import java.io.Serializable;

public class CourseCommentAppraiseRequest implements Serializable {
    private String orgId;

    private Short objType;
    /**
     * 点赞人
     */
    private String userId;
    /**
     * 评论ID
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
