package constants;

import helpers.PropertiesHelpers;

public class ConstantGlobal {
    static {
        PropertiesHelpers.loadAllFiles();
    }

    public final static String BROWSER = PropertiesHelpers.getValue("BROWSER");
    public final static boolean HEADLESS = Boolean.parseBoolean(PropertiesHelpers.getValue("HEADLESS"));
    public final static String URL = PropertiesHelpers.getValue("URL");
    public final static String URL_CRM = PropertiesHelpers.getValue("URL_CRM");
    public final static String USERNAME = PropertiesHelpers.getValue("USERNAME");
    public final static long STEP_TIME = Long.parseLong(PropertiesHelpers.getValue("STEP_TIME"));
    public final static long EXPLICIT_TIMEOUT = Long.parseLong(PropertiesHelpers.getValue("EXPLICIT_TIMEOUT"));
    public final static long PAGE_LOAD_TIMEOUT = Long.parseLong(PropertiesHelpers.getValue("PAGE_LOAD_TIMEOUT"));
    public final static String ENV = PropertiesHelpers.getValue("ENV");
    public final static String SCREENSHOT_FAIL = PropertiesHelpers.getValue("SCREENSHOT_FAIL");
    public final static String SCREENSHOT_PASS = PropertiesHelpers.getValue("SCREENSHOT_PASS");
    public final static String SCREENSHOT_STEP = PropertiesHelpers.getValue("SCREENSHOT_STEP");
    public final static String RECORD_VIDEO = PropertiesHelpers.getValue("RECORD_VIDEO");
    public final static String RECORD_VIDEO_PATH = PropertiesHelpers.getValue("RECORD_VIDEO_PATH");
    public final static String EXTENT_REPORT_PATH = PropertiesHelpers.getValue("EXTENT_REPORT_PATH");
    public final static String AUTHOR = PropertiesHelpers.getValue("AUTHOR");
    public final static String LOCATE = PropertiesHelpers.getValue("LOCATE");
    public static final String CHROME_BINARY_PATH = PropertiesHelpers.getValue("CHROME_BROWSER_BINARY");
    public static final String URL_DASHBOARD = PropertiesHelpers.getValue("URL_DASHBOARD");
    public static final String URL_ADD_JUNIOR_PROFILE = PropertiesHelpers.getValue("URL_ADD_JUNIOR_PROFILE");
    public static final String URL_MEMBERSHIP_MANAGEMENT = PropertiesHelpers.getValue("URL_MEMBERSHIP_MANAGEMENT");
    public static final String MAIL_GENERATOR_URL = PropertiesHelpers.getValue("MAIL_GENERATOR_URL");
    public static final String PASSWORD = PropertiesHelpers.getValue("PASSWORD");
    public static final String EMAIL_TARGET_SENDER = PropertiesHelpers.getValue("EMAIL_TARGET_SENDER");
    public static final String DATA_FAKER_EN = PropertiesHelpers.getValue("DATA_FAKER_EN");
    public static final String TEMP_DATA_FILE_DIR = PropertiesHelpers.getValue("TEMP_DATA_FILE_DIR");

}
