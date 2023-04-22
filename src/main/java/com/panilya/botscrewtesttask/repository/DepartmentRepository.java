package com.panilya.botscrewtesttask.repository;

import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.database.Lecturer;
import com.panilya.botscrewtesttask.dto.DepartmentDegreeCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);

    @Query("select l from Department d join Lecturer l on d.headOfDepartment.id = l.id where d.name = :departmentName")
    Optional<Lecturer> findHeadOfDepartment(@Param("departmentName") String departmentName);

    @Query(value = "select l.degree as degree, count(l) as count from department_lecturer dl " +
            "join department d on d.id = dl.department_id " +
            "join lecturer l on dl.lecturer_id = l.id " +
            "where d.name = :departmentName " +
            "group by l.degree", nativeQuery = true)
    List<DepartmentDegreeCountDto> getDepartmentStatistics(@Param("departmentName") String departmentName);

    @Query(value = "select avg(l.salary) from department_lecturer dl " +
            "join department d on d.id = dl.department_id " +
            "join lecturer l on dl.lecturer_id = l.id " +
            "where d.name = :departmentName", nativeQuery = true)
    BigDecimal getAverageSalaryForDepartment(@Param("departmentName") String departmentName);

    @Query(value = "select count(l.id) from department_lecturer dl " +
            "join department d on d.id = dl.department_id " +
            "join lecturer l on dl.lecturer_id = l.id " +
            "where d.name = :departmentName", nativeQuery = true)
    Long countEmployeesInDepartment(@Param("departmentName") String departmentName);

}
