package hooks;

import drivers.DriverFactory;
import drivers.DriverManager;
import helpers.CaptureHelpers;
import helpers.PropertiesHelpers;
import io.cucumber.java.*;

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
            DriverFactory.closeDriver();
        }
    }

    @Before
    public void beforeScenario() {
        System.out.println("================ beforeScenario ================");
        DriverFactory.createDriver();
    }

    @After
    public void afterScenario() {
        System.out.println("================ afterScenario ================");
        DriverFactory.closeDriver();

    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("================ afterStep ================");
        if (scenario.isFailed()) {
            CaptureHelpers.takeScreenshot(String.valueOf(scenario));
        }
    }
}
