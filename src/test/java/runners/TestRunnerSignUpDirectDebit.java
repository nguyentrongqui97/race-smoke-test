package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Sign_Up_Pay_Via_Direct_Debit.feature",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/Sign_Up_Pay_Via_Direct_Debit.html",
                "json:target/cucumber-reports/Sign_Up_Pay_Via_Direct_Debit.json"}
)

@Test
public class TestRunnerSignUpDirectDebit extends AbstractTestNGCucumberTests {
}

