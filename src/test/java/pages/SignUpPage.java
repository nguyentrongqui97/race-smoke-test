package pages;

import drivers.DriverManager;
import helpers.MailGeneratorHelpers;
import org.openqa.selenium.By;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static constants.ConstantGlobal.*;
import static keywords.WebUI.*;

public class SignUpPage extends CommonPage {
    static By emailTextBox = By.name("email");
    static By passwordTextBox = By.name("password");
    static By confirmPasswordTextBox = By.name("confirmPassword");
    static By getStartedButton = By.cssSelector("button.MuiButton-contained:nth-child(1)");
    static By copyButtonLocator = By.id("copy-button");
    static By OTPPageTitle = By.cssSelector("span.MuiTypography-gutterBottom");
    static By firstName = By.name("firstName");
    static By lastName = By.name("lastName");
    static By day = By.name("dob.day");
    static By month = By.name("dob.month");
    static By year = By.name("dob.year");
    static By enterAddressManuallyButton = By.cssSelector("button.MuiTypography-root");
    static By addresLine1 = By.name("address.addressLine1");
    static By city = By.name("address.town");
    static By country = By.cssSelector("div.MuiGrid2-grid-xs-12:nth-child(5) > div:nth-child(2) > div:nth-child(1)");
    static By postcode = By.name("address.postcode");
    static By phoneNumber = By.name("primaryPhone");
    static By sexAtBirth = By.cssSelector("#\\:rc\\:");
    static By genderIdentity = By.cssSelector("#\\:re\\:");


    public void completeRegisterForANewAccount() throws InterruptedException, IOException, UnsupportedFlavorException {
        MailGeneratorHelpers helper = new MailGeneratorHelpers(DriverManager.getDriver());
        helper.openMailGeneratorTabAndCopyEmail(copyButtonLocator);
        helper.pasteEmailInMainTab(emailTextBox);
        waitForElementVisible(OTPPageTitle);
        sendText(passwordTextBox, PASSWORD);
        sendText(confirmPasswordTextBox, PASSWORD);
        clickElement(getStartedButton);
        helper.getOTPFromMailTab();
    }

    public void completePersonalDetails() {

    }
}
