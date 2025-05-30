package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Login.feature",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/Login.html",
                "json:target/cucumber-reports/Login.json"},
        tags = "@SMOKE"
)

@Test
public class TestRunnerLogin extends AbstractTestNGCucumberTests {
}

