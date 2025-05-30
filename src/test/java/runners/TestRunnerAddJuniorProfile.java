package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Add_Junior_Profile.feature",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/Add_Junior_Profile.html",
                "json:target/cucumber-reports/Add_Junior_Profile.json"},
        tags = "@SMOKE"
)

@Test
public class TestRunnerAddJuniorProfile extends AbstractTestNGCucumberTests {
}

