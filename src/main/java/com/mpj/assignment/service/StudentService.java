package com.mpj.assignment.service;

import com.mpj.assignment.model.Address;
import com.mpj.assignment.model.Student;
import com.mpj.assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressService addressService;

    @Transactional
    public Student addStudent(Student student) throws Exception {
        Student studentDB = this.studentRepository.save(student);

        Address address = new Address();
        address.setAddress("Varanasi");
        address.setStudent(student);
//        if (student.getFirstName().equals("Thuc")) {
//            throw new Exception("pcccc");
//        }

        // calling addAddress() method
        // of AddressService class
        this.addressService.addAddress(address);
        return studentDB;
    }
}
