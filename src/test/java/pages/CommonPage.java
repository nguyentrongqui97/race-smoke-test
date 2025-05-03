package pages;

import keywords.WebUI;
import org.openqa.selenium.By;

public class CommonPage {


    private By loginButton = By.cssSelector("button.MuiButtonBase-root:nth-child(8)");
    private By signInButton = By.cssSelector("button.MuiButton-contained:nth-child(3)");

    public void clickLoginButton() {
        WebUI.clickElement(loginButton);
    }

    public void clickSignInButton() {
        WebUI.clickElement(signInButton);
    }




}
