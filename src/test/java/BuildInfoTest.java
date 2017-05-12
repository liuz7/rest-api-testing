import com.dianping.base.BaseTest;
import com.dianping.model.test.BuildInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BuildInfoTest extends BaseTest {

    @Test
    public void buildInfoTest1() throws Exception {
        given().accept(ContentType.JSON).
                param("env", "beta").param("branch", "master").
                when().
                get("/build/ezc/duo_module/buildInfo").
                then().
                statusCode(200).
                body("jenkins_job_name", equalTo("beta-ezc-duo_module"));
    }

    @Test
    public void buildInfoTest2() throws Exception {
        Response response = given().accept(ContentType.JSON).
                param("env", "beta").param("branch", "master").
                when().get("/build/ezc/duo_module/buildInfo");
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(from(response.asString()).getString("jenkins_job_name")).isEqualToIgnoringCase("beta-ezc-duo_module");
    }

    @Test
    public void buildInfoTest3() throws Exception {
        BuildInfo buildInfo = given().accept(ContentType.JSON).
                param("env", "beta").param("branch", "master").
                when().get("/build/ezc/duo_module/buildInfo").as(BuildInfo.class);;
        assertThat(buildInfo.getJenkinsJobName()).isEqualToIgnoringCase("beta-ezc-duo_module");
    }

}
