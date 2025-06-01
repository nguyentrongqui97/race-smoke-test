package constants;

import helpers.PropertiesHelpers;

public class ConstantSystemValues {
    static {
        PropertiesHelpers.loadAllFiles();
    }

    public final static String SUCCESS_MESSAGE_ADD_JUNIOR_PROFILE = PropertiesHelpers.getValue("SUCCESS_MESSAGE_ADD_JUNIOR_PROFILE");
    public final static String SUCCESS_MESSAGE_ADD_EMERGENCY_CONTACT = PropertiesHelpers.getValue("SUCCESS_MESSAGE_ADD_EMERGENCY_CONTACT");
    public final static String RELATIONSHIP_AS_FATHER = PropertiesHelpers.getValue("RELATIONSHIP_AS_FATHER");
    public final static String RELATIONSHIP_AS_MOTHER = PropertiesHelpers.getValue("RELATIONSHIP_AS_MOTHER");

}
