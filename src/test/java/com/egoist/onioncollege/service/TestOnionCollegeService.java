package com.egoist.onioncollege.service;

import com.alibaba.fastjson.JSON;
import com.egoist.parent.pojo.dto.EgoistResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOnionCollegeService {
    @Autowired
    private OnionCollegeService onionCollegeService;

    @Test
    public void testSubject() {
        onionCollegeService.postSubjectAndComment();
    }

    @Test
    public void testCreateClassQuestion() {
        onionCollegeService.createClassQuestion("111111", "111111111111111");
    }

    @Test
    public void testQueryTrainingClassInfo() {
        EgoistResult result = onionCollegeService.queryTrainingClassInfo();
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetCourseIdOfLastistStage() {
        EgoistResult result = onionCollegeService.getCourseIdOfLastistStage();
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
        // [15137008,15137111,15137113,15137114,15137115,15137520,15137659,15137660,15137661,15137662,15137663,15137664,15137665,15137666,15137667,15137668,15137669,15137670,15137671,15137673]
    }

    @Test
    public void testCourseCommentAndAppraise() {
        EgoistResult result = onionCollegeService.courseCommentAndAppraise("" + 15137008);
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testLogin() {
        EgoistResult result = onionCollegeService.login();
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
        System.out.println(onionCollegeService.getUserId());
        System.out.println(onionCollegeService.getAuthToken());
        System.out.println(onionCollegeService.getAccessToken());
    }
}
