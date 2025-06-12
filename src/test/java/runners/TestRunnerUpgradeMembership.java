package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Upgrade_Membership.feature",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/Upgrade_Membership.html",
                "json:target/cucumber-reports/Upgrade_Membership.json"},
        monochrome = true
)

@Test
public class TestRunnerUpgradeMembership extends AbstractTestNGCucumberTests {
    //Parallel Execution
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

