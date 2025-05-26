package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Sign_Up.feature",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/Sign_Up.html",
                "json:target/cucumber-reports/Sign_Up.json"}
)

@Test
public class TestRunnerSignUp extends AbstractTestNGCucumberTests {
}

