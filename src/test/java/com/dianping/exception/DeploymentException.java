package com.dianping.exception;

/**
 * Created by georgeliu on 2017/5/2.
 */
public class DeploymentException extends RuntimeException {

    public DeploymentException(String message) {
        super(message);
    }

    public DeploymentException() {
    }

    public DeploymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeploymentException(Throwable cause) {
        super(cause);
    }

}
