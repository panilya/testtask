package com.panilya.botscrewtesttask.fakedata;

import com.panilya.botscrewtesttask.database.Degree;
import com.panilya.botscrewtesttask.database.Lecturer;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.util.List;

import static com.panilya.botscrewtesttask.database.Degree.ASSISTANT;
import static com.panilya.botscrewtesttask.database.Degree.ASSOCIATE_PROFESSOR;
import static com.panilya.botscrewtesttask.database.Degree.PROFESSOR;

public class LecturerObjectMother {

    private static final Faker faker = new Faker();

    public static Lecturer createLecturer() {
        return createLecturer(faker.name().fullName());
    }

    public static Lecturer createLecturer(String name) {
        return createLecturer(name, List.of(ASSISTANT, ASSOCIATE_PROFESSOR, PROFESSOR).get(faker.number().numberBetween(0, 3)),
                BigDecimal.valueOf(faker.number().randomDouble(2, 1000, 10000)));
    }

    public static Lecturer createLecturer(String name, BigDecimal salary) {
        return createLecturer(name, List.of(ASSISTANT, ASSOCIATE_PROFESSOR, PROFESSOR).get(faker.number().numberBetween(0, 3)),
                salary);
    }

    public static Lecturer createLecturer(String name, Degree degree, BigDecimal salary) {
        return new Lecturer(name, degree, salary);
    }

}
