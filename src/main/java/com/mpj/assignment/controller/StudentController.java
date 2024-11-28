package com.mpj.assignment.controller;

import com.mpj.assignment.exception.ResourceNotFoundException;
import com.mpj.assignment.model.Student;
import com.mpj.assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

   @Autowired
    StudentRepository studentRepository;

    // get all students
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    // get student by id rest api
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
        return ResponseEntity.ok(student);
    }

    // create student rest api
    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // create student rest api
    @PostMapping("/students")
    public List<Student> createStudents(@RequestBody List<Student> students) {
        return studentRepository.saveAll(students);
    }

    // update student rest api
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmailId(studentDetails.getEmailId());

        Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    // update student rest api
    @PutMapping("/students")
    public List<ResponseEntity<Student>> updateStudents(@RequestBody List<Student> studentDetails){
        List<ResponseEntity<Student>> results = new ArrayList<>();
        for(Student studentDetail : studentDetails) {
            Student student = studentRepository.findById(studentDetail.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + studentDetail.getId()));

            student.setFirstName(student.getFirstName());
            student.setLastName(student.getLastName());
            student.setEmailId(student.getEmailId());

            results.add(ResponseEntity.ok(studentRepository.save(student)));
        }

        return results;
    }

    // delete student rest api
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // delete all students rest api
    @DeleteMapping("/students")
    public ResponseEntity<Map<String, Boolean>> deleteAllStudents(){
        studentRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted-all", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
