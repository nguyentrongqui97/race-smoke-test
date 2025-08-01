package pages;

import static constants.ConstantGlobal.*;
import static constants.ConstantSystemValues.*;

import org.openqa.selenium.By;


import static keywords.WebUI.*;

public class MembershipManagementPage {
    private final SignUpPage signUpPage;

    public MembershipManagementPage(SignUpPage signUpPage) {
        this.signUpPage = signUpPage;
    }

    static By upgradeMembershipButtonList = By.xpath("//button[normalize-space()='Upgrade membership']");
    static By communityOptionModal = By.cssSelector("div.css-1wicjwk:nth-child(1) > div:nth-child(1) > div:nth-child(6) > button:nth-child(1)");
    static By upgradeToActiveButton = By.cssSelector("div.css-1wicjwk:nth-child(2) > div:nth-child(1) > div:nth-child(6) > button:nth-child(1)");
    static By upgradeToRacerButton = By.cssSelector("div.css-1wicjwk:nth-child(3) > div:nth-child(1) > div:nth-child(6) > button:nth-child(1)");
    static By upgradeToUltimateRacerButton = By.cssSelector("div.css-1wicjwk:nth-child(4) > div:nth-child(1) > div:nth-child(6) > button:nth-child(1)");
    static By currentMembership = By.cssSelector(".css-g2y9sr > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > p:nth-child(2)");
    static By membershipAfterUpgrade = By.cssSelector(".css-ejlojh > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > p:nth-child(2)");
    static By creditDebitCardRadioButton = By.cssSelector("input[name='paymentMethod'][value='STRIPE']");
    static By directDebitRadioButton = By.cssSelector("input[name='paymentMethod'][value='GOCARDLESS']");
    static By confirmUpgradeButton = By.xpath("//button[contains(text(),'Confirm Upgrade')]");
    static By membershipTypeList = By.xpath("//h6[text()='Member type:']/following-sibling::p");
    static By refundMembershipButtonList = By.xpath("//button[normalize-space()='Refund membership']");
    static By confirmRefundButton = By.xpath("//button[contains(text(),'Refund Membership')]");
    static By successStatusMessage = By.cssSelector("div.MuiPaper-root");
    static By manageRenewalButtonList = By.xpath("//button[normalize-space()='Manage renewal']");
    static By manageRenewalMembershipTypeList = By.xpath("//h6[text()='Expiration:']/following::p[contains(text(),'Your membership type will change to')]");
    static By confirmChangeButton = By.xpath("//button[contains(text(),'Confirm change')]");
    static By expirationDateList = By.xpath("//p[contains(text(),'renew')]/b");
    static By closeButtonNotificationBanner = By.cssSelector("button[aria-label='Close']");


    public void upgradeAMembership(String beforeUpgradeMembership, String upgradedMembership, String upgradedPaymentMethod) {
        openURL(URL_MEMBERSHIP_MANAGEMENT);
        locateToAListOfItemsAndClickTheItem(upgradeMembershipButtonList, 0, 20);

        //Loop variables
        By upgradeButton;
        String expectedMembership;

        switch (upgradedMembership) {
            case "Active":
                upgradeButton = upgradeToActiveButton;
                expectedMembership = ACTIVE;
                break;
            case "Racer":
                upgradeButton = upgradeToRacerButton;
                expectedMembership = RACER;
                break;
            case "Ultimate Racer":
                upgradeButton = upgradeToUltimateRacerButton;
                expectedMembership = ULTIMATE_RACER;
                break;
            default:
                throw new IllegalArgumentException("Unsupported membership type: " + upgradedMembership);
        }

        clickElement(upgradeButton, 20);
        assertTextEqual(currentMembership, beforeUpgradeMembership);
        assertTextEqual(membershipAfterUpgrade, expectedMembership);

        if (CREDIT_DEBIT.equalsIgnoreCase(upgradedPaymentMethod)) {
            clickCheckBox(creditDebitCardRadioButton);
        } else if (DIRECT_DEBIT.equalsIgnoreCase(upgradedPaymentMethod) && COMMUNITY.equalsIgnoreCase(beforeUpgradeMembership)) {
            clickCheckBox(directDebitRadioButton);
        }

        clickElementWithJSWithoutScrolling(confirmUpgradeButton, 20);

        signUpPage.makePayment(upgradedPaymentMethod);

    }

    public void successfullyUpgradeAMembership(String membership) {
        String accountMembershipFirstName = signUpPage.returnFirstName();
        String accountMembershipLastName = signUpPage.returnLastName();

        assertTextEqual(successStatusMessage,
                accountMembershipFirstName + " " + accountMembershipLastName + "'s " + SUCCESS_MESSAGE_UPGRADE_MEMBERSHIP + membership,
                20);
        clickElementWithJS(closeButtonNotificationBanner);
        assertTextContainsByOrder(membershipTypeList, 0, membership, 30);
    }

    public void refundAMembership(String upgradedMembership, String refundMembership) {
        refreshPage();
        locateToAListOfItemsAndClickTheItem(refundMembershipButtonList, 0);
        assertTextEqual(currentMembership, upgradedMembership);
        assertTextEqual(membershipAfterUpgrade, refundMembership);

        clickElementWithJSWithoutScrolling(confirmRefundButton, 30);

    }

    public void successfullyRefundAMembership(String membership) {
        String accountMembershipFirstName = signUpPage.returnFirstName();
        String accountMembershipLastName = signUpPage.returnLastName();

        assertTextEqual(successStatusMessage,
                accountMembershipFirstName + " " + accountMembershipLastName + "'s " + SUCCESS_MESSAGE_REFUND_MEMBERSHIP + membership + " membership",
                30);
        clickElementWithJS(closeButtonNotificationBanner);
        assertTextContainsByOrder(membershipTypeList, 0, membership);
    }

    public void manageRenewal(String membership, String membershipManageRenewal) {
        openURL(URL_MEMBERSHIP_MANAGEMENT);
        locateToAListOfItemsAndClickTheItem(manageRenewalButtonList, 0, 30);

        //Loop variables
        By upgradeButton;
        String expectedMembership;

        switch (membershipManageRenewal) {
            case "Community":
                upgradeButton = communityOptionModal;
                expectedMembership = COMMUNITY;
                break;
            case "Active":
                upgradeButton = upgradeToActiveButton;
                expectedMembership = ACTIVE;
                break;
            case "Racer":
                upgradeButton = upgradeToRacerButton;
                expectedMembership = RACER;
                break;
            case "Ultimate Racer":
                upgradeButton = upgradeToUltimateRacerButton;
                expectedMembership = ULTIMATE_RACER;
                break;
            default:
                throw new IllegalArgumentException("Unsupported membership type: " + membershipManageRenewal);
        }

        clickElementWithJSWithoutScrolling(upgradeButton, 30);
        assertTextEqual(currentMembership, membership);
        assertTextEqual(membershipAfterUpgrade, expectedMembership);

        clickElementWithJSWithoutScrolling(confirmChangeButton, 30);

    }

    public void successfullyManageRenewal(String membershipManageRenewal) {
        String accountMembershipFirstName = signUpPage.returnFirstName();
        String accountMembershipLastName = signUpPage.returnLastName();

        String renewalDate = getTextFromListByIndex(expirationDateList, 0);
        String successStatusMessageManageRenewal = accountMembershipFirstName + " " + accountMembershipLastName + "'s " + SUCCESS_MESSAGE_MANAGE_RENEWAL + membershipManageRenewal + " on the " + renewalDate;
        String manageRenewalMembershipTypeMessage = "Your membership type will change to " + membershipManageRenewal + " on this date";

        assertTextEqual(successStatusMessage, successStatusMessageManageRenewal, 30);
        clickElementWithJS(closeButtonNotificationBanner);

        assertTextContainsByOrder(manageRenewalMembershipTypeList, 0, manageRenewalMembershipTypeMessage);

    }

    public void manageRenewalForInvalidCase() {
        openURL(URL_MEMBERSHIP_MANAGEMENT);
    }

    public void unableToManageRenewalForInvalidCase() {
        verifyElementDisable(manageRenewalButtonList);
    }

}

