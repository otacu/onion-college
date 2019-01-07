package com.egoist.onioncollege.service;

import com.alibaba.fastjson.JSON;
import com.egoist.onioncollege.constants.OnionCollegeConstants;
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

    private void init() {
        onionCollegeService.setUserId("1133756");
        onionCollegeService.setAuthToken("aAIAAAcACAAABLxMEQAAAAAACAAABGUYAAAAAAAACAAABN97AgAAAAAAEwAABzI5NzA1MTUyMDI0MjUzNzQzMDIUAAAHamluZ3NoZW5nLXllQG1zeWMuY2MUAAAHamluZ3NoZW5nLXllQG1zeWMuY2MLAAAHMTUwMTg0MjE4NDk=");
        onionCollegeService.setAccessToken("V1.0|oRRvXstpELwrwDhruX4nUwHaJOQzx9sKmNUmjYxVKtHKRQsqBiGJVsvcx1oLzBhtdaRAQxwtVkHMq8vrjSgwCo4YyBEwVt3F8z7p1T0L0hVQPUOyXnPGHkPacVVtniqB");
    }

    @Test
    public void testCreateClassQuestion() {
        this.init();
        onionCollegeService.createClassQuestion(OnionCollegeConstants.CLASS_ID_1, "111111", "111111111111111");
    }

    @Test
    public void testQueryTrainingClassInfo() {
        this.init();
        EgoistResult result = onionCollegeService.queryTrainingClassInfo();
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetCourseIdOfLastistStage() {
        this.init();
        EgoistResult result = onionCollegeService.getCourseOfLastistStage();
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testCourseCommentAndAppraise() {
        this.init();
        EgoistResult result = onionCollegeService.courseCommentAndAppraise("" + 15137008);
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testLogin() {
        EgoistResult result = onionCollegeService.login("jingsheng-ye@msyc.cc", "123456");
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
        System.out.println(onionCollegeService.getUserId());
        System.out.println(onionCollegeService.getAuthToken());
        System.out.println(onionCollegeService.getAccessToken());
    }

    @Test
    public void testGetClassList() {
        this.init();
        EgoistResult result = onionCollegeService.getClassList();
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testMarkCourseReaded() {
        this.init();
        EgoistResult result = onionCollegeService.markCourseReaded(15137671);
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testWriteUserCourseHistory() {
        this.init();
        EgoistResult result = onionCollegeService.writeUserCourseHistory(80832, 15137671);
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetSubjectForComment() {
        this.init();
        EgoistResult result = onionCollegeService.getSubjectForComment();
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetQuestionForComment() {
        this.init();
        EgoistResult result = onionCollegeService.getQuestionForComment();
        System.out.println("##################################################");
        System.out.println(JSON.toJSONString(result));
    }
}
