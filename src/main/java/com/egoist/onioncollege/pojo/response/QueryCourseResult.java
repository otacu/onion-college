package com.egoist.onioncollege.pojo.response;

import java.io.Serializable;
import java.util.List;

public class QueryCourseResult implements Serializable {
    private Integer answerCount;

    private Integer noticeCount;

    private List<Stage> stages;

    private Boolean signed;

    /**
     * @return answerCount
     */
    public Integer getAnswerCount() {
        return answerCount;
    }

    /**
     * @param answerCount answerCount
     */
    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    /**
     * @return noticeCount
     */
    public Integer getNoticeCount() {
        return noticeCount;
    }

    /**
     * @param noticeCount noticeCount
     */
    public void setNoticeCount(Integer noticeCount) {
        this.noticeCount = noticeCount;
    }

    /**
     * @return stages
     */
    public List<Stage> getStages() {
        return stages;
    }

    /**
     * @param stages stages
     */
    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    /**
     * @return signed
     */
    public Boolean getSigned() {
        return signed;
    }

    /**
     * @param signed signed
     */
    public void setSigned(Boolean signed) {
        this.signed = signed;
    }
}
