package pages;

import constants.ConstantGlobal;
import static keywords.WebUI.*;
import org.openqa.selenium.By;

public class LoginPage extends CommonPage {

    static By username = By.cssSelector("#username");
    static By password = By.cssSelector("#password");
    private By loginButton = By.cssSelector("button.MuiButtonBase-root:nth-child(8)");
    private By signInButton = By.cssSelector("button.MuiButton-contained:nth-child(3)");



    public void goToLoginPage(){
        openURL(ConstantGlobal.URL);
        clickElement(loginButton);
    }

    public void typeUserName(String userName) {
        sendText(username, userName);
    }

    public void typePassword(String passwordInput) {
        sendText(password, passwordInput);
    }

    public void clickSignInButton(){
        clickElement(signInButton);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

}
