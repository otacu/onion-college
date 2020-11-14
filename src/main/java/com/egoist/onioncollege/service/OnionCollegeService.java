package com.egoist.onioncollege.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egoist.onioncollege.constants.OnionCollegeConstants;
import com.egoist.onioncollege.pojo.request.*;
import com.egoist.onioncollege.pojo.response.ClassInfo;
import com.egoist.onioncollege.pojo.response.QueryCourseResult;
import com.egoist.onioncollege.pojo.response.Stage;
import com.egoist.onioncollege.pojo.response.Subject;
import com.egoist.parent.common.constants.EgoistExceptionStatusConstant;
import com.egoist.parent.common.utils.http.EgoistOkHttp3Util;
import com.egoist.parent.common.utils.json.EgoistJsonUtil;
import com.egoist.parent.pojo.dto.EgoistResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class OnionCollegeService {

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
    public EgoistResult login(String userName, String password) {
        try {
            String url = OnionCollegeConstants.LOGIN_URL;
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            LoginRequest request = new LoginRequest();
            request.setSvcCode(OnionCollegeConstants.SVC_CODE);
            request.setUsername(userName);
            request.setPassword(password);
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
     * 发布问答
     *
     * @param title   标题
     * @param content 内容
     * @return
     */
    public EgoistResult createClassQuestion(Integer classId, String title, String content) {
        try {
            String url = String.format(OnionCollegeConstants.CREATE_CLASS_QUESTION_URL, classId);
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
            Integer subjectId = returnObject.getJSONObject("result").getInteger("subjectId");
            return EgoistResult.ok(subjectId);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "发布问答失败：" + e.toString(), null);
        }
    }

    /**
     * 评论问答
     *
     * @param subjectId 问答id
     * @param comment   评论内容
     * @return 结果
     */
    public EgoistResult postClassQuestionComment(Integer subjectId, String comment) {
        try {
            String url = String.format(OnionCollegeConstants.POST_CLASS_QUESTION_COMMENT_URL, subjectId);
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("X-Auth-Token", this.getAuthToken());
            PostClassQuestionCommentRequest request = new PostClassQuestionCommentRequest();
            request.setComment(comment);
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
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "评论问答失败：" + e.toString(), null);
        }
    }

    /**
     * 获取班级列表
     *
     * @return
     */
    public EgoistResult getClassList() {
        try {
            String url = String.format(OnionCollegeConstants.MY_CLASS_LIST_URL, this.getUserId(),
                    OnionCollegeConstants.ORG_ID, new Date().getTime());
            Map<String, String> header = new HashMap<>();
            header.put("X-Access-Token", this.getAccessToken());
            JSONObject returnObject = EgoistOkHttp3Util.getJson(url, header);
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            List<ClassInfo> result = EgoistJsonUtil.jsonToList(EgoistJsonUtil.objectToJson(returnObject.get("result")), ClassInfo.class);
            return EgoistResult.ok(result);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "获取班级列表失败：" + e.toString(), null);
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
            JSONObject returnObject = EgoistOkHttp3Util.getJson(url, header);
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
    public EgoistResult getCourseOfLastistStage() {
        try {
            EgoistResult queryResult = this.queryTrainingClassInfo();
            if (!EgoistResult.isOk(queryResult)) {
                return queryResult;
            }
            QueryCourseResult queryCourseResult = (QueryCourseResult) queryResult.getData();
            List<Stage> stages = queryCourseResult.getStages();
            Stage lastistStage = stages.get(stages.size() - 1);
            return EgoistResult.ok(lastistStage.getCourseItems());
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "查询最新一个月的课程失败：" + e.toString(), null);
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

    /**
     * 标记课程已读
     *
     * @param courseId 课程id
     * @return
     */
    public EgoistResult markCourseReaded(Integer courseId) {
        try {
            String url = String.format(OnionCollegeConstants.MARK_COURSE_READED_URL, courseId, OnionCollegeConstants.ORG_ID, this.getUserId());
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("X-Auth-Token", this.getAuthToken());
            JSONObject returnObject = EgoistOkHttp3Util.postHeaderBody(url, header, "{}");
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
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "标记课程已读失败：" + e.toString(), null);
        }
    }

    /**
     * 保存课程学习记录
     *
     * @param id         courseItem id
     * @param resourceId courseItem resourceId
     * @return 结果
     */
    public EgoistResult writeUserCourseHistory(Integer id, Integer resourceId) {
        try {
            String url = String.format(OnionCollegeConstants.WRITE_USER_COURSE_HISTORY_URL, this.getUserId(),
                    id, resourceId, resourceId);
            Map<String, String> header = new HashMap<>();
            header.put("X-Auth-Token", this.getAuthToken());
            JSONObject returnObject = EgoistOkHttp3Util.getJson(url, header);
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
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "保存课程学习记录失败：" + e.toString(), null);
        }
    }

    /**
     * 获取热门话题列表
     *
     * @return
     */
    public EgoistResult getHotSubjectList() {
        try {
            String url = String.format(OnionCollegeConstants.HOT_SUBJECT_LIST_URL, this.getUserId(), new Date().getTime());
            Map<String, String> header = new HashMap<>();
            header.put("X-Access-Token", this.getAccessToken());
            JSONObject returnObject = EgoistOkHttp3Util.getJson(url, header);
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            JSONArray jsonArray = returnObject.getJSONArray("result");
            List<Subject> subjectList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Subject subject = new Subject();
                subject.setSubjectId(jsonObject.getInteger("subjectId"));
                subject.setUserId(jsonObject.getJSONObject("user").getInteger("userId"));
                subjectList.add(subject);
            }
            return EgoistResult.ok(subjectList);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "获取热门话题列表失败：" + e.toString(), null);
        }
    }

    /**
     * 获取用于评论的话题
     *
     * @return
     */
    public EgoistResult getSubjectForComment() {
        try {
            List<Integer> subjectIdList = new ArrayList<>();
            EgoistResult hotSubjectResult = this.getHotSubjectList();
            if (!EgoistResult.isOk(hotSubjectResult)) {
                return hotSubjectResult;
            }
            List<Subject> subjectList = (List<Subject>) hotSubjectResult.getData();
            subjectList.forEach(subject -> {
                if (subject.getUserId() != 0 && !subject.getUserId().equals(Integer.parseInt(this.getUserId()))) {
                    subjectIdList.add(subject.getSubjectId());
                }
            });
            return EgoistResult.ok(subjectIdList);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "获取用于评论的话题失败：" + e.toString(), null);
        }
    }

    /**
     * 获取班级问答列表
     *
     * @param classId 班级id
     * @return
     */
    public EgoistResult getClassQuestionList(Integer classId) {
        try {
            String url = String.format(OnionCollegeConstants.CLASS_QUESTION_LIST_URL, classId);
            Map<String, String> header = new HashMap<>();
            header.put("X-Auth-Token", this.getAuthToken());
            JSONObject returnObject = EgoistOkHttp3Util.getJson(url, header);
            if (returnObject == null) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "返回报文为空", null);
            }
            Boolean flag = (Boolean) returnObject.get("flag");
            String msg = (String) returnObject.get("msg");
            if (!flag) {
                return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, msg, null);
            }
            JSONArray jsonArray = returnObject.getJSONArray("result");
            List<Subject> subjectList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Subject subject = new Subject();
                subject.setSubjectId(jsonObject.getInteger("subjectId"));
                subject.setUserId(jsonObject.getJSONObject("user").getInteger("userId"));
                subjectList.add(subject);
            }
            return EgoistResult.ok(subjectList);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "获取班级问答列表失败：" + e.toString(), null);
        }
    }

    /**
     * 获取用于评论的问答
     *
     * @return
     */
    public EgoistResult getQuestionForComment() {
        try {
            List<Integer> subjectIdList = new ArrayList<>();
            EgoistResult classQuestionResult = this.getClassQuestionList(OnionCollegeConstants.CLASS_ID_2);
            if (!EgoistResult.isOk(classQuestionResult)) {
                return classQuestionResult;
            }
            List<Subject> subjectList = (List<Subject>) classQuestionResult.getData();
            subjectList.forEach(subject -> {
                if (!subject.getUserId().equals(Integer.parseInt(this.getUserId()))) {
                    subjectIdList.add(subject.getSubjectId());
                }
            });
            return EgoistResult.ok(subjectIdList);
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "获取用于评论的问答失败：" + e.toString(), null);
        }
    }
}
