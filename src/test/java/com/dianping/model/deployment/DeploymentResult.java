package com.dianping.model.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by georgeliu on 2017/5/4.
 */
public class DeploymentResult {

    @JsonProperty(value = "Status")
    private String status;
    @JsonProperty(value = "StartAt")
    private String startAt;
    @JsonProperty(value = "EndAt")
    private String endAt;
    @JsonProperty(value = "ReleaseId")
    private String releaseId;
    @JsonProperty(value = "Commit")
    private String commit;
    @JsonProperty(value = "PackageId")
    private String packageId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    @Override
    public String toString() {
        return "DeploymentResult{" +
                "status='" + status + '\'' +
                ", startAt='" + startAt + '\'' +
                ", endAt='" + endAt + '\'' +
                ", releaseId='" + releaseId + '\'' +
                ", commit='" + commit + '\'' +
                ", packageId='" + packageId + '\'' +
                '}';
    }
}
