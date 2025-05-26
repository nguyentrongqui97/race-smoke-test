package pages;

import constants.ConstantGlobal;
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
    private String firstNameData;
    private String lastNameData;
    Faker faker = new Faker();


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
    static By nextContinueToPaymentButton = By.cssSelector(".MuiButton-containedSecondary");

    //Membership page
    static By homeNation = By.cssSelector(".MuiInputBase-adornedStart");
    static By homeNationDropDown = By.cssSelector("ul[role='listbox'] li");
    static By region = By.cssSelector(".MuiInputBase-adornedStart");
    static By regionDropDown = By.cssSelector("ul[role='listbox'] li");
    static By communityPlan = By.cssSelector("div.MuiPaper-root:nth-child(2) > div:nth-child(1) > div:nth-child(1)");
    static By activePlan = By.cssSelector("div.MuiPaper-root:nth-child(3) > div:nth-child(1) > div:nth-child(1)");
    static By racerPlan = By.cssSelector("div.MuiPaper-root:nth-child(4) > div:nth-child(1) > div:nth-child(1)");
    static By ultimateRacerPlan = By.cssSelector("div.MuiPaper-root:nth-child(5) > div:nth-child(1) > div:nth-child(1)");
    static By agreementRadioButton = By.cssSelector("input[type='checkbox']");
    static By creditDebitCardRadioButton = By.cssSelector("input[name='paymentMethod'][value='STRIPE']");
    static By directDebitRadioButton = By.cssSelector("input[name='paymentMethod'][value='GOCARDLESS']");

    //Stripe
    static By stripePaymentForm = By.cssSelector(".CheckoutPaymentForm");
    static By stripeEmail = By.id("email");
    static By stripeCardNumber = By.id("cardNumber");
    static By stripeCardExpiry = By.id("cardExpiry");
    static By stripeCardCvc = By.id("cardCvc");
    static By stripeCardHolderName = By.id("billingName");
    static By stripePayButton = By.xpath("//span[text()='Pay']");


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

        Random random = new Random();

        firstNameData = faker.name().firstName();
        lastNameData = faker.name().lastName();

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
        String stripeEmailData = faker.internet().emailAddress();
        waitForElementClickable(homeNation, 5000);
        selectARandomOptionFromDropDown(homeNation, homeNationDropDown);
        selectARandomOptionFromDropDown(region, regionDropDown);
        switch (membership) {
            case "Community":
                clickElement(communityPlan);
                clickCheckBox(agreementRadioButton);
                clickElement(nextButton, 2000);
                break;
            case "Active":
                clickElement(activePlan);
                clickCheckBox(creditDebitCardRadioButton);
                clickCheckBox(agreementRadioButton);
                clickElement(nextContinueToPaymentButton);

                //Stripe
                waitForElementVisible(stripePaymentForm, 8000);
                sendText(stripeEmail, stripeEmailData);
                sendText(stripeCardNumber, STRIPE_CARD_NUMBER);
                sendText(stripeCardExpiry, STRIPE_CARD_EXPIRY);
                sendText(stripeCardCvc, STRIPE_CARD_CVC);
                sendText(stripeCardHolderName, firstNameData + lastNameData);
                waitForElementClickable(stripePayButton);
                clickCheckBox(stripePayButton);
                break;
            case "Racer":
                clickElement(racerPlan);
                clickCheckBox(creditDebitCardRadioButton);
                clickCheckBox(agreementRadioButton);
                clickElement(nextContinueToPaymentButton);

                //Stripe
                waitForElementVisible(stripePaymentForm, 8000);
                sendText(stripeEmail, stripeEmailData);
                sendText(stripeCardNumber, STRIPE_CARD_NUMBER);
                sendText(stripeCardExpiry, STRIPE_CARD_EXPIRY);
                sendText(stripeCardCvc, STRIPE_CARD_CVC);
                sendText(stripeCardHolderName, firstNameData + lastNameData);
                waitForElementClickable(stripePayButton);
                clickCheckBox(stripePayButton);
            case "Ultimate Raver":
                clickElement(ultimateRacerPlan);
                clickCheckBox(creditDebitCardRadioButton);
                clickCheckBox(agreementRadioButton);
                clickElement(nextContinueToPaymentButton);

                //Stripe
                waitForElementVisible(stripePaymentForm, 8000);
                sendText(stripeEmail, stripeEmailData);
                sendText(stripeCardNumber, STRIPE_CARD_NUMBER);
                sendText(stripeCardExpiry, STRIPE_CARD_EXPIRY);
                sendText(stripeCardCvc, STRIPE_CARD_CVC);
                sendText(stripeCardHolderName, firstNameData + lastNameData);
                waitForElementClickable(stripePayButton);
                clickCheckBox(stripePayButton);
            default:
                System.out.println("default");
        }

    }

}
