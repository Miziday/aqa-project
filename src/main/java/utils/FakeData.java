package utils;

import net.datafaker.Faker;

import java.util.UUID;

public class FakeData {

    private static final Faker faker = new Faker();

    public static String getRandomFullName() {
        return faker.name().fullName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String getRandomUUID() {
        return String.valueOf(UUID.randomUUID());
    }
}
