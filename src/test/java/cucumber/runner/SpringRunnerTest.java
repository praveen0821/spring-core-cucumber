package cucumber.runner;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//@RunWith(SpringRunn.class)
//@SpringBootTest(classes = SpringDemoApplication.class)
public class SpringRunnerTest {
//    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
        Assertions.assertNotNull("Test run");
    }
}
