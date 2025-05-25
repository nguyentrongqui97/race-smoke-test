package pages;

import drivers.DriverManager;
import helpers.DataFakerHelpers;
import helpers.MailGeneratorHelpers;
import net.datafaker.Faker;
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
    static By emailTextBox = By.name("email");
    static By passwordTextBox = By.name("password");
    static By confirmPasswordTextBox = By.name("confirmPassword");
    static By getStartedButton = By.cssSelector("button.MuiButton-contained:nth-child(1)");
    static By copyButtonLocator = By.id("copy-button");
    static By OTPPageTitle = By.cssSelector("span.MuiTypography-gutterBottom");
    static By OTPboxes = By.cssSelector("#\\:r5\\:");
    static By verifyOTPButton = By.cssSelector("button.MuiButton-contained:nth-child(2)");
    static By firstName = By.name("firstName");
    static By lastName = By.name("lastName");
    static By day = By.name("dob.day");
    static By month = By.name("dob.month");
    static By year = By.name("dob.year");
    static By enterAddressManuallyButton = By.cssSelector("button.MuiTypography-root");
    static By addressLine1 = By.name("address.addressLine1");
    static By addressLine2 = By.name("address.addressLine2");
    static By city = By.name("address.town");
    static By country = By.cssSelector("div.MuiGrid2-grid-xs-12:nth-child(5) > div:nth-child(2) > div:nth-child(1)");
    static By countryDropdownValues = By.cssSelector("#\\:rk\\:");
    static By postcode = By.name("address.postcode");
    static By phoneNumber = By.name("primaryPhone");
    static By sexAtBirth = By.cssSelector("#\\:rc\\:");
    static By genderIdentity = By.cssSelector("#\\:re\\:");
    static By emailFieldLocator = By.id("fe_text");



    public void completeRegisterForANewAccount() throws InterruptedException, IOException, UnsupportedFlavorException {
        MailGeneratorHelpers helper = new MailGeneratorHelpers(DriverManager.getDriver());
        helper.openMailGeneratorTabAndCopyEmail(emailFieldLocator);
        helper.pasteEmailInMainTab(emailTextBox);
        waitForElementVisible(OTPPageTitle);
        sendText(passwordTextBox, PASSWORD);
        sendText(confirmPasswordTextBox, PASSWORD);
        clickElement(getStartedButton);
        helper.getOTPFromMailTab();


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

        String addressLine1Data = faker.address().fullAddress();
        String addressLine2Data = faker.address().secondaryAddress();
        String cityData = faker.address().cityName();
        String postcodeData = faker.address().postcode();
        String phoneNumberData = faker.phoneNumber().phoneNumber();

        //Type data to textboxes
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
        sendText(postcode, postcodeData);
        sendText(phoneNumber, phoneNumberData);

        //Randomly select dropdown values
        clickElement(country);
        List<WebElement> options = driver.findElements(countryDropdownValues);
        int randomIndex = random.nextInt(3); // assuming exactly 3 options
        options.get(randomIndex).click();


    }
}
