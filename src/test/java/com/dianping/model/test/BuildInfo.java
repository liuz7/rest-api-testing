package com.dianping.model.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by georgeliu on 2017/4/20.
 */
public class BuildInfo {

    private String buildNumber;

    private Date buildTime;

    private String buildCommit;

    private String buildStatus;
    @JsonProperty(value = "jenkins_url")
    private String jenkinsUrl;
    @JsonProperty(value = "jenkins_job_name")
    private String jenkinsJobName;
    @JsonProperty(value = "jenkins_log_url")
    private String jenkinsLogUrl;
    @JsonProperty(value = "jenkins_last_build_data")
    private String jenkinsLastBuildData;
    @JsonProperty(value = "jenkins_last_build_url")
    private String jenkinsLastBuildUrl;

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getBuildCommit() {
        return buildCommit;
    }

    public void setBuildCommit(String buildCommit) {
        this.buildCommit = buildCommit;
    }

    public String getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(String buildStatus) {
        this.buildStatus = buildStatus;
    }

    public String getJenkinsUrl() {
        return jenkinsUrl;
    }

    public void setJenkinsUrl(String jenkinsUrl) {
        this.jenkinsUrl = jenkinsUrl;
    }

    public String getJenkinsJobName() {
        return jenkinsJobName;
    }

    public void setJenkinsJobName(String jenkinsJobName) {
        this.jenkinsJobName = jenkinsJobName;
    }

    public String getJenkinsLogUrl() {
        return jenkinsLogUrl;
    }

    public void setJenkinsLogUrl(String jenkinsLogUrl) {
        this.jenkinsLogUrl = jenkinsLogUrl;
    }

    public String getJenkinsLastBuildData() {
        return jenkinsLastBuildData;
    }

    public void setJenkinsLastBuildData(String jenkinsLastBuildData) {
        this.jenkinsLastBuildData = jenkinsLastBuildData;
    }

    public String getJenkinsLastBuildUrl() {
        return jenkinsLastBuildUrl;
    }

    public void setJenkinsLastBuildUrl(String jenkinsLastBuildUrl) {
        this.jenkinsLastBuildUrl = jenkinsLastBuildUrl;
    }
}
