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
    static By upgradeToActiveButton = By.cssSelector("div.css-1wicjwk:nth-child(2) > div:nth-child(1) > div:nth-child(6) > button:nth-child(1)");
    static By upgradeToRacerButton = By.cssSelector("div.css-1wicjwk:nth-child(3) > div:nth-child(1) > div:nth-child(6) > button:nth-child(1)");
    static By upgradeToUltimateRacerButton = By.cssSelector("div.css-1wicjwk:nth-child(4) > div:nth-child(1) > div:nth-child(6) > button:nth-child(1)");
    static By currentMembership = By.cssSelector(".css-g2y9sr > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > p:nth-child(2)");
    static By membershipAfterUpgrade = By.cssSelector(".css-ejlojh > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > p:nth-child(2)");
    static By creditDebitCardRadioButton = By.cssSelector("input[name='paymentMethod'][value='STRIPE']");
    static By directDebitRadioButton = By.cssSelector("input[name='paymentMethod'][value='GOCARDLESS']");
    static By confirmUpgradeButton = By.xpath("//button[contains(text(),'Confirm Upgrade')]");

    static By successStatusMessage = By.cssSelector("div.MuiPaper-root");


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

        if ("Credit-Debit".equalsIgnoreCase(upgradedPaymentMethod)) {
            clickCheckBox(creditDebitCardRadioButton);
        } else {
            clickCheckBox(directDebitRadioButton);
        }

        clickElementWithJSWithoutScrolling(confirmUpgradeButton, 20);

        signUpPage.makePayment(upgradedPaymentMethod);

    }

    public void successfullyUpgradeAMembership(String membership) {
        String accountMembershipFirstName = signUpPage.returnFirstName();
        String accountMembershipLastName = signUpPage.returnLastName();
        System.out.println("accountMembershipFirstName" + accountMembershipFirstName);
        System.out.println("accountMembershipLastName" + accountMembershipLastName);

        assertTextEqual(successStatusMessage,
                accountMembershipFirstName + " " + accountMembershipLastName + "'s " + SUCCESS_MESSAGE_UPGRADE_MEMBERSHIP + membership,
                20);
    }
}

