package hooks;

import common.BaseTest;
import drivers.DriverManager;
import helpers.CaptureHelpers;
import helpers.PropertiesHelpers;
import io.cucumber.java.*;
import org.testng.ITestResult;
import utils.LogUtils;

public class CucumberHooks {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("================ beforeAll ================");
        PropertiesHelpers.loadAllFiles();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("================ afterAll ================");
        if(DriverManager.getDriver() != null){
            BaseTest.closeDriver();
        }
    }

    @Before
    public void beforeScenario() {
        System.out.println("================ beforeScenario ================");
        BaseTest.createDriver();
    }

    @After
    public void afterScenario() {
        System.out.println("================ afterScenario ================");
        BaseTest.closeDriver();

    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("================ beforeStep ================");

    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("================ afterStep ================");
        if (scenario.isFailed()) {
            CaptureHelpers.takeScreenshot(String.valueOf(scenario));
        }
    }
}
