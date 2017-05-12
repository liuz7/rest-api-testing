package com.dianping.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by georgeliu on 2017/5/4.
 */
public class Base {

    @JsonProperty(value = "URI")
    private String uri;
    private int port;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
