package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileManagerUtils;
import utils.LogUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.time.Duration;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static constants.ConstantGlobal.*;
import static keywords.WebUI.*;

public class MailGeneratorHelpers {
    private WebDriver driver;
    private String mainTabHandle;
    private By consentButton = By.cssSelector("button.fc-cta-consent");
    private String emailAddress;

    static By otpEmail = By.cssSelector("#maillist tr");
    static By otpCode = By.xpath("//p[contains(text(), 'Your verification code is:')]/following-sibling::p[1]");

    public MailGeneratorHelpers(WebDriver driver) {
        this.driver = driver;
        this.mainTabHandle = driver.getWindowHandle();
    }

    public void openMailGeneratorTabAndCopyEmail(By emailFieldLocator) {
        ((JavascriptExecutor) driver).executeScript("window.open('https://10minutemail.net/', '_blank');");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        clickElement(consentButton);

        WebElement emailField = driver.findElement(By.id("fe_text"));
        emailAddress = emailField.getAttribute("value");
        LogUtils.info("EMAIL: " + emailAddress);

        StringSelection selection = new StringSelection(emailAddress);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    public void pasteEmailInMainTab(By inputEmailLocator) throws InterruptedException {
        driver.switchTo().window(mainTabHandle);
        Thread.sleep(5000);

        try {
            LogUtils.info("EMAIL: " + emailAddress + " sent to " + inputEmailLocator);
            waitForElementVisible(inputEmailLocator);
            driver.findElement(inputEmailLocator).sendKeys(emailAddress);
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

        while (System.currentTimeMillis() - startTime < 180000) {
            try {
                List<WebElement> rows = driver.findElements(otpEmail);

                for (WebElement row : rows) {
                    if (row.getText().contains(targetSender)) {
                        // Click on the row (first column has the sender)
                        row.findElement(By.cssSelector("a.row-link")).click();

                        // Wait for email body to load and extract OTP
                        WebElement otpParagraph = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(otpCode));

                        String text = otpParagraph.getText();
                        Matcher matcher = Pattern.compile("\\b\\d{6}\\b").matcher(text);

                        if (matcher.find()) {
                            otp = matcher.group();
                        }
                        return otp;
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

    public void enterOTPInMainTab(String otp) throws InterruptedException {
        driver.switchTo().window(mainTabHandle);
        Thread.sleep(2000);

        try {
            List<WebElement> otpInputs = driver.findElements(By.cssSelector("input.MuiInputBase-input.MuiOutlinedInput-input.css-1o64soc"));

            if (otp.length() != 6 || otpInputs.size() < 6) {
                throw new IllegalStateException("Mismatch between OTP length and input fields.");
            }

            // Click first input box to focus (optional)
            otpInputs.get(0).click();

            for (int i = 0; i < 6; i++) {
                WebElement inputBox = otpInputs.get(i);
                inputBox.clear();
                inputBox.sendKeys(Character.toString(otp.charAt(i)));
                Thread.sleep(100);
            }

            System.out.println("OTP entered successfully: " + otp);

        } catch (Exception e) {
            LogUtils.error("Failed to enter OTP: " + e.getMessage());
        }
    }

    public void getOTPFromMailTabAndCopyToClipboard() {
        // Switch to the mail tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        long startTime = System.currentTimeMillis();

        String targetSender = "no-reply@verificationemail.com";

        while (System.currentTimeMillis() - startTime < 300000) {
            try {
                List<WebElement> rows = driver.findElements(By.cssSelector("#maillist tr"));

                for (WebElement row : rows) {
                    if (row.getText().contains(targetSender)) {
                        row.findElement(By.cssSelector("a.row-link")).click();

                        WebElement otpParagraph = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(By.cssSelector("div.mailinhtml:nth-child(1) > p:nth-child(6)")));

                        String text = otpParagraph.getText();
                        Matcher matcher = Pattern.compile("\\b\\d{6}\\b").matcher(text);

                        if (matcher.find()) {
                            String otp = matcher.group();

                            // Copy OTP to clipboard (inlined)
                            StringSelection stringSelection = new StringSelection(otp);
                            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

                            return;
                        }
                    }
                }

                driver.navigate().refresh();
                Thread.sleep(5000);

            } catch (Exception e) {
                driver.navigate().refresh();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                    // Ignore
                }
            }
        }

        throw new RuntimeException("OTP email did not arrive within 2 minutes.");
    }

    public void enterOTPInMainTab() {
        // Switch back to the main/original tab (assumed to be first)
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        try {
            // Retrieve OTP from clipboard
            String otp = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .getData(DataFlavor.stringFlavor);

            if (otp == null || otp.length() != 6 || !otp.matches("\\d{6}")) {
                throw new RuntimeException("Invalid OTP retrieved from clipboard: " + otp);
            }

            // Find all OTP input boxes using class selector
            List<WebElement> otpInputs = driver.findElements(By.cssSelector("input.MuiInputBase-input.MuiOutlinedInput-input.css-1o64soc"));

            if (otpInputs.size() != 6) {
                throw new RuntimeException("Expected 6 OTP input boxes, but found " + otpInputs.size());
            }

            // Enter each digit into the corresponding box
            for (int i = 0; i < 6; i++) {
                otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve OTP from clipboard or input it: " + e.getMessage(), e);
        }
    }

    public void openMailGeneratorTabAndSaveEmail(By emailFieldLocator) {
        ((JavascriptExecutor) driver).executeScript("window.open('https://10minutemail.net/', '_blank');");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        clickElement(consentButton);

        WebElement emailField = driver.findElement(By.id("fe_text"));
        emailAddress = emailField.getAttribute("value");
        LogUtils.info("EMAIL: " + emailAddress);

        // Save email to file
        String filePath = FileManagerUtils.getFilePath("email", ".txt");
        FileManagerUtils.write(filePath, emailAddress);
    }

    public void getOTPFromMailTabAndSaveToFile() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 300000) {
            try {
                List<WebElement> rows = driver.findElements(otpEmail);

                for (WebElement row : rows) {
                    if (row.getText().contains(EMAIL_TARGET_SENDER)) {
                        row.findElement(By.cssSelector("a.row-link")).click();

                        WebElement otpParagraph = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(otpCode));

                        String text = otpParagraph.getText();
                        Matcher matcher = Pattern.compile("\\b\\d{6}\\b").matcher(text);

                        if (matcher.find()) {
                            String otp = matcher.group();

                            // Save OTP to file
                            String filePath = FileManagerUtils.getFilePath("otp", ".txt");
                            FileManagerUtils.write(filePath, otp);
                            return;
                        }
                    }
                }

                driver.navigate().refresh();
                Thread.sleep(5000);

            } catch (Exception e) {
                driver.navigate().refresh();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {}
            }
        }

        throw new RuntimeException("OTP email did not arrive within 5 minutes.");
    }

    public void enterOTPFromFileInMainTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        try {
            String filePath = FileManagerUtils.getFilePath("otp", ".txt");
            String otp = FileManagerUtils.read(filePath);

            if (otp == null || otp.length() != 6 || !otp.matches("\\d{6}")) {
                throw new RuntimeException("Invalid OTP retrieved from file: " + otp);
            }

            List<WebElement> otpInputs = driver.findElements(By.cssSelector("input.MuiInputBase-input.MuiOutlinedInput-input.css-1o64soc"));

            if (otpInputs.size() != 6) {
                throw new RuntimeException("Expected 6 OTP input boxes, but found " + otpInputs.size());
            }

            for (int i = 0; i < 6; i++) {
                otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve OTP from file or input it: " + e.getMessage(), e);
        }
    }


}
