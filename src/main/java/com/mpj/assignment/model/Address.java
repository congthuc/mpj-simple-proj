package com.mpj.assignment.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;

    private String country;

    // one to one mapping means,
    // one employee stays at one address only
    @OneToOne
    private Student student;
}
