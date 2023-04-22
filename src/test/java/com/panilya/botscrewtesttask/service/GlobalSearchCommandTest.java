package com.panilya.botscrewtesttask.service;

import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.database.Lecturer;
import com.panilya.botscrewtesttask.fakedata.DepartmentObjectMother;
import com.panilya.botscrewtesttask.fakedata.LecturerObjectMother;
import com.panilya.botscrewtesttask.junit.ClearDatabase;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.repository.LecturerRepository;
import com.panilya.botscrewtesttask.service.commands.GlobalSearchCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ClearDatabase
@ActiveProfiles("test")
public class GlobalSearchCommandTest {

    @Autowired
    private GlobalSearchCommand globalSearchCommand;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Test
    void testCommandExecution() {
        Lecturer lecturer = LecturerObjectMother.createLecturer("Illia");
        Lecturer lecturer2 = LecturerObjectMother.createLecturer("Vlad");
        lecturerRepository.saveAll(List.of(lecturer, lecturer2));

        assertThat(lecturerRepository.count()).isEqualTo(2);

        departmentRepository.save(DepartmentObjectMother.createDepartment("Math"));
        assertThat(departmentRepository.count()).isEqualTo(1);

        Department mathDepartment = departmentRepository.findByName("Math").orElseThrow();
        mathDepartment.addLecturer(lecturerRepository.findByName(lecturer.getName()).orElseThrow());
        mathDepartment.addLecturer(lecturerRepository.findByName(lecturer2.getName()).orElseThrow());

        departmentRepository.save(mathDepartment);

        CommandInformation command = CommandInformation.GLOBAL_SEARCH_COMMAND;
        String commandResult = globalSearchCommand.executeCommand(command.getInputTemplate().formatted("la"));
        assertThat(commandResult).isEqualTo(command.getOutputTemplate().formatted("Vlad"));
    }

}
