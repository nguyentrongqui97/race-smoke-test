package stepdefinitions;

import common.BaseTest;
import constants.ConstantGlobal;
import drivers.DriverManager;
import helpers.ExcelHelpers;
import helpers.PropertiesHelpers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import utils.LogUtils;

public class MyStepdefs {
    @Given("a user successfully log in")
    public void aUserSuccessfullyLogIn() throws InterruptedException {
//        ExcelHelpers excel = new ExcelHelpers();
//        excel.setExcelFile("src/test/resources/Book1.xlsx", "Sheet1");
//        String userName = excel.getCellData("username",2);
//        String password = excel.getCellData("password",2);
//        System.out.println(userName);
//        System.out.println(password);
//
//        LogUtils.info("HEHEHEH");

        BaseTest.createDriver();
        DriverManager.getDriver().get("https://www.youtube.com/watch?v=7efrS-2oTrg&list=PLgcMOggxCIP1DNm0WOVZP6vbvMVO4Vl7H&index=3");
    }

    @And("I navigate to Sign in page")
    public void iNavigateToSignInPage() {
    }

    @When("I choose {string}")
    public void iChoose(String arg0) {
    }

    @And("I login to my account")
    public void iLoginToMyAccount() {
    }

    @Then("I successfully registered for the subscription")
    public void iSuccessfullyRegisteredForTheSubscription() {
    }
}
