package cucumber.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = {"pretty",
        "html:target/cucumber-report.html", "json:target/cucumber-report.json"},
        glue = {"cucumber.definitions"}, tags = "(@Test1 or @Test2 or @Test4) and not @Test3")
public class CucumberRunnerTest {
}