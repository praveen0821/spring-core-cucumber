package cucumber.definitions;

import cucumber.utility.RestTemplateUtility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class StepDefsVersionTest extends RestTemplateUtility {

    @When("^the client calls /version$")
    public void the_client_issues_GET_version() throws Throwable {
//        executeGet("http://localhost:8082/version");
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) throws Throwable {
        //assertThat(latestResponse.getBody()).isEqualTo(version);
    }
}