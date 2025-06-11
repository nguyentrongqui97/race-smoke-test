package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Manage_Renewal.feature",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/Manage_Renewal.html",
                "json:target/cucumber-reports/Manage_Renewal.json"},
        tags = "@SMOKE"
)

@Test
public class TestRunnerManageRenewal extends AbstractTestNGCucumberTests {
}

