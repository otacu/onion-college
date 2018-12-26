package com.egoist.onioncollege.service;

import com.alibaba.fastjson.JSONObject;
import com.egoist.onioncollege.config.OnionCollegeConfig;
import com.egoist.onioncollege.constants.OnionCollegeConstants;
import com.egoist.onioncollege.enums.SubjectCommentEnum;
import com.egoist.onioncollege.pojo.request.*;
import com.egoist.onioncollege.pojo.response.QueryCourseResult;
import com.egoist.onioncollege.pojo.response.Stage;
import com.egoist.parent.common.constants.EgoistExceptionStatusConstant;
import com.egoist.parent.common.utils.http.EgoistOkHttp3Util;
import com.egoist.parent.common.utils.json.EgoistJsonUtil;
import com.egoist.parent.pojo.dto.EgoistResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class OnionCollegeService {
    @Autowired
    private OnionCollegeConfig onionCollegeConfig;

    private String userId;

    private String authToken;

    private String accessToken;

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
     * @return authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken authToken
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * @return accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 登录
     *
     * @return
     */
    public EgoistResult login() {
        try {
            String url = OnionCollegeConstants.LOGIN_URL;
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            LoginRequest request = new LoginRequest();
            request.setSvcCode(OnionCollegeConstants.SVC_CODE);
            request.setUsername(onionCollegeConfig.getUSERNAME());
            request.setPassword(onionCollegeConfig.getPASSWORD());
            request.setOrgCode(OnionCollegeConstants.ORD_CODE);
            JSONObject returnObject = EgoistOkHttp3Util.postHeaderBody(url, header, EgoistJsonUtil.objectToJson(request));
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            this.setAuthToken(returnObject.getJSONObject("result").getString("authToken"));
            this.setAccessToken(returnObject.getJSONObject("result").getString("accessToken"));
            this.setUserId(returnObject.getJSONObject("result").getString("userId"));
            return EgoistResult.ok();
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "登录失败：" + e.toString(), null);
        }
    }

    /**
     * 发表话题
     *
     * @param content 内容
     * @return subjectId 话题id
     */
    public EgoistResult postSubject(String content) {
        try {
            String url = String.format(OnionCollegeConstants.POST_SUBJECT_URL, this.getUserId());
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("X-Auth-Token", this.getAuthToken());
            PostSubjectRequest request = new PostSubjectRequest();
            request.setContent(content);
            request.setAnonymousFlag(OnionCollegeConstants.ANONYMOUS_FLAG);
            request.setTitle("");
            request.setCategoryId("2063");
            request.setImgURLs(new ArrayList<>());
            JSONObject returnObject = EgoistOkHttp3Util.postHeaderBody(url, header, EgoistJsonUtil.objectToJson(request));
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            Integer subjectId = (Integer) returnObject.getJSONObject("result").get("subjectId");
            return EgoistResult.ok(subjectId);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "发布话题失败：" + e.toString(), null);
        }
    }

    /**
     * 发表话题评论
     *
     * @param subjectId 话题id
     * @param content   内容
     * @return
     */
    public EgoistResult postSubjectComment(Integer subjectId, String content) {
        try {
            String url = String.format(OnionCollegeConstants.POST_SUBJECT_COMMENT_URL, subjectId);
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("X-Auth-Token", this.getAuthToken());
            PostSubjectCommentRequest request = new PostSubjectCommentRequest();
            request.setVoiceURL("");
            request.setContent(content);
            request.setUserId(this.getUserId());
            request.setImgURLs(new ArrayList<>());
            request.setAnonymousFlag(OnionCollegeConstants.ANONYMOUS_FLAG);
            JSONObject returnObject = EgoistOkHttp3Util.postHeaderBody(url, header, EgoistJsonUtil.objectToJson(request));
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            return EgoistResult.ok();
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "评论话题失败：" + e.toString(), null);
        }
    }

    /**
     * 发表话题和评论
     *
     * @return
     */
    public EgoistResult postSubjectAndComment() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            for (int i = 0; i < 2; i++) {
                int code = dayOfWeek * i;
                EgoistResult postSubjectResult = this.postSubject(SubjectCommentEnum.getContent(code));
                if (!EgoistResult.isOk(postSubjectResult)) {
                    log.error(postSubjectResult.getMsg());
                    continue;
                }
                Integer subjectId = (Integer) postSubjectResult.getData();
                EgoistResult postCommentResult = this.postSubjectComment(subjectId, SubjectCommentEnum.getReference(code));
                if (!EgoistResult.isOk(postCommentResult)) {
                    log.error(postCommentResult.getMsg());
                }
            }
            return EgoistResult.ok();
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "发表话题和评论异常：" + e.toString(), null);
        }
    }

    /**
     * 发布问答
     *
     * @param title   标题
     * @param content 内容
     * @return
     */
    public EgoistResult createClassQuestion(String title, String content) {
        try {
            String url = String.format(OnionCollegeConstants.CREATE_CLASS_QUESTION_URL, OnionCollegeConstants.CLASS_ID_1);
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("X-Auth-Token", this.getAuthToken());
            CreateClassQuestionRequest request = new CreateClassQuestionRequest();
            request.setTitle(title);
            request.setContent(content);
            JSONObject returnObject = EgoistOkHttp3Util.postHeaderBody(url, header, EgoistJsonUtil.objectToJson(request));
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            return EgoistResult.ok();
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "发布问答失败：" + e.toString(), null);
        }
    }

    /**
     * 查询班级课程列表
     *
     * @return
     */
    public EgoistResult queryTrainingClassInfo() {
        try {
            String url = String.format(OnionCollegeConstants.QUERY_TRAINING_CLASS_INFO_URL, this.getUserId(),
                    OnionCollegeConstants.CLASS_ID_2, new Date().getTime());
            Map<String, String> header = new HashMap<>();
            header.put("X-Access-Token", this.getAccessToken());
            JSONObject returnObject = EgoistOkHttp3Util.get(url, header);
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            QueryCourseResult result = EgoistJsonUtil.jsonToPojo(EgoistJsonUtil.objectToJson(returnObject.get("result")), QueryCourseResult.class);
            return EgoistResult.ok(result);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "查询班级课程失败：" + e.toString(), null);
        }
    }

    /**
     * 获取最新一个月的课程列表
     *
     * @return
     */
    public EgoistResult getCourseIdOfLastistStage() {
        try {
            EgoistResult queryResult = this.queryTrainingClassInfo();
            if (!EgoistResult.isOk(queryResult)) {
                return queryResult;
            }
            QueryCourseResult queryCourseResult = (QueryCourseResult) queryResult.getData();
            List<Stage> stages = queryCourseResult.getStages();
            Stage lastistStage = stages.get(stages.size() - 1);
            List<Integer> courseIdList = new ArrayList<>();
            lastistStage.getCourseItems().forEach(courseItem -> {
                courseIdList.add(courseItem.getResourceId());
            });
            return EgoistResult.ok(courseIdList);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "查询最新一个月的课程id失败：" + e.toString(), null);
        }
    }

    /**
     * 评论课程
     *
     * @param parentId 父评论id 没有的填"0"
     * @param courseId 课程id
     * @return 评论id
     */
    public EgoistResult publishCourseComment(String parentId, String courseId) {
        try {
            String url = OnionCollegeConstants.PUBLISH_COURSE_COMMENT_URL;
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("X-Auth-Token", this.getAuthToken());
            PublishCourseCommentRequest request = new PublishCourseCommentRequest();
            request.setObjId(OnionCollegeConstants.ORG_ID);
            request.setObjType((short) 1);
            request.setRemark("好好学习，天天向上");
            request.setParentId(parentId);
            request.setUserId(this.getUserId());
            request.setObjId(courseId);
            JSONObject returnObject = EgoistOkHttp3Util.postHeaderBody(url, header, EgoistJsonUtil.objectToJson(request));
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            Integer commentId = returnObject.getJSONObject("result").getInteger("commentId");
            return EgoistResult.ok(commentId);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "评论课程失败：" + e.toString(), null);
        }
    }

    /**
     * 点赞课程评论
     *
     * @param commentId 评论id
     * @return
     */
    public EgoistResult courseCommentAppraise(String commentId) {
        try {
            String url = OnionCollegeConstants.COURSE_COMMENT_APPRAISE_URL;
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("X-Access-Token", this.getAccessToken());
            CourseCommentAppraiseRequest request = new CourseCommentAppraiseRequest();
            request.setOrgId(OnionCollegeConstants.ORG_ID);
            request.setObjType((short) 2);
            request.setUserId(this.getUserId());
            request.setObjId(commentId);
            JSONObject returnObject = EgoistOkHttp3Util.postHeaderBody(url, header, EgoistJsonUtil.objectToJson(request));
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            return EgoistResult.ok();
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "点赞课程评论失败：" + e.toString(), null);
        }
    }

    /**
     * 课程评论及点赞
     *
     * @param courseId 课程id
     * @return
     */
    public EgoistResult courseCommentAndAppraise(String courseId) {
        try {
            EgoistResult commentResult = this.publishCourseComment("0", courseId);
            if (!EgoistResult.isOk(commentResult)) {
                return commentResult;
            }
            Integer commentId = (Integer) commentResult.getData();
            EgoistResult appraiseResult = this.courseCommentAppraise("" + commentId);
            return appraiseResult;
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "课程评论及点赞异常：" + e.toString(), null);
        }
    }
}
