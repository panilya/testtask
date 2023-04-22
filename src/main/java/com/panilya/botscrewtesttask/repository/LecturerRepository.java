package com.panilya.botscrewtesttask.repository;

import com.panilya.botscrewtesttask.database.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    Optional<Lecturer> findByName(String name);

    // Since example of answer doesn't mention searching also department names,
    // I assume that it's not needed and I will only search by lecturers names
    @Query("select l.name from Lecturer l where l.name like %:param%")
    List<String> globalSearch(@Param("param") String template);

}
