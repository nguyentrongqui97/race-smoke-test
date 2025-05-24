package helpers;

import constants.ConstantGlobal;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtils;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.awt.datatransfer.DataFlavor;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static keywords.WebUI.*;

public class MailGeneratorHelpers {
    private WebDriver driver;
    private String mainTabHandle;
    private By consentButton = By.cssSelector("button.fc-cta-consent");

    public MailGeneratorHelpers(WebDriver driver) {
        this.driver = driver;
        this.mainTabHandle = driver.getWindowHandle();
    }

    public void openMailGeneratorTabAndCopyEmail(By copyButtonLocator) throws IOException, UnsupportedFlavorException {
        ((JavascriptExecutor) driver).executeScript("window.open('https://10minutemail.net/', '_blank');");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        clickElement(consentButton);
        clickElement(copyButtonLocator);

        String email = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        System.out.println("EMAIL: " + email);

    }

    public void pasteEmailInMainTab(By inputEmailLocator) throws InterruptedException {
        driver.switchTo().window(mainTabHandle);
        Thread.sleep(5000);
        try {
            String email = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            System.out.println("Email: " + email + " sent to " + inputEmailLocator);
            waitForElementVisible(inputEmailLocator);
            driver.findElement(inputEmailLocator).sendKeys(email);
        } catch (Exception e) {
            LogUtils.error("Cannot paste email to main tab " + e);
        }
    }

    public String getOTPFromMailTab() {
        // Switch to the mail tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        long startTime = System.currentTimeMillis();

        String targetSender = "no-reply@verificationemail.com";
        String otp = null;

        while (System.currentTimeMillis() - startTime < 120000) {
            try {
                List<WebElement> rows = driver.findElements(By.cssSelector("#maillist tr"));

                for (WebElement row : rows) {
                    if (row.getText().contains(targetSender)) {
                        // Click on the row (first column has the sender)
                        row.findElement(By.cssSelector("a.row-link")).click();

                        // Wait for email body to load and extract OTP
                        WebElement otpParagraph = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(By.cssSelector("div.mailinhtml:nth-child(1) > p:nth-child(6)")));

                        String text = otpParagraph.getText();
                        Matcher matcher = Pattern.compile("\\b\\d{6}\\b").matcher(text);

                        if (matcher.find()) {
                            otp = matcher.group();
                        }
                        return otp; // Done
                    }
                }

                driver.navigate().refresh();
                Thread.sleep(5000);

            } catch (Exception e) {
                driver.navigate().refresh();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                }
            }
        }

        throw new RuntimeException("OTP email did not arrive within 2 minutes.");
    }
}
