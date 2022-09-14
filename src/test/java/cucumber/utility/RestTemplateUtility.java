package cucumber.utility;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CucumberContextConfiguration
//@SpringBootTest(classes = SpringDemoApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class RestTemplateUtility {

//    Resource resource=new ClassPathResource("C:\\Users\\LENOVO\\Downloads\\HelloWorld\\src\\main\\webapp\\WEB-INF\\dispatcher-servlet.xml");
//    BeanFactory factory=new AnnotationConfigApplicationContext();
    public static ResponseResults latestResponse = null;

    static ResponseEntity<String> responseAsString = null;
    protected static ResponseEntity<List> responseAsAllEmpType = null;
    protected static ResponseEntity responseAsEmpType = null;
    protected Response response;

    @Autowired
    protected RestTemplate restTemplate;

    public Response restAssuredGetApi(String url, String reqBody) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(reqBody).get(url);
        return response;
    }

    public void executeGet(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        HttpEntity entity = new HttpEntity(httpHeaders);

        restTemplate.setErrorHandler(errorHandler);
        responseAsString = restTemplate.exchange(url, HttpMethod.GET, entity,
                String.class);
        latestResponse = restTemplate.execute(url, HttpMethod.GET, requestCallback, response -> {
            if (errorHandler.hadError) {
                return (errorHandler.getResults());
            } else {
                return (new ResponseResults(response));
            }
        });
    }


    public void executePost(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate
          .execute(url, HttpMethod.POST, requestCallback, response -> {
              if (errorHandler.hadError) {
                  return (errorHandler.getResults());
              } else {
                  return (new ResponseResults(response));
              }
          });
    }

    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResults results = null;
        private Boolean hadError = false;

        private ResponseResults getResults() {
            return results;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            results = new ResponseResults(response);
        }
    }
}