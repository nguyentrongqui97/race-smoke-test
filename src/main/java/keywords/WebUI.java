package keywords;

import constants.ConstantGlobal;
import drivers.DriverManager;
import helpers.PropertiesHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LogUtils;


import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WebUI {

    private final static long EXPLICIT_TIMEOUT = ConstantGlobal.EXPLICIT_TIMEOUT;
    private final static long PAGE_LOAD_TIMEOUT = ConstantGlobal.PAGE_LOAD_TIMEOUT;

    static {
        PropertiesHelpers.loadAllFiles();
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(PAGE_LOAD_TIMEOUT),
                Duration.ofMillis(500));

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //wait for JS to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager
                .getDriver())
                .executeScript("return document.readyState")
                .toString()
                .equals("complete");

        //wait until JS is ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
        if (!jsReady) {
            LogUtils.info("JavaScript is NOT ready!!!");
            try {
                wait.until(jsLoad);
            } catch (Throwable e) {
                e.printStackTrace();
                Assert.fail("Timeout waiting for the page loaded (JavaScript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    public static void waitForAngularLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(PAGE_LOAD_TIMEOUT),
                Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) DriverManager.getDriver()).executeScript(angularReadyScript).toString());
        };

        //Get Angular is Ready
        boolean angularReady = Boolean.parseBoolean(js.executeScript(angularReadyScript).toString());

        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            LogUtils.info("Angular is NOT Ready!");
            //Wait for Angular to load
            try {
                //Wait for jQuery to load
                wait.until(angularLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for Angular load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(PAGE_LOAD_TIMEOUT),
                Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            LogUtils.info("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    public static void verifyElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        System.out.println("Verify " + by + " is displayed.");
        Assert.assertEquals(DriverManager.getDriver().findElement(by).isDisplayed(), "Element not visible.");
    }

    public static void verifyElementVisible(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        System.out.println("Verify " + by + " is displayed");
        Assert.assertTrue(DriverManager.getDriver().findElement(by).isDisplayed(), message);
    }


    public static boolean verifyElementVisibleReturnBoolean(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        System.out.println("Verify " + by + " is displayed.");
        Assert.assertEquals(DriverManager.getDriver().findElement(by).isDisplayed(), "Element not visible.");
        return true;
    }

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(EXPLICIT_TIMEOUT),
                    Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Time out waiting for element visible." + by.toString());
            Assert.fail("Time out waiting for element visible." + by);
        }
    }

    //Checks for rendered elements
    public static void waitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(timeOut),
                    Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Time out waiting for element visible." + by.toString());
            Assert.fail("Time out waiting for element visible.");
        }
    }

    public static void waitForElementNotVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(EXPLICIT_TIMEOUT),
                    Duration.ofMillis(500));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Time out waiting for element to become not visible: " + by.toString());
            Assert.fail("Time out waiting for element to become not visible: " + by);
        }
    }

    //Checks for presence in DOM, hidden elements
    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(EXPLICIT_TIMEOUT),
                    Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Time out waiting for element visible." + by.toString());
            Assert.fail("Time out waiting for element visible.");
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(timeOut),
                    Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Time out waiting for element visible." + by.toString());
            Assert.fail("Time out waiting for element visible.");
        }
    }

    public static void waitForTextVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(EXPLICIT_TIMEOUT),
                    Duration.ofMillis(500));

            wait.until(driver -> {
                WebElement element = driver.findElement(by);
                return element.isDisplayed() && !element.getText().trim().isEmpty();
            });

        } catch (Throwable error) {
            LogUtils.error("Time out waiting for text to be visible in element: " + by.toString());
            Assert.fail("Time out waiting for text to be visible in element: " + by);
        }
    }

    public static void waitForTextVisible(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(timeout),
                    Duration.ofMillis(500));

            wait.until(driver -> {
                WebElement element = driver.findElement(by);
                return element.isDisplayed() && !element.getText().trim().isEmpty();
            });

        } catch (Throwable error) {
            LogUtils.error("Time out waiting for text to be visible in element: " + by.toString());
            Assert.fail("Time out waiting for text to be visible in element: " + by);
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(EXPLICIT_TIMEOUT),
                    Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            LogUtils.error("Time out waiting for element visible." + by.toString());
            Assert.fail("Time out waiting for element visible.");
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(timeOut),
                    Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            LogUtils.error("Time out waiting for element visible." + by.toString());
            Assert.fail("Time out waiting for element visible.");
        }
    }



    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        LogUtils.info("Open URL: " + url);
        waitForPageLoaded();
    }

    public static void clickElement(By by) {
        waitForPageLoaded();
        waitForElementClickable(by);
        getWebElement(by).click();
        LogUtils.info("Click element: " + by);
    }

    public static void clickElement(By by, int timeOut) {
        waitForPageLoaded();
        waitForElementClickable(by, timeOut);
        getWebElement(by).click();
        LogUtils.info("Click element: " + by);
    }

    public static void clickElementWithJS(By by) {
        waitForPageLoaded();
        waitForElementClickable(by);
        WebElement element = DriverManager.getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
        LogUtils.info("Click element: " + by);
    }

    public static void clickElementWithJS(By by, int timeOut) {
        waitForPageLoaded();
        waitForElementClickable(by, timeOut);
        WebElement element = DriverManager.getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
        LogUtils.info("Click element: " + by);
    }

    public static void sendText(By by, String value) {
        waitForPageLoaded();
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set text: " + value + " on element " + by);
    }

    public static void sendText(By by, String value, Keys key) {
        waitForPageLoaded();
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value, key);
        LogUtils.info("Set text: " + value + " on element " + by);
    }

    public static void getText(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        String text = getWebElement(by).getText();
        LogUtils.info("Get text: " + text);
    }

    public static void getTextInAttribute(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        String text = getWebElement(by).getAttribute("value");
        LogUtils.info("Get text: " + text);
    }

    public static void getCurrentURL(String expectedUrl) {
        waitForPageLoaded();
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, currentUrl, "Current  found is not as expected.");
    }

    public static void assertTextEqual(By by, String expectedText){
        waitForPageLoaded();
        waitForElementVisible(by);
        String actualText = getWebElement(by).getText().trim();
        Assert.assertEquals(actualText, expectedText, "Text does not match for locator: " + by.toString());
    }

    public static void assertTextEqual(By by, String expectedText, int timeOut){
        waitForPageLoaded();
        waitForElementVisible(by, timeOut);
        String actualText = getWebElement(by).getText().trim();
        Assert.assertEquals(actualText, expectedText, "Text does not match for locator: " + by.toString());
    }

    public static void assertUrlEqual(String expectedUrl){
        waitForPageLoaded();
        String actualUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl,"Current URL: " + actualUrl + "does not match the expected URL: " + expectedUrl);
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void selectARandomOptionFromDropDown(By dropdownLocator, By optionsLocator){
        try {
            WebDriver driver = DriverManager.getDriver();
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(EXPLICIT_TIMEOUT),
                    Duration.ofMillis(500));

            waitForElementClickable(dropdownLocator);
            clickElement(dropdownLocator);

            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));

            // Filter out unselectable options
            List<WebElement> clickableOptions = options.stream()
                    .filter(option -> {
                        String text = option.getText().trim();
                        return !text.equalsIgnoreCase("Select an option") && option.isEnabled();
                    })
                    .collect(Collectors.toList());

            if (clickableOptions.isEmpty()) {
                LogUtils.error("No clickable dropdown options available for locator: " + optionsLocator);
                Assert.fail("No valid dropdown options to select.");
            }

            Random random = new Random();
            WebElement randomOption = clickableOptions.get(random.nextInt(clickableOptions.size()));
            String optionValue = randomOption.getText();
            randomOption.click();
            LogUtils.info("Random dropdown option selected: " + optionValue);

        } catch (Exception e) {
            LogUtils.error("Failed to select a random dropdown option: " + e.getMessage());
            Assert.fail("Error while selecting a random option from the dropdown list.");
        }
    }

    public static void clickCheckBox(By by) {
        waitForPageLoaded();
        WebElement checkbox = getWebElement(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", checkbox);
        try {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        } catch (Throwable error) {
            LogUtils.error("Cannot tick the checkbox: " + by.toString());
            Assert.fail("Cannot tick the checkbox: " + by);
        }
    }


}
