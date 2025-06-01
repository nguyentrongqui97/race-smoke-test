package pages;

import static constants.ConstantSystemValues.*;

import org.openqa.selenium.By;
import utils.DataGenerateUtils;

import static keywords.WebUI.*;

public class MyProfilePage {
    String contact1NameData;
    String contact1PhoneNumberData;
    String contact1EmailData;
    String contact2NameData;
    String contact2PhoneNumberData;
    String contact2EmailData;


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
    static By addAnotherEmergencyContactButton = By.cssSelector("button.MuiButton-outlined:nth-child(2)");
    static By saveButton = By.xpath("//button[normalize-space(text())='Save details']");
    static By successStatusMessage = By.cssSelector("div.MuiPaper-root:nth-child(1)");
    static By emergencyContact1FullNameAfterAdded = By.cssSelector("div.css-1b01cce:nth-of-type(1) > div:nth-of-type(2) > p");
    static By emergencyContact2FullNameAfterAdded = By.cssSelector("div.MuiGrid2-grid-xs-12:nth-child(2) > div:nth-child(2) > p");
    static By emergencyContact1PhoneNumberAfterAdded = By.cssSelector("div.css-1b01cce:nth-child(1) > div:nth-child(3) > p:nth-child(2)");
    static By emergencyContact2PhoneNumberAfterAdded = By.cssSelector("div.MuiGrid2-grid-xs-12:nth-child(2) > div:nth-child(3) > p");
    static By emergencyContact1RelationshipAfterAdded = By.cssSelector("div.css-1b01cce:nth-child(1) > div:nth-child(4) > p:nth-child(2)");
    static By emergencyContact2RelationshipAfterAdded = By.cssSelector("div.MuiGrid2-grid-xs-12:nth-child(2) > div:nth-child(4) > p:nth-child(2)");
    static By emergencyContact1EmailAfterAdded = By.cssSelector("div.css-1b01cce:nth-child(1) > div:nth-child(5) > p:nth-child(2)");
    static By emergencyContact2EmailAfterAdded = By.cssSelector("div.MuiGrid2-grid-xs-12:nth-child(2) > div:nth-child(5) > p:nth-child(2)");


    public void addEmergencyContact(String number) {
        int numberOfContact = Integer.parseInt(number);

        contact1NameData = DataGenerateUtils.generateFullNameData();
        contact1PhoneNumberData = DataGenerateUtils.generatePhoneNumberData();
        contact1EmailData = DataGenerateUtils.generateEmailAddressData();

        clickElementWithJS(emergencyContactDetailsAccordionSideBar);
        clickElement(addEmergencyContactDetailsButton);

        sendText(emergencyContact1FirstName, contact1NameData);
        sendText(emergencyContact1PhoneNumber, contact1PhoneNumberData);
        sendText(emergencyContact1Relation, RELATIONSHIP_AS_FATHER);
        sendText(emergencyContact1Email, contact1EmailData);

        if (numberOfContact == 2) {
            contact2NameData = DataGenerateUtils.generateFullNameData();
            contact2PhoneNumberData = DataGenerateUtils.generatePhoneNumberData();
            contact2EmailData = DataGenerateUtils.generateEmailAddressData();

            clickElement(addAnotherEmergencyContactButton);

            sendText(emergencyContact2FirstName, contact2NameData);
            sendText(emergencyContact2PhoneNumber, contact2PhoneNumberData);
            sendText(emergencyContact2Relation, RELATIONSHIP_AS_MOTHER);
            sendText(emergencyContact2Email, contact2EmailData);
        }

        clickElement(saveButton);
    }

    public void emergencyContactSuccessfullyAdded(String number) {
        int numberOfContact = Integer.parseInt(number);
        assertTextEqual(successStatusMessage, SUCCESS_MESSAGE_ADD_EMERGENCY_CONTACT, 10);

        if(numberOfContact == 2){
            assertTextEqual(emergencyContact2FullNameAfterAdded, contact2NameData);
            assertTextEqual(emergencyContact2PhoneNumberAfterAdded, contact2PhoneNumberData);
            assertTextEqual(emergencyContact2RelationshipAfterAdded, RELATIONSHIP_AS_MOTHER);
            assertTextEqual(emergencyContact2EmailAfterAdded, contact2EmailData);
        }

        assertTextEqual(emergencyContact1FullNameAfterAdded, contact1NameData);
        assertTextEqual(emergencyContact1PhoneNumberAfterAdded, contact1PhoneNumberData);
        assertTextEqual(emergencyContact1RelationshipAfterAdded, RELATIONSHIP_AS_FATHER);
        assertTextEqual(emergencyContact1EmailAfterAdded, contact1EmailData);

    }

}
