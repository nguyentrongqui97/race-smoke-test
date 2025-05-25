package helpers;

import constants.ConstantGlobal;
import net.datafaker.Faker;

import java.util.Locale;

public class DataFakerHelpers {

    private static Faker faker;

    public static Faker createDataFaker() {
        faker = new Faker(new Locale(ConstantGlobal.DATA_FAKER_EN));
        return faker;
    }

    public static Faker getDataFaker() {
        if (faker == null) {
            faker = createDataFaker();
        }
        return faker;
    }

    public static void setDataFaker(Faker faker) {
        DataFakerHelpers.faker = faker;
    }
}
