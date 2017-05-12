package com.dianping.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by georgeliu on 2017/5/4.
 */
public class Deploy {

    @JsonProperty(value = "URI")
    private String uri;
    private int port;
    private String release_id;
    private String template_id;
    private String user;
    private String token;
    private boolean skip;
    private Request request;

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

    public String getRelease_id() {
        return release_id;
    }

    public void setRelease_id(String release_id) {
        this.release_id = release_id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
