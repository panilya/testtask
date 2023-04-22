package com.panilya.botscrewtesttask.fakedata;

import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.database.Lecturer;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("dev")
@Service
public class DataPreInitializationService {

    private final LecturerRepository lecturerRepository;

    private final DepartmentRepository departmentRepository;

    // Flag to clear database on startup
    private final boolean clearDatabase = true;
    // Flag to fill database on startup with predefined data
    private final boolean fillDatabase = true;

    @Autowired
    public DataPreInitializationService(LecturerRepository lecturerRepository, DepartmentRepository departmentRepository) {
        this.lecturerRepository = lecturerRepository;
        this.departmentRepository = departmentRepository;
    }

    public void initializeData() {

        if (clearDatabase) {
            System.out.println("CommandLineRunner --- Clearing database");
            departmentRepository.deleteAll();
            lecturerRepository.deleteAll();
        }

        if (!fillDatabase) {
            return;
        }
        System.out.println("CommandLineRunner --- Filling database with predefined data");

        Department softwareEngineeringDepartment = DepartmentObjectMother.createDepartment("Software engineering");

        Lecturer petro = LecturerObjectMother.createLecturer("Petro");
        Lecturer maria = LecturerObjectMother.createLecturer("Maria");
        softwareEngineeringDepartment.addLecturer(petro);
        softwareEngineeringDepartment.addLecturer(maria);
        softwareEngineeringDepartment.setHeadOfDepartment(maria);

        Department computerScienceDepartment = DepartmentObjectMother.createDepartment("Computer science");

        Lecturer john = LecturerObjectMother.createLecturer("John");
        softwareEngineeringDepartment.addLecturer(john);
        computerScienceDepartment.addLecturer(john);
        computerScienceDepartment.setHeadOfDepartment(john);

        Department mathematicsDepartment = DepartmentObjectMother.createDepartment("Mathematics");

        Lecturer ivan = LecturerObjectMother.createLecturer("Ivan");
        mathematicsDepartment.addLecturer(ivan);
        mathematicsDepartment.setHeadOfDepartment(ivan);

        Department philosophyDepartment = DepartmentObjectMother.createDepartment("Philosophy");

        Lecturer illia = LecturerObjectMother.createLecturer("Illia");
        mathematicsDepartment.addLecturer(illia);
        philosophyDepartment.addLecturer(illia);
        philosophyDepartment.setHeadOfDepartment(illia);

        departmentRepository.saveAll(List.of(
                softwareEngineeringDepartment,
                computerScienceDepartment,
                mathematicsDepartment,
                philosophyDepartment
        ));
    }

}
