package com.mpj.assignment.repository;

import com.mpj.assignment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "Select s from Student s where s.firstName = ?1")
    List<Student> findByFirstName(String name);
}
