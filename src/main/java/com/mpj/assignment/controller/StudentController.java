package com.mpj.assignment.controller;

import com.mpj.assignment.exception.ResourceNotFoundException;
import com.mpj.assignment.model.Student;
import com.mpj.assignment.repository.StudentRepository;
import com.mpj.assignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StudentController {


    @Value("${pct.user.name:No Name}")
    private String dbName;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    // get all students
    @GetMapping("/students")
    public List<Student> getAllStudents(@RequestParam(name="firstName", required = false) String firstName){
        if (StringUtils.hasLength(firstName)) {
            return studentRepository.findByFirstName(firstName);
        }
        return studentRepository.findAll();
    }

    // get student by id rest api
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
        // TODO
        //  return not found instead
        return ResponseEntity.ok(student);
    }

    // create student rest api
    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) throws Exception {
        return studentService.addStudent(student);
    }

    // create student rest api
    @PostMapping("/students")
    public List<Student> createStudents(@RequestBody List<Student> students) {
        return studentRepository.saveAll(students);
    }

    // update student rest api
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmailId(studentDetails.getEmailId());

        Student updatedStudent = studentRepository.save(student);

        // TODO
        //  check student existing, return proper status if it's not.
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
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        // TODO
        //  return a list of updated students instead
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
