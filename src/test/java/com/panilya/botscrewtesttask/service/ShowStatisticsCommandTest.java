package com.panilya.botscrewtesttask.service;

import com.panilya.botscrewtesttask.database.Degree;
import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.database.Lecturer;
import com.panilya.botscrewtesttask.fakedata.DepartmentObjectMother;
import com.panilya.botscrewtesttask.fakedata.LecturerObjectMother;
import com.panilya.botscrewtesttask.junit.ClearDatabase;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.repository.LecturerRepository;
import com.panilya.botscrewtesttask.service.commands.ShowStatisticsCommand;
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
public class ShowStatisticsCommandTest {

    @Autowired
    private ShowStatisticsCommand showStatisticsCommand;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Test
    void testCommandExecution() {
        Lecturer assistant1 = LecturerObjectMother.createLecturer("Illia", Degree.ASSISTANT, BigDecimal.valueOf(1000));
        Lecturer assistant2 = LecturerObjectMother.createLecturer("Vlad", Degree.ASSISTANT, BigDecimal.valueOf(2000));
        Lecturer associateProfessor = LecturerObjectMother.createLecturer("Vasya", Degree.ASSOCIATE_PROFESSOR, BigDecimal.valueOf(3000));
        Lecturer professor = LecturerObjectMother.createLecturer("Petya", Degree.PROFESSOR, BigDecimal.valueOf(4000));
        lecturerRepository.saveAll(List.of(assistant1, assistant2, associateProfessor, professor));

        assertThat(lecturerRepository.count()).isEqualTo(4);

        departmentRepository.save(DepartmentObjectMother.createDepartment("Math"));
        assertThat(departmentRepository.count()).isEqualTo(1);

        Department mathDepartment = departmentRepository.findByName("Math").orElseThrow();
        mathDepartment.addLecturer(lecturerRepository.findByName(assistant1.getName()).orElseThrow());
        mathDepartment.addLecturer(lecturerRepository.findByName(assistant2.getName()).orElseThrow());
        mathDepartment.addLecturer(lecturerRepository.findByName(associateProfessor.getName()).orElseThrow());
        mathDepartment.addLecturer(lecturerRepository.findByName(professor.getName()).orElseThrow());
        mathDepartment.setHeadOfDepartment(lecturerRepository.findByName(professor.getName()).orElseThrow());

        departmentRepository.save(mathDepartment);

        assertThat(departmentRepository.count()).isEqualTo(1);

        CommandInformation command = CommandInformation.SHOW_STATISTICS_COMMAND;
        String commandResult = showStatisticsCommand.executeCommand(command.getInputTemplate().formatted(mathDepartment.getName()));
        assertThat(commandResult).isEqualTo(command.getOutputTemplate().formatted(2, 1, 1));
    }

}
