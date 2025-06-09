package stepdefinitions;

import hooks.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;

public class StepDefRefundMembership {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AccountManagerPage accountManagerPage;
    CommonPage commonPage;
    SignUpPage signUpPage;
    MyProfilePage myProfilePage;
    MembershipManagementPage membershipManagementPage;


    public StepDefRefundMembership(TestContext testContext) {
        loginPage = testContext.getLoginPage();
        dashboardPage = testContext.getDashboardPage();
        accountManagerPage = testContext.getAccountManagerPage();
        commonPage = testContext.getCommonPage();
        signUpPage = testContext.getSignUpPage();
        myProfilePage = testContext.getMyProfilePage();
        membershipManagementPage = testContext.getMembershipManagementPage();
    }
    @When("the user refunds the membership within {int} days from {string} to {string}")
    public void theUserRefundsTheMembershipWithinDaysFromTo(int days, String upgradedMembership, String refundMembership) {
        membershipManagementPage.refundAMembership(upgradedMembership, refundMembership);
    }


    @Then("the user successfully reverts to the {string}")
    public void theUserSuccessfullyRevertsToThe(String membership) {
        membershipManagementPage.successfullyRefundAMembership(membership);
    }

}
