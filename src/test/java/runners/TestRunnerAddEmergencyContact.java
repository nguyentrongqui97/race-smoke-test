package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Add_Emergency_Contact.feature",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/Add_Emergency_Contact.html",
                "json:target/cucumber-reports/Add_Emergency_Contact.json"},
        monochrome = true
)

@Test
public class TestRunnerAddEmergencyContact extends AbstractTestNGCucumberTests {
    //Parallel Execution
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

