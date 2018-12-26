package com.egoist.onioncollege.pojo.request;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    private String svcCode;

    private String username;

    private String password;

    private String orgCode;

    /**
     * @return svcCode
     */
    public String getSvcCode() {
        return svcCode;
    }

    /**
     * @param svcCode svcCode
     */
    public void setSvcCode(String svcCode) {
        this.svcCode = svcCode;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return orgCode
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
