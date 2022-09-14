package cucumber.definitions;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@CucumberContextConfiguration
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
//@SpringBootTest(classes = SpringDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberGlueTest {

}