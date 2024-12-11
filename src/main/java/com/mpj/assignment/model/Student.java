package com.mpj.assignment.model;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;

import java.util.Arrays;

@Entity
@Data
@Table(name = "students")
public class Student implements InitializingBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Student(){
        System.out.println("This is the construction method.....");
    }

    public Student(long id, String firstName, String lastName, String emailId, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    @PostConstruct
    public void init() {
        System.out.println("This is the init method.....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("This is the post constructed object method.....");

    }
}
