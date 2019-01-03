package com.egoist.onioncollege.constants;

public final class OnionCollegeConstants {
    private OnionCollegeConstants (){

    }

    public static final String POST_SUBJECT_URL = "https://study.study2win.net/front/discussGroup/postSubject?disGrpId=2063&userId=%s";

    public static final String POST_SUBJECT_COMMENT_URL = "https://study.study2win.net/front/discussSubject/postComment?subjectId=%s&targetId=0";

    public static final String CREATE_CLASS_QUESTION_URL = "https://study.study2win.net/front/courseitem/createclassquestion?classId=%s";

    public static final String PUBLISH_COURSE_COMMENT_URL = "http://study.study2win.net/front/basicCourse/publishComment.action";

    public static final String QUERY_TRAINING_CLASS_INFO_URL = "https://study.study2win.net/front/classes/queryTrainingClassInfo?userId=%s&classId=%s&t=%s";

    public static final String COURSE_COMMENT_APPRAISE_URL = "http://study.study2win.net/front/basicCourse/userAppraise.action";

    public static final String LOGIN_URL = "https://study.study2win.net/front/login/unp";

    public static final String POST_CLASS_QUESTION_COMMENT_URL = "https://study.study2win.net/front/courseitem/postCommentToSubject?subjectId=%s&targetCommendId=0";

    public static final String MY_CLASS_LIST_URL = "https://study.study2win.net/front/classDefine/myClassList?userId=%s&orgId=%s&page=1&t=%s";

    /**
     * 1为匿名，0为显示名字
     */
    public static final Short ANONYMOUS_FLAG = 1;

    /**
     * 12月新员工培训
     */
    public static final Integer CLASS_ID_1 = 6958;

    /**
     * 职场通
     */
    public static final Integer CLASS_ID_2 = 10284;

    /**
     * 公司id
     */
    public static final String ORG_ID = "6245";

    /**
     * 登录参数
     */
    public static final String SVC_CODE = "client:009";

    /**
     * 登录参数
     */
    public static final String ORD_CODE = "lks";

}
