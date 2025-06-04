package stepdefinitions;

import hooks.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;

public class StepDefUpgradeMembership {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AccountManagerPage accountManagerPage;
    CommonPage commonPage;
    SignUpPage signUpPage;
    MyProfilePage myProfilePage;
    MembershipManagementPage membershipManagementPage;


    public StepDefUpgradeMembership(TestContext testContext) {
        loginPage = testContext.getLoginPage();
        dashboardPage = testContext.getDashboardPage();
        accountManagerPage = testContext.getAccountManagerPage();
        commonPage = testContext.getCommonPage();
        signUpPage = testContext.getSignUpPage();
        myProfilePage = testContext.getMyProfilePage();
        membershipManagementPage = testContext.getMembershipManagementPage();
    }

    @When("the user upgrades the {string} to {string} paying via {string}")
    public void theUserUpgradesTheToPayingVia(String beforeUpgradeMembership, String upgradedMembership, String upgradedPaymentMethod) {
        membershipManagementPage.upgradeAMembership(beforeUpgradeMembership, upgradedMembership, upgradedPaymentMethod);

    }

    @Then("the user successfully upgrades the membership to {string}")
    public void theUserSuccessfullyUpgradesTheMembershipTo(String membership) {
        membershipManagementPage.successfullyUpgradeAMembership(membership);
    }
}
