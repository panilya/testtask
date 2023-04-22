package com.panilya.botscrewtesttask.repository;

import com.panilya.botscrewtesttask.database.Degree;
import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.database.Lecturer;
import com.panilya.botscrewtesttask.fakedata.DepartmentObjectMother;
import com.panilya.botscrewtesttask.fakedata.LecturerObjectMother;
import com.panilya.botscrewtesttask.junit.ClearDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@ClearDatabase
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void testDepartmentCreating() {
        Department department = departmentRepository.save(DepartmentObjectMother.createDepartment("Math"));
        assertThat(department).isNotNull();
        assertThat(department.getId()).isNotNull();
        assertThat(department.getName()).isEqualTo("Math");
    }

    @Test
    void testFindByDepartmentName() {
        Department department = departmentRepository.save(DepartmentObjectMother.createDepartment("Math"));
        assertThat(department).isNotNull();
        assertThat(department.getId()).isNotNull();
        assertThat(department.getName()).isEqualTo("Math");

        Department departmentFromDb = departmentRepository.findByName("Math").orElseThrow();
        assertThat(departmentFromDb).isNotNull();
        assertThat(departmentFromDb.getId()).isNotNull();
        assertThat(departmentFromDb.getName()).isEqualTo("Math");
    }

    @Test
    void testFindDepartmentHead() {
        departmentRepository.save(DepartmentObjectMother.createDepartment("Math"));

        Department departmentFromDb = departmentRepository.findByName("Math").orElseThrow();
        assertThat(departmentRepository.count()).isEqualTo(1);
        assertThat(departmentFromDb.getName()).isEqualTo("Math");
        assertThat(departmentFromDb.getHeadOfDepartment()).isNull();

        Lecturer headOfDepartment = LecturerObjectMother.createLecturer("Vasya", Degree.PROFESSOR, BigDecimal.valueOf(2000));
        departmentFromDb.setHeadOfDepartment(headOfDepartment);

        departmentRepository.save(departmentFromDb);

        departmentRepository.findHeadOfDepartment("Math").ifPresentOrElse(
                head -> assertThat(head.getName()).isEqualTo(headOfDepartment.getName()),
                () -> fail("Head of department not found")
        );
    }

}
