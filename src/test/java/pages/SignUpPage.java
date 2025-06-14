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
import utils.DataGenerateUtils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import static constants.ConstantGlobal.*;
import static constants.ConstantSystemValues.*;
import static keywords.WebUI.*;

public class SignUpPage extends CommonPage {
    private WebDriver driver;
    private String firstNameData;
    private String lastNameData;
    private String addressLine1Data;
    private String addressLine2Data;
    private String postcodeData;
    private String phoneNumberData;
    private String cityData;
    private String paymentEmailData;
    private String dayData;
    private String monthData;
    private String yearData;

    //Register username and password page
    static By emailTextBox = By.name("email");
    static By passwordTextBox = By.name("password");
    static By confirmPasswordTextBox = By.name("confirmPassword");
    static By getStartedButton = By.cssSelector("button.MuiButton-contained:nth-child(1)");

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

    //Direct Debit
    static By goCardLessPaymentForm = By.cssSelector(".css-111f2z0");
    static By goCardlessFirstname = By.id("given_name");
    static By goCardlessLastname = By.id("family_name");
    static By goCardlessEmail = By.id("email");
    static By goCardLessEnterAddressManuallyButton = By.xpath("//button[.//span[text()='or click here to enter your address manually']]");
    static By goCardlessBillingAddressLine1 = By.id("address_line1");
    static By goCardlessCity = By.id("city");
    static By goCardLessPostcode = By.id("postal_code");
    static By goCardlessContinueButton = By.xpath("//button[.//span[text()='Continue']]");
    static By goCardlessSelectBankForm = By.cssSelector(".css-2lftss > div:nth-child(1)");
    static By goCardlessSelectBankSuccessOption = By.xpath("//*[contains(text(), 'Read Refund Account Bank - Automatically authorises the payment request, and returns payment with payer account details')]");
    static By goCardlessSetUpThisDirectDebitButton = By.xpath("//*[text()='Set up this Direct Debit']");
    static By goCardlessContinueToManualWebLoginButton = By.xpath("//*[text()='Continue to manual web login']");
    static By goCardlessConfirmAndContinueButton = By.xpath("//*[text()='Confirm and continue']");


    //After payment
    static By paymentSuccessStatus = By.cssSelector("h4.MuiTypography-root");
    static By skipButton = By.cssSelector(".MuiButton-outlined");

    public void completeRegisterForANewAccount() throws InterruptedException {
        MailGeneratorHelpers helper = new MailGeneratorHelpers(DriverManager.getDriver());
        helper.openMailGeneratorTabAndSaveEmail(emailFieldLocator);
        helper.pasteEmailInMainTab(emailTextBox);
        waitForElementVisible(OTPPageTitle);
        sendText(passwordTextBox, PASSWORD);
        sendText(confirmPasswordTextBox, PASSWORD);
        clickElement(getStartedButton);
        helper.getOTPFromMailTabAndSaveToFile();
        helper.enterOTPFromFileInMainTab();
        clickElement(verifyOTPButton);
    }

    public void completePersonalDetails() throws InterruptedException {


        firstNameData = DataGenerateUtils.generateFullNameData();
        lastNameData = DataGenerateUtils.generateLastNameData();
        addressLine1Data = DataGenerateUtils.generateAddressLine1Data();
        addressLine2Data = DataGenerateUtils.generateAddressLine2Data();
        postcodeData = DataGenerateUtils.generatePostCodeData();
        phoneNumberData = DataGenerateUtils.generatePhoneNumberData();
        cityData = DataGenerateUtils.generateCityData();
        dayData = DataGenerateUtils.generateDayData();
        monthData = DataGenerateUtils.generateMonthData();
        yearData = DataGenerateUtils.generateYearData();

        //Type data to textboxes
        sendText(firstName, firstNameData, 60);
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

    public void chooseMembership(String membership, String paymentMethod) throws InterruptedException {

        paymentEmailData = DataGenerateUtils.generateEmailAddressData();
        postcodeData = DataGenerateUtils.generatePostCodeData();

        Thread.sleep(5000);
        waitForElementClickable(homeNation, 5000);
        selectARandomOptionFromDropDown(homeNation, homeNationDropDown);
        selectARandomOptionFromDropDown(region, regionDropDown);
        switch (membership) {
            case "Community":
                clickElement(communityPlan);
                clickCheckBox(agreementRadioButton, 20);
                clickElement(nextButton, 2000);
                break;
            case "Active":
                clickElement(activePlan);
                break;
            case "Racer":
                clickElement(racerPlan);
                break;
            case "Ultimate Racer":
                clickElement(ultimateRacerPlan);
                break;
        }

        switch (paymentMethod) {
            case "Credit-Debit":
                clickCheckBox(creditDebitCardRadioButton);
                clickCheckBox(agreementRadioButton, 20);
                clickElement(nextContinueToPaymentButton);

                waitForElementVisible(stripePaymentForm, 8000);
                sendText(stripeEmail, paymentEmailData);
                sendText(stripeCardNumber, STRIPE_CARD_NUMBER);
                sendText(stripeCardExpiry, STRIPE_CARD_EXPIRY);
                sendText(stripeCardCvc, STRIPE_CARD_CVC);
                sendText(stripeCardHolderName, firstNameData + lastNameData);
                clickElementWithJS(stripePayButton);
                break;
            case "Direct Debit":
                clickCheckBox(directDebitRadioButton);
                clickCheckBox(agreementRadioButton, 20);
                clickElement(nextContinueToPaymentButton);

                waitForElementVisible(goCardLessPaymentForm, 60);
                sendText(goCardlessFirstname, firstNameData);
                sendText(goCardlessLastname, lastNameData);
                sendText(goCardlessEmail, paymentEmailData);
                clickElement(goCardLessEnterAddressManuallyButton);
                sendText(goCardlessBillingAddressLine1, addressLine1Data);
                sendText(goCardlessCity, cityData);
                sendText(goCardLessPostcode, postcodeData);
                clickElement(goCardlessContinueButton);
                clickElementWithJS(goCardlessSelectBankSuccessOption);
                clickElementWithJS(goCardlessSetUpThisDirectDebitButton);
                clickElementWithJS(goCardlessContinueToManualWebLoginButton);
                break;
        }
    }

    public void successfullySignUpForANewAccount(String membership) {
        waitForTextVisible(paymentSuccessStatus, 60);
        assertTextEqual(paymentSuccessStatus, "Payment successful");
        clickElement(nextButton);
        clickElement(skipButton);
    }

    private void handleCreditDebitPayment() {
        paymentEmailData = DataGenerateUtils.generateEmailAddressData();

        waitForElementVisible(stripePaymentForm, 80);
        sendText(stripeEmail, paymentEmailData);
        sendText(stripeCardNumber, STRIPE_CARD_NUMBER);
        sendText(stripeCardExpiry, STRIPE_CARD_EXPIRY);
        sendText(stripeCardCvc, STRIPE_CARD_CVC);
        sendText(stripeCardHolderName, firstNameData + lastNameData);
        clickElementWithJS(stripePayButton);
    }

    private void handleDirectDebitPayment() {

        paymentEmailData = DataGenerateUtils.generateEmailAddressData();
        postcodeData = DataGenerateUtils.generatePostCodeData();

        waitForElementVisible(goCardLessPaymentForm, 80);
        sendText(goCardlessFirstname, firstNameData);
        sendText(goCardlessLastname, lastNameData);
        sendText(goCardlessEmail, paymentEmailData);

        if (isElementVisible(goCardLessEnterAddressManuallyButton)) {
            clickElement(goCardLessEnterAddressManuallyButton);
            sendText(goCardlessBillingAddressLine1, addressLine1Data);
            sendText(goCardlessCity, cityData);
            sendText(goCardLessPostcode, postcodeData);
        }

        clickElement(goCardlessContinueButton);
        clickElementWithJS(goCardlessSelectBankSuccessOption);

        if (isElementVisible(goCardlessSetUpThisDirectDebitButton)) {
            clickElementWithJS(goCardlessSetUpThisDirectDebitButton);
        }

        if (isElementVisible(goCardlessConfirmAndContinueButton)) {
            clickElementWithJS(goCardlessConfirmAndContinueButton);
        }
        clickElementWithJS(goCardlessContinueToManualWebLoginButton);
    }

    public void makePayment(String paymentMethod) {
        switch (paymentMethod) {
            case "Credit-Debit":
                handleCreditDebitPayment();
                break;
            case "Direct Debit":
                handleDirectDebitPayment();
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }

    public String returnFirstName() {
        return firstNameData;
    }

    public String returnLastName() {
        return lastNameData;
    }


}