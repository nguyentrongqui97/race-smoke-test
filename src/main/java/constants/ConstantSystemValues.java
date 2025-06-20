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
    public final static String COMMUNITY = PropertiesHelpers.getValue("COMMUNITY");
    public final static String ACTIVE = PropertiesHelpers.getValue("ACTIVE");
    public final static String RACER = PropertiesHelpers.getValue("RACER");
    public final static String ULTIMATE_RACER = PropertiesHelpers.getValue("ULTIMATE_RACER");
    public final static String SUCCESS_MESSAGE_UPGRADE_MEMBERSHIP = PropertiesHelpers.getValue("SUCCESS_MESSAGE_UPGRADE_MEMBERSHIP");
    public final static String SUCCESS_MESSAGE_MANAGE_RENEWAL = PropertiesHelpers.getValue("SUCCESS_MESSAGE_MANAGE_RENEWAL");
    public static final String STRIPE_CARD_NUMBER = PropertiesHelpers.getValue("STRIPE_CARD_NUMBER");
    public static final String STRIPE_CARD_EXPIRY = PropertiesHelpers.getValue("STRIPE_CARD_EXPIRY");
    public static final String STRIPE_CARD_CVC = PropertiesHelpers.getValue("STRIPE_CARD_CVC");
    public final static String SUCCESS_MESSAGE_REFUND_MEMBERSHIP = PropertiesHelpers.getValue("SUCCESS_MESSAGE_REFUND_MEMBERSHIP");
    public final static String CREDIT_DEBIT = PropertiesHelpers.getValue("CREDIT_DEBIT");
    public final static String DIRECT_DEBIT = PropertiesHelpers.getValue("DIRECT_DEBIT");

}
