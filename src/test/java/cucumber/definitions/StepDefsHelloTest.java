package cucumber.definitions;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.utility.RestTemplateUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;

public class StepDefsHelloTest extends RestTemplateUtility {

    private static final String REQ_API = "/hellocntrl";
    private static final String APPLICATION_JSON = "application/json";
    private final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @When("^the client calls /baeldung$")
    public void the_client_issues_POST_hello() throws Throwable {
//        ModelAndView res1 = controller.showMessage();
//        System.out.println("Response: "+ res1);
//        System.out.println("EmpContoller bean: " + empController);
//        empController.getEmployeeManager();
//        executeGet("http://localhost:8082/hello");

        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo(REQ_API))
                .withHeader("content-type", equalTo(APPLICATION_JSON))
                //.withRequestBody(containing("testing-framework"))
                .willReturn(aResponse().withBody("Welcome to API call")));
//                .willReturn(aResponse().withStatus(200)));


        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + REQ_API);
//        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", APPLICATION_JSON);
        //request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        String res = convertResponseToString(response);
        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Welcome to API call", res);
        verify(getRequestedFor(urlEqualTo(REQ_API))
                .withHeader("content-type", equalTo(APPLICATION_JSON)));

        wireMockServer.stop();
    }

    @Given("^the client calls /hello$")
    public void the_client_issues_GET_hello() throws Throwable {
        //response = restAssuredGetApi("/hello", "");
        //executeGet("http://localhost:8082/hello");
    }

    @Then("^the baeldung client receives status code of (\\d+)$")
    public void the_baeldung_client_receives_status_code_of(int statusCode, String docString) throws Throwable {
//        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
//        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
//        assertThat(docString, is("new lines added"));
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
//        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
//        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

}