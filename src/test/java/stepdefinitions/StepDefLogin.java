package stepdefinitions;

import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import pages.CommonPage;
import pages.DashboardPage;
import pages.LoginPage;
import java.util.List;


public class StepDefLogin {

    LoginPage loginPage;
    CommonPage commonPage;
    DashboardPage dashboardPage;

    public StepDefLogin(TestContext testContext) {
        loginPage = testContext.getLoginPage();
        commonPage = testContext.getCommonPage();
        dashboardPage = testContext.getDashboardPage();
    }

    @Given("a user is on the login page")
    public void aUserIsOnTheLoginPage() {
        loginPage.goToLoginPage();
        loginPage.clickLoginButton();
    }

    @When("the user inputs {string} and {string}")
    public void theUserInputsAnd(String username, String password) {
        loginPage.typeUserName(username);
        loginPage.typePassword(password);
        loginPage.clickSignInButton();
    }

    @Then("the user should be logged into successfully as a {string} member")
    public void theUserShouldBeLoggedInSuccessfullyAsAMember(String membershipType) {
        loginPage.selectProfileToSignIn();
        dashboardPage.verifyCorrectMembershipType(membershipType);
    }
}
