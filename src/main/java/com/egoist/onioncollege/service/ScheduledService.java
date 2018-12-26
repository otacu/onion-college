package com.egoist.onioncollege.service;

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

    @Scheduled(cron = "0 0 9 ? * *")
    public void scheduled() {
        log.info("#######################定时任务开始########################");
        EgoistResult loginResult = onionCollegeService.login();
        if (!EgoistResult.isOk(loginResult)) {
            log.error("###############################################" + loginResult.getMsg());
            return;
        }
        onionCollegeService.createClassQuestion("111111", "111111111111111");
        onionCollegeService.createClassQuestion("222222", "222222222222222");
        onionCollegeService.postSubjectAndComment();
        EgoistResult courseIdListResult = onionCollegeService.getCourseIdOfLastistStage();
        if (!EgoistResult.isOk(courseIdListResult)) {
            return;
        }
        List<Integer> courseIdList = (List<Integer>) courseIdListResult.getData();
        for (int i = 0; i < 8; i++) {
            onionCollegeService.courseCommentAndAppraise("" + courseIdList.get(i));
        }
        log.info("#######################定时任务结束########################");
    }

}
