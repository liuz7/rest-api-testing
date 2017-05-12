package com.dianping.model.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by georgeliu on 2017/5/3.
 */
public class DeploymentRequest {

    @JsonProperty(value = "Commit")
    private String commit;
    @JsonProperty(value = "Label")
    private String label;

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
