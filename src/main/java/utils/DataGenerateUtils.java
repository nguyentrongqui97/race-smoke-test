package utils;

import helpers.DataFakerHelpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class DataGenerateUtils {

    public static String generateFullNameData() {
        return DataFakerHelpers.generateDataFaker().name().fullName();
    }

    public static String generateLastNameData() {
        return DataFakerHelpers.generateDataFaker().name().lastName();
    }

    public static String generateAddressLine1Data() {
        return DataFakerHelpers.generateDataFaker().address().streetAddress();
    }

    public static String generateAddressLine2Data() {
        return DataFakerHelpers.generateDataFaker().address().secondaryAddress();
    }

    public static String generatePostCodeData() {
        return DataFakerHelpers.generateDataFaker().address().postcode();
    }

    public static String generatePhoneNumberData() {
        return DataFakerHelpers.generateDataFaker().numerify("071########");
    }

    public static String generateCityData() {
        return DataFakerHelpers.generateDataFaker().address().cityName();
    }

    public static String generateDayData() {
        Random random = new Random();
        LocalDate startDate = LocalDate.of(1950, 1, 1);
        long daysForParents = ChronoUnit.DAYS.between(startDate, LocalDate.of(2009, 12, 31));
        LocalDate randomDate = startDate.plusDays(random.nextInt((int) daysForParents + 1));
        return String.valueOf(randomDate.getDayOfMonth());
    }

    public static String generateMonthData() {
        Random random = new Random();
        LocalDate startDate = LocalDate.of(1950, 1, 1);
        long daysForParents = ChronoUnit.DAYS.between(startDate, LocalDate.of(2009, 12, 31));
        LocalDate randomDate = startDate.plusDays(random.nextInt((int) daysForParents + 1));
        return String.valueOf(randomDate.getMonthValue());
    }

    public static String generateYearData() {
        Random random = new Random();
        LocalDate startDate = LocalDate.of(1950, 1, 1);
        long daysForParents = ChronoUnit.DAYS.between(startDate, LocalDate.of(2009, 12, 31));
        LocalDate randomDate = startDate.plusDays(random.nextInt((int) daysForParents + 1));
        return String.valueOf(randomDate.getYear());
    }

    public static String generateYearForJuniorData() {
        Random random = new Random();
        LocalDate startDate = LocalDate.of(2010, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        LocalDate randomDate = startDate.plusDays(random.nextInt((int) totalDays + 1));
        return String.valueOf(randomDate.getYear());
    }

    public static String generatePaymentEmailAddressData() {
        return DataFakerHelpers.generateDataFaker().internet().emailAddress();
    }
}
