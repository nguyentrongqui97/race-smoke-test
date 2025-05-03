package hooks;

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import pages.CommonPage;
import pages.DashboardPage;
import pages.LoginPage;

public class TestContext {

    private CommonPage commonPage;
    private LoginPage loginPage;
    private DashboardPage dashBoardPage;

    public TestContext() {
//        ThreadGuard.protect(new DriverFactory().createDriver());
//        LogUtils.info("Driver in TestContext: " + getDriver());
    }

    public LoginPage    getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public CommonPage getCommonPage() {
        if (commonPage == null) {
            commonPage = new CommonPage();
        }
        return commonPage;
    }

    public DashboardPage getDashboardPage() {
        if (dashBoardPage == null) {
            dashBoardPage = new DashboardPage();
        }
        return dashBoardPage;
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }


}
