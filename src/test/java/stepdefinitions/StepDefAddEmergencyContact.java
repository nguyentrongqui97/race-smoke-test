package stepdefinitions;

import hooks.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;

public class StepDefAddEmergencyContact {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AccountManagerPage accountManagerPage;
    CommonPage commonPage;
    SignUpPage signUpPage;
    MyProfilePage myProfilePage;

    public StepDefAddEmergencyContact(TestContext testContext) {
        loginPage = testContext.getLoginPage();
        dashboardPage = testContext.getDashboardPage();
        accountManagerPage = testContext.getAccountManagerPage();
        commonPage = testContext.getCommonPage();
        signUpPage = testContext.getSignUpPage();
        myProfilePage = testContext.getMyProfilePage();
    }

    @When("the user adds information for {string} emergency contact")
    public void theUserAddsInformationForEmergencyContact(String number) {
        dashboardPage.clickViewMyProfile();
        myProfilePage.addEmergencyContact(number);
    }


    @Then("{string} emergency contact shall be successfully added")
    public void emergencyContactShallBeSuccessfullyAdded(String number) {
        myProfilePage.emergencyContactSuccessfullyAdded(number);
    }
}
