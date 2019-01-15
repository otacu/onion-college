package com.egoist.onioncollege.service;

import com.egoist.onioncollege.config.OnionCollegeConfig;
import com.egoist.onioncollege.constants.OnionCollegeConstants;
import com.egoist.onioncollege.enums.SubjectCommentEnum;
import com.egoist.onioncollege.pojo.response.CourseItem;
import com.egoist.parent.common.constants.EgoistExceptionStatusConstant;
import com.egoist.parent.common.utils.string.EgoistStringUtil;
import com.egoist.parent.pojo.dto.EgoistResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ScheduledService {
    @Autowired
    private OnionCollegeService onionCollegeService;

    @Autowired
    private OnionCollegeConfig onionCollegeConfig;

    /**
     * 注意服务器慢8个小时
     */
    @Scheduled(cron = "0 10 1 ? * *")
    public void schedule1() {
        log.info("#######################定时任务开始1########################");
        try {
            String[] userNameArray = EgoistStringUtil.split(onionCollegeConfig.getUSERNAME(), ",");
            String[] passwordArray = EgoistStringUtil.split(onionCollegeConfig.getPASSWORD(), ",");
            for (int i = 0; i < userNameArray.length; i++) {
                scheduleContent(userNameArray[i], passwordArray[i]);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("#######################定时任务结束1########################");
    }

    private void scheduleContent(String userName, String password) {
        try {
            // 登录
            EgoistResult loginResult = onionCollegeService.login(userName, password);
            if (!EgoistResult.isOk(loginResult)) {
                log.error("###############################################" + loginResult.getMsg());
                return;
            }
            // 发布两个班级问答
            this.createTwoClassQuestion();
            // 评论两个问答
            this.commentTwoClassQuestion();
            // 发表两个话题
            this.postTwoSubject();
            // 评论两个话题
            this.commentTwoSubject();
            // 取最新一个月的学习任务
            EgoistResult courseIdListResult = onionCollegeService.getCourseOfLastistStage();
            if (!EgoistResult.isOk(courseIdListResult)) {
                return;
            }
            List<CourseItem> courseList = (List<CourseItem>) courseIdListResult.getData();
            for (CourseItem course : courseList) {
                // 全部评论，点赞评论
                onionCollegeService.courseCommentAndAppraise("" + course.getResourceId());
                // 全部标记已读
                onionCollegeService.markCourseReaded(course.getResourceId());
                // 保存课程学习记录
                onionCollegeService.writeUserCourseHistory(course.getId(), course.getResourceId());
            }
            // 40分钟
            Thread.sleep(2400000);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 发表两个话题
     *
     * @return
     */
    public EgoistResult postTwoSubject() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            for (int i = 0; i < 2; i++) {
                int code = dayOfWeek * i;
                EgoistResult postSubjectResult = onionCollegeService.postSubject(SubjectCommentEnum.getContent(code));
                if (!EgoistResult.isOk(postSubjectResult)) {
                    log.error(postSubjectResult.getMsg());
                    continue;
                }
            }
            return EgoistResult.ok();
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "发表两个话题异常：" + e.toString(), null);
        }
    }

    /**
     * 评论两个话题
     *
     * @return
     */
    public EgoistResult commentTwoSubject() {
        try {
            EgoistResult queryResult = onionCollegeService.getSubjectForComment();
            if (!EgoistResult.isOk(queryResult)) {
                return queryResult;
            }
            List<Integer> subjectIdList = (List<Integer>) queryResult.getData();
            for (int i = 0; i < 2; i++) {
                onionCollegeService.postSubjectComment(subjectIdList.get(i), "打卡");
            }
            return EgoistResult.ok();
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "评论两个话题异常：" + e.toString(), null);
        }
    }

    /**
     * 创建两个问答
     *
     * @return
     */
    public EgoistResult createTwoClassQuestion() {
        try {
//            EgoistResult getClassListResult = onionCollegeService.getClassList();
//            if (!EgoistResult.isOk(getClassListResult)) {
//                return getClassListResult;
//            }
//            List<ClassInfo> classList = (List<ClassInfo>) getClassListResult.getData();
//            for (ClassInfo classInfo : classList) {
            onionCollegeService.createClassQuestion(OnionCollegeConstants.CLASS_ID_2, "好好学习天天向上", "....................");
            onionCollegeService.createClassQuestion(OnionCollegeConstants.CLASS_ID_2, "班级问答打卡", "....................");
//            }
            return EgoistResult.ok();
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "创建两个问答异常：" + e.toString(), null);
        }
    }

    /**
     * 评论两个问答
     *
     * @return
     */
    public EgoistResult commentTwoClassQuestion() {
        try {
            EgoistResult queryResult = onionCollegeService.getQuestionForComment();
            if (!EgoistResult.isOk(queryResult)) {
                return queryResult;
            }
            List<Integer> subjectIdList = (List<Integer>) queryResult.getData();
            for (int i = 0; i < 2; i++) {
                onionCollegeService.postClassQuestionComment(subjectIdList.get(i), "签到");
            }
            return EgoistResult.ok();
        } catch (Exception e) {
            return new EgoistResult(EgoistExceptionStatusConstant.STATUS_400, "评论两个问答异常：" + e.toString(), null);
        }
    }
}
