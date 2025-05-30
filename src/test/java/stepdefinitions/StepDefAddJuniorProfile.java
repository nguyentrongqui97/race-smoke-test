package stepdefinitions;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;

public class StepDefAddJuniorProfile {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AccountManagerPage accountManagerPage;
    CommonPage commonPage;
    SignUpPage signUpPage;

    public StepDefAddJuniorProfile(TestContext testContext) {
        loginPage = testContext.getLoginPage();
        dashboardPage = testContext.getDashboardPage();
        accountManagerPage = testContext.getAccountManagerPage();
        commonPage = testContext.getCommonPage();
        signUpPage = testContext.getSignUpPage();
    }

    @Given("A user successfully logs in with a {string} and {string}")
    public void aUserSuccessfullyLogsInWithAAnd(String username, String password, String membership) {
        loginPage.goToLoginPage();
        loginPage.clickLoginButton();
        loginPage.typeUserName(username);
        loginPage.typePassword(password);
        loginPage.clickSignInButton();
        dashboardPage.verifyCorrectMembershipType(membership);
    }

    @When("the user completes the junior personal details profile")
    public void theUserCompletesTheJuniorPersonalDetailsProfile() throws InterruptedException {
        accountManagerPage.completeJuniorProfile();
    }

    @Then("the new junior profile is successfully added")
    public void theNewJuniorProfileIsSuccessfullyAdded() {
        accountManagerPage.verifyJuniorProfileAdded();
    }

    @Given("A user successfully logs in with a {string} and {string} and {string}")
    public void aUserSuccessfullyLogsInWithAAndAnd(String username, String password, String membership) throws InterruptedException {
        loginPage.goToLoginPage();
        loginPage.clickLoginButton();
        loginPage.typeUserName(username);
        loginPage.typePassword(password);
        loginPage.clickSignInButton();
        loginPage.selectProfileToSignIn();
        dashboardPage.verifyCorrectMembershipType(membership);
    }

    @Given("A user successfully logs in as a {string}")
    public void aUserSuccessfullyLogsInAsA(String membership, String paymentMethod) throws InterruptedException {
        loginPage.goToSignUpPage();
        signUpPage.completeRegisterForANewAccount();
        signUpPage.completePersonalDetails();
        signUpPage.successfullySignUpForANewAccount(membership);
        dashboardPage.verifyCorrectMembershipType(membership);
        signUpPage.chooseMembership(membership, paymentMethod);
    }

    @Given("A user successfully logs in as a {string} paid via {string}")
    public void aUserSuccessfullyLogsInAsAPaidVia(String membership, String paymentMethod) throws InterruptedException {
        loginPage.goToSignUpPage();
        signUpPage.completeRegisterForANewAccount();
        signUpPage.completePersonalDetails();
        signUpPage.chooseMembership(membership, paymentMethod);
        signUpPage.successfullySignUpForANewAccount(membership);
        dashboardPage.verifyCorrectMembershipType(membership);
    }
}
