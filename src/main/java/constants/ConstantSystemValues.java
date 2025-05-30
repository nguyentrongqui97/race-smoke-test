package constants;

import helpers.PropertiesHelpers;

public class ConstantSystemValues {
    static {
        PropertiesHelpers.loadAllFiles();
    }

    public final static String SUCCESS_MESSAGE_ADD_JUNIOR_PROFILE = PropertiesHelpers.getValue("SUCCESS_MESSAGE_ADD_JUNIOR_PROFILE");

}
