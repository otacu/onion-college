package com.egoist.onioncollege.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OnionCollegeConfig {
    @Value("${onion.college.username}")
    private String USERNAME;

    @Value("${onion.college.password}")
    private String PASSWORD;

    /**
     * @return USERNAME
     */
    public String getUSERNAME() {
        return USERNAME;
    }

    /**
     * @return PASSWORD
     */
    public String getPASSWORD() {
        return PASSWORD;
    }
}
