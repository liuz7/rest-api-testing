package com.dianping.base;

import com.dianping.retry.RetryService;
import com.dianping.task.CheckDeploymentTask;
import com.dianping.task.StartDeploymentTask;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.support.RetryTemplate;
import org.testng.annotations.BeforeSuite;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.restassured.config.ConnectionConfig.connectionConfig;
import static io.restassured.config.DecoderConfig.decoderConfig;
import static io.restassured.config.HttpClientConfig.httpClientConfig;

/**
 * Created by georgeliu on 2017/4/18.
 */
public class BaseTest extends Common {

    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static RetryTemplate retryTemplate = RetryService.retryTemplate();

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() throws Exception {
        deploy();
        String baseURI = checkNotNull(configuration.getBase().getUri());
        int port = checkNotNull(configuration.getBase().getPort());
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.config = RestAssured.config().connectionConfig(connectionConfig().closeIdleConnectionsAfterEachResponse());
        RestAssured.baseURI = baseURI;
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config = RestAssured.config().decoderConfig(decoderConfig().defaultCharsetForContentType("UTF-8", "application/json"));
        //RestAssured.filters(new SSOFilter());
        RestAssured.authentication = RestAssured.preemptive().basic("username", "password");
    }

    private void deploy() {
        boolean isSkipDeploy = checkNotNull(configuration.getDeploy().isSkip());
        if (!isSkipDeploy) {
            logger.info("Start deploying ...");
            String jobId = retryTemplate.execute(new StartDeploymentTask());
            retryTemplate.execute(new CheckDeploymentTask(jobId));
            logger.info("Finish deploying ...");
        } else {
            logger.info("Deployment Skipped.");
        }
    }

}
