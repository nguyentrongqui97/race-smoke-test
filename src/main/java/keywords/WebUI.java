package keywords;

import constants.ConstantGlobal;
import drivers.DriverManager;
import helpers.PropertiesHelpers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LogUtils;
import org.openqa.selenium.By;


import java.time.Duration;

public class WebUI {

    static {
        PropertiesHelpers.loadAllFiles();
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(ConstantGlobal.PAGE_LOAD_TIMEOUT),
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
                Assert.fail("Timeout waiting for the page loaded (JavaScript). (" + ConstantGlobal.PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    public static void waitForAngularLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConstantGlobal.PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
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
                Assert.fail("Timeout waiting for Angular load. (" + ConstantGlobal.PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConstantGlobal.PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
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
                Assert.fail("Timeout waiting for JQuery load. (" + ConstantGlobal.PAGE_LOAD_TIMEOUT + "s)");
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




}
