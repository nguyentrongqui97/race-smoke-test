package helpers;

import constants.ConstantGlobal;
import net.datafaker.Faker;

import java.util.Locale;

public class DataFakerHelpers {

    private static Faker faker;

    public static Faker createFaker() {
        faker = new Faker(new Locale(ConstantGlobal.DATA_FAKER_EN));
        return faker;
    }

    public static Faker createFakerByPreferredLanguage(String preferredLanguage) {
        faker = new Faker(new Locale(preferredLanguage));
        return faker;
    }

    public static Faker generateDataFaker() {
        if (faker == null) {
            faker = createFaker();
        }
        return faker;
    }

    public static Faker generateDataFakerByPreferredLanguage(String preferredLanguage) {
        faker = createFakerByPreferredLanguage(preferredLanguage);
        return faker;
    }

    public static void setFaker(Faker faker) {
        DataFakerHelpers.faker = faker;
    }
}
