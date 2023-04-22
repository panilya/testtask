package com.panilya.botscrewtesttask.config;

import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.database.Lecturer;
import com.panilya.botscrewtesttask.fakedata.LecturerObjectMother;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.repository.LecturerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("dev")
@Configuration
public class DevelopmentConfig {

//    @Bean
//    public CommandLineRunner init(
//            DepartmentRepository departmentRepository,
//            LecturerRepository lecturerRepository
//    ) {
//        return args -> {
//
//            // Flag to clear database on startup
//            final boolean clearDatabase = true;
//            // Flag to fill database on startup with predefined data
//            final boolean fillDatabase = true;
//
//            if (clearDatabase) {
//                System.out.println("CommandLineRunner --- Clearing database");
//                departmentRepository.deleteAll();
//                lecturerRepository.deleteAll();
//            }
//
//            if (!fillDatabase) {
//                return;
//            }
//            System.out.println("CommandLineRunner --- Filling database with predefined data");
//
//            Department softwareEngineeringDepartment = new Department("Software engineering");
//
//            Lecturer petro = LecturerObjectMother.createLecturer("Petro");
//            Lecturer maria = LecturerObjectMother.createLecturer("Maria");
//            softwareEngineeringDepartment.addLecturer(petro);
//            softwareEngineeringDepartment.addLecturer(maria);
//            softwareEngineeringDepartment.setHeadOfDepartment(maria);
//
//            Department computerScienceDepartment = new Department("Computer science");
//
//            Lecturer john = LecturerObjectMother.createLecturer("John");
//            softwareEngineeringDepartment.addLecturer(john);
//            computerScienceDepartment.addLecturer(john);
//            computerScienceDepartment.setHeadOfDepartment(john);
//
//            Department mathematicsDepartment = new Department("Mathematics");
//
//            Lecturer ivan = LecturerObjectMother.createLecturer("Ivan");
//            mathematicsDepartment.addLecturer(ivan);
//            mathematicsDepartment.setHeadOfDepartment(ivan);
//
//            Department philosophyDepartment = new Department("Philosophy");
//
//            Lecturer illia = LecturerObjectMother.createLecturer("Illia");
//            mathematicsDepartment.addLecturer(illia);
//            philosophyDepartment.addLecturer(illia);
//            philosophyDepartment.setHeadOfDepartment(illia);
//
//            departmentRepository.saveAll(List.of(
//                    softwareEngineeringDepartment,
//                    computerScienceDepartment,
//                    mathematicsDepartment,
//                    philosophyDepartment
//            ));
//
//        };
//    }

}
