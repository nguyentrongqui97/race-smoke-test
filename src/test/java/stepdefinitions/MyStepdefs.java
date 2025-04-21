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
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.LogUtils;

import java.sql.Driver;

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
        DriverManager.getDriver().get(PropertiesHelpers.getValue("URLtest"));
        WebUI.sendText(By.cssSelector(".ui-autocomplete-input"), "hehehehehe", Keys.ENTER);

        WebUI.getCurrentURL(PropertiesHelpers.getValue("URL"));

        BaseTest.closeDriver();
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
