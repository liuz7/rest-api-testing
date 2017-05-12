package com.dianping.task;

import com.dianping.base.Common;
import com.dianping.exception.DeploymentException;
import com.dianping.model.deployment.DeploymentResult;
import com.dianping.utils.MtBATool;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

/**
 * Created by georgeliu on 2017/5/2.
 */
public class CheckDeploymentTask extends Common implements RetryCallback<DeploymentResult, DeploymentException> {

    public static final Logger logger = LoggerFactory.getLogger(CheckDeploymentTask.class);
    private static String releaseId = checkNotNull(configuration.getDeploy().getRelease_id());
    private static String user = checkNotNull(configuration.getDeploy().getUser());
    private static String secret = checkNotNull(configuration.getDeploy().getToken());
    private static String baseURI = checkNotNull(configuration.getDeploy().getUri());
    private static int port = checkNotNull(configuration.getDeploy().getPort());
    private String jobId;

    public CheckDeploymentTask(String jobId) {
        this.jobId = jobId;
    }

    public DeploymentResult doWithRetry(RetryContext context) throws DeploymentException {
        DeploymentResult deploymentResult = checkDeployment(releaseId, jobId);
        return deploymentResult;
    }

    private DeploymentResult checkDeployment(String releaseId, String jobId) {
        DeploymentResult deploymentResult;
        String path = String.format("/ci/release/%s/job/%s", releaseId, jobId);
        String date = MtBATool.authDate();
        String authorization = MtBATool.sign("GET", path, date, secret);

        Response response = given().baseUri(baseURI).port(port).
                header("Date", date).
                header("Authorization", "MWS" + " " + user + ":" + authorization).
                accept(ContentType.JSON).contentType(ContentType.JSON).
                when().get(path);
        int statusCode = response.getStatusCode();
        //logger.info(String.valueOf(statusCode));
        if (statusCode == 200) {
            String deploymentStatus = checkNotNull(from(response.asString()).getString("Status"));
            logger.info(deploymentStatus);
            if (!deploymentStatus.equalsIgnoreCase("Success")) {
                throw new DeploymentException("Deployment failed");
            } else {
                deploymentResult = response.as(DeploymentResult.class, ObjectMapperType.JACKSON_2);
                logger.info(deploymentResult.toString());
            }
        } else {
            throw new DeploymentException("Deployment failed");
        }
        return deploymentResult;
    }

}
