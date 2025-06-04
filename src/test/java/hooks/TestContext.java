package hooks;

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import pages.*;

public class TestContext {

    private CommonPage commonPage;
    private LoginPage loginPage;
    private DashboardPage dashBoardPage;
    private SignUpPage signUpPage;
    private AccountManagerPage accountManagerPage;
    private MyProfilePage myProfilePage;
    private MembershipManagementPage membershipManagementPage;


    public TestContext() {
//        ThreadGuard.protect(new DriverFactory().createDriver());
//        LogUtils.info("Driver in TestContext: " + getDriver());
    }

    public LoginPage getLoginPage() {
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

    public SignUpPage getSignUpPage() {
        if (signUpPage == null) {
            signUpPage = new SignUpPage();
        }
        return signUpPage;
    }

    public AccountManagerPage getAccountManagerPage() {
        if (accountManagerPage == null) {
            accountManagerPage = new AccountManagerPage();
        }
        return accountManagerPage;
    }

    public MyProfilePage getMyProfilePage() {
        if (myProfilePage == null) {
            myProfilePage = new MyProfilePage();
        }
        return myProfilePage;
    }

    public MembershipManagementPage getMembershipManagementPage() {
        if (membershipManagementPage == null) {
            membershipManagementPage = new MembershipManagementPage(signUpPage);
        }
        return membershipManagementPage;
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }


}
