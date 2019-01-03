package com.egoist.onioncollege.service;

import com.egoist.onioncollege.config.OnionCollegeConfig;
import com.egoist.parent.common.utils.string.EgoistStringUtil;
import com.egoist.parent.pojo.dto.EgoistResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
    @Scheduled(cron = "0 15 8 ? * *")
    public void schedule1() {
        log.info("#######################定时任务开始1########################");
        try {
            String[] userNameArray = EgoistStringUtil.split(onionCollegeConfig.getUSERNAME(), ",");
            String[] passwordArray = EgoistStringUtil.split(onionCollegeConfig.getPASSWORD(), ",");
            scheduleContent(userNameArray[0], passwordArray[0]);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("#######################定时任务结束1########################");
    }

    @Scheduled(cron = "0 15 11 ? * *")
    public void schedule2() {
        try {
        log.info("#######################定时任务开始2########################");
        String[] userNameArray = EgoistStringUtil.split(onionCollegeConfig.getUSERNAME(), ",");
        String[] passwordArray = EgoistStringUtil.split(onionCollegeConfig.getPASSWORD(), ",");
        scheduleContent(userNameArray[1], passwordArray[1]);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("#######################定时任务结束2########################");
    }

    @Scheduled(cron = "0 15 13 ? * *")
    public void schedule3() {
        try {
            log.info("#######################定时任务开始3########################");
            String[] userNameArray = EgoistStringUtil.split(onionCollegeConfig.getUSERNAME(), ",");
            String[] passwordArray = EgoistStringUtil.split(onionCollegeConfig.getPASSWORD(), ",");
            scheduleContent(userNameArray[2], passwordArray[2]);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("#######################定时任务结束3########################");
    }

    private void scheduleContent(String userName, String password) {
        // 登录
        EgoistResult loginResult = onionCollegeService.login(userName, password);
        if (!EgoistResult.isOk(loginResult)) {
            log.error("###############################################" + loginResult.getMsg());
            return;
        }
        // 发布班级问答并评论
        onionCollegeService.createClassQuestionAndComment();
        // 发表话题和评论
        onionCollegeService.postSubjectAndComment();
        // 取最新一个月的学习任务，全部评论，点赞评论
        EgoistResult courseIdListResult = onionCollegeService.getCourseIdOfLastistStage();
        if (!EgoistResult.isOk(courseIdListResult)) {
            return;
        }
        List<Integer> courseIdList = (List<Integer>) courseIdListResult.getData();
        for (Integer courseId : courseIdList) {
            onionCollegeService.courseCommentAndAppraise("" + courseId);
        }
    }
}
