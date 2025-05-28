package pages;

import helpers.PropertiesHelpers;
import org.openqa.selenium.By;

import static keywords.WebUI.*;

public class DashboardPage extends CommonPage {
    private By membershipType = By.cssSelector(".css-v0ctg1 > div:nth-child(2) > div:nth-child(1) > h2:nth-child(1)");

    public void verifyCorrectMembershipType(String expectedMembershipType){
        waitForExpectedTextThenAssertActualTextEqual(membershipType, expectedMembershipType, 60);
    }

}
