package pages;

import org.openqa.selenium.By;
import utils.DataGenerateUtils;

import static constants.ConstantGlobal.URL_ADD_JUNIOR_PROFILE;
import static constants.ConstantSystemValues.SUCCESS_MESSAGE_ADD_JUNIOR_PROFILE;
import static keywords.WebUI.*;

public class MyProfile {

    static By emergencyContactDetailsAccordionSideBar = By.cssSelector("div.MuiPaper-root:nth-child(4) > h3:nth-child(1) > div:nth-child(1) > div:nth-child(2)");
    static By addEmergencyContactDetailsButton = By.cssSelector(".css-csmd10");
    static By emergencyContact1FirstName = By.name("contacts[0].fullName");
    static By emergencyContact2FirstName = By.name("contacts[1].fullName");
    static By emergencyContact1PhoneNumber = By.name("contacts[0].phone");
    static By emergencyContact2PhoneNumber = By.name("contacts[1].phone");
    static By emergencyContact1Relation = By.name("contacts[0].relationship");
    static By emergencyContact2Relation = By.name("contacts[1].relationship");
    static By emergencyContact1Email = By.name("contacts[0].email");
    static By emergencyContact2Email = By.name("contacts[1].email");


    public void addEmergencyContact(int number){
        clickElement(emergencyContactDetailsAccordionSideBar);
        clickElement(addEmergencyContactDetailsButton);




    }

}
