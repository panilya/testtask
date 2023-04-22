package com.panilya.botscrewtesttask.fakedata;

import com.panilya.botscrewtesttask.database.Department;
import net.datafaker.Faker;

public class DepartmentObjectMother {

    private static final Faker faker = new Faker();

    public static Department createDepartment() {
        return new Department(faker.company().name());
    }

    public static Department createDepartment(String name) {
        return new Department(name);
    }

}
