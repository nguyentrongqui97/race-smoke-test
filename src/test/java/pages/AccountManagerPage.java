package pages;
import static constants.ConstantSystemValues.*;
import static keywords.WebUI.*;

import static constants.ConstantGlobal.*;
import org.openqa.selenium.By;
import utils.DataGenerateUtils;

public class AccountManagerPage {

    private String profileFirstNameData;
    private String profileLastNameData;
    private String dayData;
    private String monthData;
    private String yearData;

    static By manageProfileSector = By.cssSelector("div.box-container:nth-child(4)");
    static By addAJuniorProfileButton = By.cssSelector("button.MuiButton-contained:nth-child(2)");
    static By profileFirstName = By.name("profile.first_name");
    static By profileLastName = By.name("profile.last_name");
    static By day = By.name("dob.day");
    static By month = By.name("dob.month");
    static By year = By.name("dob.year");
    static By sexAtBirth = By.cssSelector("div.MuiGrid2-spacing-xs-3:nth-child(3) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1)");
    static By sexAtBirthDropDown = By.cssSelector("ul[role='listbox'] li");
    static By region = By.cssSelector("div.MuiGrid2-root:nth-child(4) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1)");
    static By regionDropDown = By.cssSelector("ul[role='listbox'] li");
    static By createNewProfileButton = By.cssSelector(".MuiButton-contained");
    static By juniorProfileForm = By.cssSelector("div.MuiGrid2-grid-xs-5:nth-child(1)");
    static By successStatusMessage = By.cssSelector("div.MuiPaper-root");


    public void completeJuniorProfile() throws InterruptedException {
        openURL(URL_ADD_JUNIOR_PROFILE);

        profileFirstNameData = DataGenerateUtils.generateFullNameData();
        profileLastNameData = DataGenerateUtils.generateLastNameData();
        dayData = DataGenerateUtils.generateDayData();
        monthData = DataGenerateUtils.generateMonthData();
        yearData = DataGenerateUtils.generateYearForJuniorData();

        waitForElementVisible(juniorProfileForm, 10);
        sendText(profileFirstName, profileFirstNameData);
        sendText(profileLastName, profileLastNameData);
        sendText(day, dayData);
        sendText(month, monthData);
        sendText(year, yearData);
        selectARandomOptionFromDropDown(sexAtBirth, sexAtBirthDropDown);
        selectARandomOptionFromDropDown(region, regionDropDown);
        clickElement(createNewProfileButton);
    }

    public void verifyJuniorProfileAdded(){
        waitForPageLoaded();
        assertTextEqual(successStatusMessage, SUCCESS_MESSAGE_ADD_JUNIOR_PROFILE, 10);
    }
}
