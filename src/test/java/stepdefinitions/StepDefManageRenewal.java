package stepdefinitions;

import hooks.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;

public class StepDefManageRenewal {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AccountManagerPage accountManagerPage;
    CommonPage commonPage;
    SignUpPage signUpPage;
    MyProfilePage myProfilePage;
    MembershipManagementPage membershipManagementPage;


    public StepDefManageRenewal(TestContext testContext) {
        loginPage = testContext.getLoginPage();
        dashboardPage = testContext.getDashboardPage();
        accountManagerPage = testContext.getAccountManagerPage();
        commonPage = testContext.getCommonPage();
        signUpPage = testContext.getSignUpPage();
        myProfilePage = testContext.getMyProfilePage();
        membershipManagementPage = testContext.getMembershipManagementPage();
    }

    @When("the user manages renewal for the {string} to {string}")
    public void theUserManagesRenewalForTheTo(String membership, String membershipManageRenewal) {
        membershipManagementPage.manageRenewal(membership, membershipManageRenewal);
    }

    @Then("the user successfully manages renewal to {string}")
    public void theUserSuccessfullyManagesRenewalTo(String membershipManageRenewal) {
        membershipManagementPage.successfullyManageRenewal(membershipManageRenewal);
    }


    @When("the user attempts to manage the renewal for the {string}")
    public void theUserAttemptsToManageTheRenewalForThe(String membership) {
        membershipManagementPage.manageRenewalForInvalidCase();
    }

    @Then("the user should not be able to complete the renewal process.")
    public void theUserShouldNotBeAbleToCompleteTheRenewalProcess() {
        membershipManagementPage.unableToManageRenewalForInvalidCase();
    }
}
