package com.egoist.onioncollege.pojo.response;

import java.io.Serializable;

public class Subject implements Serializable {
    private Integer subjectId;

    private Integer userId;

    /**
     * @return subjectId
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId subjectId
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
