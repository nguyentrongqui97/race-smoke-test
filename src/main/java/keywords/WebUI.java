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

    public static boolean isElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            System.out.println("Element is visible: " + by);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Element is NOT visible: " + by);
            return false;
        }
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
            LogUtils.error("Time out waiting for element visible." + by.toString() + " in " + timeOut + " seconds.");
            Assert.fail("Time out waiting for element visible." + by + " in " + timeOut + " seconds.");
        }
    }

    public static void waitForElementVisibleAndAppearExpectedText(By by, String expectedText) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(EXPLICIT_TIMEOUT),
                    Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.textToBe(by, expectedText));
        } catch (Throwable error) {
            LogUtils.error("Time out waiting for the expected text: " + expectedText);
            Assert.fail("Time out waiting for the expected text: " + expectedText);
        }
    }

    public static void waitForElementVisibleAndAppearExpectedText(By by, String expectedText, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(timeOut),
                    Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.textToBe(by, expectedText));
        } catch (Throwable error) {
            LogUtils.error("Time out waiting for the expected text: " + expectedText);
            Assert.fail("Time out waiting for the expected text: " + expectedText);
        }
    }

    public static void refreshPage() {
        DriverManager.getDriver().navigate().refresh();
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
            Assert.fail("Time out waiting for element visible." + by);
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
            Assert.fail("Time out waiting for element visible." + by);
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
            Assert.fail("Time out waiting for element visible." + by);
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
            LogUtils.error("Time out waiting for element visible." + by.toString() + " in " + timeOut);
            Assert.fail("Time out waiting for element visible." + by + " in " + timeOut);
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
        LogUtils.info("Click element: " + by + " in " + timeOut);
    }

    public static void clickElementWithJS(By by) {
        waitForPageLoaded();
        waitForElementClickable(by);
        WebElement element = DriverManager.getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
        LogUtils.info("Click element: " + by);
    }

    public static void clickElementWithJS(By by, int timeOut) {
        waitForPageLoaded();
        waitForElementClickable(by, timeOut);
        WebElement element = DriverManager.getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
        LogUtils.info("Click element: " + by);
    }

    public static void clickElementWithJSWithoutScrolling(By by) {
        waitForPageLoaded();
        waitForElementClickable(by);
        WebElement element = DriverManager.getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
        LogUtils.info("Click element: " + by);
    }

    public static void clickElementWithJSWithoutScrolling(By by, int timeOut) {
        waitForPageLoaded();
        waitForElementClickable(by, timeOut);
        WebElement element = DriverManager.getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
        LogUtils.info("Click element: " + by);
    }

    public static void scrollToElement(By by) {
        waitForPageLoaded();
        WebElement element = DriverManager.getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
        LogUtils.info("Scroll to element: " + by);
    }

    public static void sendText(By by, String value) {
        waitForPageLoaded();
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set text: " + value + " on element " + by);
    }

    public static void sendText(By by, String value, int timeOut) {
        waitForPageLoaded();
        waitForElementVisible(by, timeOut);
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
        LogUtils.info("Get text: " + text + " from element: " + by);
    }

    public static String getTextAndReturn(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        String text = getWebElement(by).getText();
        LogUtils.info("Get text: " + text + " from element: " + by);
        return text;
    }

    public static String getTextFromListByIndex(By by, int index) {
        waitForPageLoaded();
        waitForElementVisible(by);
        List<WebElement> listItems = DriverManager.getDriver().findElements(by);

        if (!listItems.isEmpty() && index < listItems.size()) {
            String text = listItems.get(index).getText();
            LogUtils.info("Get text: " + text + " from element: " + by + " at index: " + index);
            return text;
        } else {
            LogUtils.error("No element found at index " + index + " for: " + by);
            return "";
        }
    }

    public static String getTextFromListByIndex(By by, int index, int timeOut) {
        waitForPageLoaded();
        waitForElementVisible(by, timeOut);
        List<WebElement> listItems = DriverManager.getDriver().findElements(by);

        if (!listItems.isEmpty() && index < listItems.size()) {
            String text = listItems.get(index).getText();
            LogUtils.info("Get text: " + text + " from element: " + by + " at index: " + index);
            return text;
        } else {
            LogUtils.error("No element found at index " + index + " for: " + by);
            return "";
        }
    }

    public static void getTextInAttribute(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        String text = getWebElement(by).getAttribute("value");
        LogUtils.info("Get text: " + text + " from element: " + by);
    }

    public static void getCurrentURL(String expectedUrl) {
        waitForPageLoaded();
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, currentUrl, "Current  found is not as expected.");
    }

    public static void assertTextEqual(By by, String expectedText) {
        LogUtils.info("Asserting " + by + " having expected text: " + expectedText);
        waitForPageLoaded();
        waitForElementVisible(by);
        String actualText = getWebElement(by).getText().trim();
        Assert.assertEquals(actualText, expectedText, "Text does not match for locator: " + by.toString());
    }

    public static void waitForExpectedTextThenAssertActualTextEqual(By by, String expectedText) {
        LogUtils.info("Asserting " + by + " having expected text: " + expectedText);
        waitForPageLoaded();
        waitForElementVisibleAndAppearExpectedText(by, expectedText);
        String actualText = getWebElement(by).getText().trim();
        Assert.assertEquals(actualText, expectedText, "Text does not match. Actual text: " + actualText + " - Expected text: " + expectedText);
    }

    public static void waitForExpectedTextThenAssertActualTextEqual(By by, String expectedText, int timeOut) {
        LogUtils.info("Asserting " + by + " having expected text: " + expectedText);
        waitForPageLoaded();
        waitForElementVisibleAndAppearExpectedText(by, expectedText, timeOut);
        String actualText = getWebElement(by).getText().trim();
        Assert.assertEquals(actualText, expectedText, "Text does not match. Actual text: " + actualText + " - Expected text: " + expectedText);
    }

    public static void assertTextEqual(By by, String expectedText, int timeOut) {
        LogUtils.info("Asserting " + by + " having expected text: " + expectedText);
        waitForPageLoaded();
        waitForElementVisible(by, timeOut);
        String actualText = getWebElement(by).getText().trim();
        Assert.assertEquals(actualText, expectedText, "Text does not match for locator: " + by.toString());
    }

    public static void assertUrlEqual(String expectedUrl) {
        waitForPageLoaded();
        String actualUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Current URL: " + actualUrl + "does not match the expected URL: " + expectedUrl);
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void selectARandomOptionFromDropDown(By dropdownLocator, By optionsLocator) {
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
        LogUtils.info("Click element: " + by);
    }

    public static void clickCheckBox(By by, int timeOut) {
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
        LogUtils.info("Click element: " + by);
    }

    public static void clickFirstListItem(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        List<WebElement> listItems = DriverManager.getDriver().findElements(by);

        if (!listItems.isEmpty()) {
            listItems.get(0).click();
            LogUtils.info("Click element: " + by);
        } else {
            LogUtils.error("No item of the list found. " + by);
        }
    }

    public static void clickFirstListItem(By by, int timeOut) {
        waitForPageLoaded();
        waitForElementVisible(by, timeOut);
        List<WebElement> listItems = DriverManager.getDriver().findElements(by);

        if (!listItems.isEmpty()) {
            listItems.get(0).click();
            LogUtils.info("Click element: " + by);
        } else {
            LogUtils.error("No item of the list found. " + by);
        }
    }

    public static void locateToAListOfItemsAndClickTheItem(By by, int itemOrder) {
        waitForPageLoaded();
//        waitForElementVisible(by);
        waitForElementClickable(by);
        List<WebElement> listItems = DriverManager.getDriver().findElements(by);

        if (!listItems.isEmpty()) {
            listItems.get(itemOrder).click();
            LogUtils.info("Click element: " + by + " at position: " + itemOrder);
        } else {
            LogUtils.error("No item of the list found. " + by);
        }
    }

    public static void locateToAListOfItemsAndClickTheItem(By by, int itemOrder, int timeOut) {
        waitForPageLoaded();
//        waitForElementVisible(by, timeOut);
        waitForElementClickable(by, timeOut);
        List<WebElement> listItems = DriverManager.getDriver().findElements(by);

        if (!listItems.isEmpty()) {
            listItems.get(itemOrder).click();
            LogUtils.info("Click element: " + by + " at position: " + itemOrder);
        } else {
            LogUtils.error("No item of the list found. " + by);
        }
    }

    public static void assertTextContainsByOrder(By by, int itemOrder, String expectedSubstring) {
        LogUtils.info("Asserting " + by + "at order " + itemOrder + " having expected text: " + expectedSubstring);

        waitForPageLoaded();
        waitForElementVisible(by);
        List<WebElement> elements = DriverManager.getDriver().findElements(by);
        if (itemOrder >= elements.size()) {
            Assert.fail("Invalid item index.");
        }
        String actualText = elements.get(itemOrder).getText().trim();
        Assert.assertTrue(actualText.contains(expectedSubstring),
                "Text does not contain expected substring. Actual: " + actualText + ", Expected to contain: " + expectedSubstring);
    }

    public static void assertTextContainsByOrder(By by, int itemOrder, String expectedSubstring, int timeOut) {
        LogUtils.info("Asserting " + by + "at order " + itemOrder + " having expected text: " + expectedSubstring + " within " + timeOut);
        waitForPageLoaded();
        waitForElementVisible(by, timeOut);
        List<WebElement> elements = DriverManager.getDriver().findElements(by);
        if (itemOrder >= elements.size()) {
            Assert.fail("Invalid item index.");
        }
        String actualText = elements.get(itemOrder).getText().trim();
        Assert.assertTrue(actualText.contains(expectedSubstring),
                "Text does not contain expected substring. Actual: " + actualText + ", Expected to contain: " + expectedSubstring);
    }

    public static boolean isElementDisabled(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        WebElement element = getWebElement(by);
        boolean isDisabled = !element.isEnabled();

        LogUtils.info("Element " + by + " is " + (isDisabled ? "disabled" : "enabled"));
        return isDisabled;
    }

    public static boolean isElementDisabled(By by, int timeOut) {
        waitForPageLoaded();
        waitForElementVisible(by, timeOut);
        WebElement element = getWebElement(by);
        boolean isDisabled = !element.isEnabled();

        LogUtils.info("Element " + by + " is " + (isDisabled ? "disabled" : "enabled"));
        return isDisabled;
    }

    public static boolean isElementDisabledByOrder(By by, int itemOrder) {
        waitForPageLoaded();
        waitForElementVisible(by);
        List<WebElement> elements = DriverManager.getDriver().findElements(by);

        if (elements.isEmpty()) {
            LogUtils.error("No elements found for locator: " + by);
            throw new NoSuchElementException("No elements found for locator: " + by);
        }

        if (itemOrder >= elements.size()) {
            LogUtils.error("Item order " + itemOrder + " is out of bounds for locator: " + by);
            throw new IndexOutOfBoundsException("Item order out of bounds: " + itemOrder);
        }

        WebElement element = elements.get(itemOrder);
        boolean isDisabled = !element.isEnabled();

        LogUtils.info("Element " + by + " at index " + itemOrder + " is " + (isDisabled ? "disabled." : "enabled."));
        return isDisabled;
    }

    public static boolean isElementDisabledByOrder(By by, int itemOrder, int timeOut) {
        waitForPageLoaded();
        waitForElementVisible(by, timeOut);
        List<WebElement> elements = DriverManager.getDriver().findElements(by);

        if (elements.isEmpty()) {
            LogUtils.error("No elements found for locator: " + by);
            throw new NoSuchElementException("No elements found for locator: " + by);
        }

        if (itemOrder >= elements.size()) {
            LogUtils.error("Item order " + itemOrder + " is out of bounds for locator: " + by);
            throw new IndexOutOfBoundsException("Item order out of bounds: " + itemOrder);
        }

        WebElement element = elements.get(itemOrder);
        boolean isDisabled = !element.isEnabled();

        LogUtils.info("Element " + by + " at index " + itemOrder + " is " + (isDisabled ? "disabled." : "enabled.") + " in " + timeOut);
        return isDisabled;
    }

    public static void verifyElementDisable(By by) {
        Assert.assertTrue(isElementDisabled(by), "Element " + by + " is not disabled as expected.");
    }

    public static void verifyElementDisableByOrder(By by, int itemOrder) {
        Assert.assertTrue(isElementDisabledByOrder(by, itemOrder), "Element " + by + " it not disabled as expected.");
    }

}