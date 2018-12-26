package com.egoist.onioncollege.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum SubjectCommentEnum {
    CONTENT0(0, "签到", "打卡"),
    CONTENT7(1, "星期日", "打卡"),
    CONTENT1(2, "星期一", "打卡"),
    CONTENT2(3, "星期二", "打卡"),
    CONTENT3(4, "星期三", "打卡"),
    CONTENT4(5, "星期四", "打卡"),
    CONTENT5(6, "星期五", "打卡"),
    CONTENT6(7, "星期六", "打卡"),
    ;

    @Getter
    private Integer code;

    @Getter
    private String content;

    @Getter
    private String reference;

    public static String getContent(Integer code) {
        SubjectCommentEnum[] values = SubjectCommentEnum.values();
        return Arrays.stream(values).filter(s -> s.getCode() == code).findFirst()
                .map(SubjectCommentEnum::getContent).orElse("");
    }

    public static String getReference(Integer code) {
        SubjectCommentEnum[] values = SubjectCommentEnum.values();
        return Arrays.stream(values).filter(s -> s.getCode() == code).findFirst()
                .map(SubjectCommentEnum::getReference).orElse("");
    }

}
