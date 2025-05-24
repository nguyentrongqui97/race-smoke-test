package stepdefinitions;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import pages.CommonPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SignUpPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class StepDefSignUp {

    LoginPage loginPage;
    CommonPage commonPage;
    DashboardPage dashboardPage;
    SignUpPage signUpPage;

    public StepDefSignUp(TestContext testContext) {
        loginPage = testContext.getLoginPage();
        commonPage = testContext.getCommonPage();
        dashboardPage = testContext.getDashboardPage();
        signUpPage = testContext.getSignUpPage();
    }

    @Given("a user successfully registers for an account")
    public void aUserSuccessfullyRegistersForAnAccount() throws InterruptedException, IOException, UnsupportedFlavorException {
        loginPage.goToSignUpPage();
        signUpPage.completeRegisterForANewAccount();

    }
}
