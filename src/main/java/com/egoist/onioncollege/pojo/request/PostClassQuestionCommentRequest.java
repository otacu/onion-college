package com.egoist.onioncollege.pojo.request;

import java.io.Serializable;

public class PostClassQuestionCommentRequest implements Serializable {
    private String comment;

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
