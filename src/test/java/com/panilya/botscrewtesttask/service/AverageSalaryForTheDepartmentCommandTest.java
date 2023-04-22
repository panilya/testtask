package com.panilya.botscrewtesttask.service;

import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.database.Lecturer;
import com.panilya.botscrewtesttask.fakedata.DepartmentObjectMother;
import com.panilya.botscrewtesttask.fakedata.LecturerObjectMother;
import com.panilya.botscrewtesttask.junit.ClearDatabase;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.repository.LecturerRepository;
import com.panilya.botscrewtesttask.service.commands.AverageSalaryForTheDepartmentCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ClearDatabase
@ActiveProfiles("test")
public class AverageSalaryForTheDepartmentCommandTest {

    @Autowired
    private AverageSalaryForTheDepartmentCommand averageSalaryForTheDepartmentCommand;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Test
    void testCommandExecution() {
        Lecturer lecturer = LecturerObjectMother.createLecturer("Illia", BigDecimal.valueOf(1000));
        Lecturer lecturer2 = LecturerObjectMother.createLecturer("Vlad", BigDecimal.valueOf(2000));
        lecturerRepository.saveAll(List.of(lecturer, lecturer2));

        assertThat(lecturerRepository.count()).isEqualTo(2);

        departmentRepository.save(DepartmentObjectMother.createDepartment("Math"));
        assertThat(departmentRepository.count()).isEqualTo(1);

        Department mathDepartment = departmentRepository.findByName("Math").orElseThrow();
        mathDepartment.addLecturer(lecturerRepository.findByName(lecturer.getName()).orElseThrow());
        mathDepartment.addLecturer(lecturerRepository.findByName(lecturer2.getName()).orElseThrow());

        departmentRepository.save(mathDepartment);

        CommandInformation command = CommandInformation.SHOW_AVERAGE_SALARY_FOR_THE_DEPARTMENT_COMMAND;
        String commandResult = averageSalaryForTheDepartmentCommand.executeCommand(command.getInputTemplate().formatted(mathDepartment.getName()));
        assertThat(commandResult).isEqualTo(command.getOutputTemplate().formatted(mathDepartment.getName(), "1500.00"));
    }

}
