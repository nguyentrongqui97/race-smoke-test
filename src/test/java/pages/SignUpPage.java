package pages;

import drivers.DriverManager;
import helpers.DataFakerHelpers;
import helpers.MailGeneratorHelpers;
import net.datafaker.Faker;
import org.apache.logging.log4j.core.appender.ScriptAppenderSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import static constants.ConstantGlobal.*;
import static keywords.WebUI.*;

public class SignUpPage extends CommonPage {
    private WebDriver driver;
    String email;

    //Register username and password page
    static By emailTextBox = By.name("email");
    static By passwordTextBox = By.name("password");
    static By confirmPasswordTextBox = By.name("confirmPassword");
    static By getStartedButton = By.cssSelector("button.MuiButton-contained:nth-child(1)");
    static By copyButtonLocator = By.id("copy-button");

    //OTP page
    static By OTPPageTitle = By.cssSelector("span.MuiTypography-gutterBottom");
    static By verifyOTPButton = By.cssSelector("button.MuiButton-contained:nth-child(2)");

    //Personal details page
    static By firstName = By.name("firstName");
    static By lastName = By.name("lastName");
    static By day = By.name("dob.day");
    static By month = By.name("dob.month");
    static By year = By.name("dob.year");
    static By enterAddressManuallyButton = By.cssSelector("button.MuiTypography-root");
    static By addressLine1 = By.name("address.addressLine1");
    static By addressLine2 = By.name("address.addressLine2");
    static By city = By.name("address.town");
    static By country = By.cssSelector("div[role='combobox']");
    static By countryDropdown = By.cssSelector("ul[role='listbox'] li");
    static By postcode = By.name("address.postcode");
    static By phoneNumber = By.name("primaryPhone");
    static By sexAtBirth = By.cssSelector("div.MuiGrid2-grid-xs-5:nth-child(2) > div:nth-child(2) > div:nth-child(1)");
    static By sexAtBirthDropDown = By.cssSelector("ul[role='listbox'] li");
    static By genderIdentity = By.cssSelector("div.MuiGrid2-grid-xs-5:nth-child(4) > div:nth-child(2) > div:nth-child(1)");
    static By genderIdentityDropdown = By.cssSelector("ul[role='listbox'] li");
    static By emailFieldLocator = By.id("fe_text");
    static By nextButton = By.cssSelector(".MuiButton-contained");

    //Membership page
    static By homeNation = By.cssSelector(".MuiInputBase-adornedStart");
    static By homeNationDropDown = By.cssSelector("ul[role='listbox'] li");
    static By region = By.cssSelector(".MuiInputBase-adornedStart");
    static By regionDropDown = By.cssSelector("ul[role='listbox'] li");
    static By communityPlan = By.cssSelector("div.MuiPaper-root:nth-child(2) > div:nth-child(1) > div:nth-child(1)");
    static By agreementRadioButton = By.cssSelector("input[type='checkbox']");
    static By creditDebitCardRadioButton = By.cssSelector("input[name='paymentMethod'][value='STRIPE']");
    static By directDebitRadioButton = By.cssSelector("input[name='paymentMethod'][value='GOCARDLESS']");

    public void completeRegisterForANewAccount() throws InterruptedException, IOException, UnsupportedFlavorException {
        MailGeneratorHelpers helper = new MailGeneratorHelpers(DriverManager.getDriver());
        helper.openMailGeneratorTabAndCopyEmail(emailFieldLocator);
        helper.pasteEmailInMainTab(emailTextBox);
        waitForElementVisible(OTPPageTitle);
        sendText(passwordTextBox, PASSWORD);
        sendText(confirmPasswordTextBox, PASSWORD);
        clickElement(getStartedButton);
        helper.getOTPFromMailTabAndCopyToClipboard();
        helper.enterOTPInMainTab();
        clickElement(verifyOTPButton);
    }

    public void completePersonalDetails() throws InterruptedException {

        Faker faker = new Faker();
        Random random = new Random();

        String firstNameData = faker.name().firstName();
        String lastNameData = faker.name().lastName();

        LocalDate startDate = LocalDate.of(1950, 1, 1);
        long daysForParents = ChronoUnit.DAYS.between(startDate, LocalDate.of(2009, 12, 31));
        LocalDate randomDate = startDate.plusDays(random.nextInt((int) daysForParents + 1));
        String dayData = String.valueOf(randomDate.getDayOfMonth());
        String monthData = String.valueOf(randomDate.getMonthValue());
        String yearData = String.valueOf(randomDate.getYear());

        String addressLine1Data = faker.address().streetAddress();
        String addressLine2Data = faker.address().secondaryAddress();

        String cityData = faker.address().cityName();
        String postcodeData = faker.address().postcode();
        String phoneNumberData = faker.numerify("071########");

        //Type data to textboxes
        Thread.sleep(5000);
        waitForElementVisible(firstName);
        sendText(firstName, firstNameData);
        sendText(lastName, lastNameData);
        sendText(day, dayData);
        sendText(month, monthData);
        sendText(year, yearData);
        clickElement(enterAddressManuallyButton);
        sendText(addressLine1, addressLine1Data);
        sendText(addressLine2, addressLine2Data);
        sendText(city, cityData);

        //Randomly select dropdown values
        selectARandomOptionFromDropDown(country, countryDropdown);
        sendText(postcode, postcodeData);
        sendText(phoneNumber, phoneNumberData);
        selectARandomOptionFromDropDown(sexAtBirth, sexAtBirthDropDown);
        selectARandomOptionFromDropDown(genderIdentity, genderIdentityDropdown);
        clickElement(nextButton);

    }

    public void chooseMembership(String membership) throws InterruptedException {
        Thread.sleep(5000);
        waitForElementClickable(homeNation, 5000);
        selectARandomOptionFromDropDown(homeNation, homeNationDropDown);
        selectARandomOptionFromDropDown(region, regionDropDown);
        switch (membership) {
            case "Community":
                clickElement(communityPlan);
                clickCheckBox(agreementRadioButton);
                break;
            case "Active":
                System.out.println("Active");
                break;
            default:
                System.out.println("default");
        }
        clickElement(nextButton, 2000);
    }

}
