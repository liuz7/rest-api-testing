package com.dianping.retry;

import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * Created by georgeliu on 2017/5/2.
 */
public class RetryService {

    public static RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(1000l);
        exponentialBackOffPolicy.setMultiplier(2);
        exponentialBackOffPolicy.setMaxInterval(64 * 1000l);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(5 * 60 * 1000l);
        retryTemplate.setRetryPolicy(timeoutRetryPolicy);
        return retryTemplate;
    }
}
