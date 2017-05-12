package com.dianping.task;

import com.dianping.base.Common;
import com.dianping.exception.DeploymentException;
import com.dianping.model.deployment.DeploymentRequest;
import com.dianping.utils.MtBATool;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

/**
 * Created by georgeliu on 2017/5/4.
 */
public class StartDeploymentTask extends Common implements RetryCallback<String, DeploymentException> {

    public static final Logger logger = LoggerFactory.getLogger(StartDeploymentTask.class);
    private static String releaseId = checkNotNull(configuration.getDeploy().getRelease_id());
    private static String templateId = checkNotNull(configuration.getDeploy().getTemplate_id());
    private static String user = checkNotNull(configuration.getDeploy().getUser());
    private static String secret = checkNotNull(configuration.getDeploy().getToken());
    private static String baseURI = checkNotNull(configuration.getDeploy().getUri());
    private static int port = checkNotNull(configuration.getDeploy().getPort());
    private static String commit = checkNotNull(configuration.getDeploy().getRequest().getCommit());
    private static String label = checkNotNull(configuration.getDeploy().getRequest().getLabel());

    public String doWithRetry(RetryContext context) throws DeploymentException {
        return startDeployment(releaseId, templateId, commit, label);
    }

    private String startDeployment(String releaseId, String templateId, String commit, String label) {
        String jobId;
        DeploymentRequest deployment = new DeploymentRequest();
        deployment.setCommit(commit);
        deployment.setLabel(label);

        String path = String.format("/ci/release/%s/template/%s/build", releaseId, templateId);
        String date = MtBATool.authDate();
        String authorization = MtBATool.sign("POST", path, date, secret);

        Response response = given().baseUri(baseURI).port(port).
                header("Date", date).
                header("Authorization", "MWS" + " " + user + ":" + authorization).
                accept(ContentType.JSON).contentType(ContentType.JSON).
                body(deployment).
                when().post(path);
        int statusCode = response.getStatusCode();
        //logger.info(String.valueOf(statusCode));
        if (statusCode == 200) {
            jobId = checkNotNull(from(response.asString()).getString("JobId"));
            logger.info("Job Id:" + jobId);
        } else {
            throw new DeploymentException("DeploymentRequest init failed");
        }
        return jobId;
    }
}
