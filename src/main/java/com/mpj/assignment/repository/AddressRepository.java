package com.mpj.assignment.repository;


import com.mpj.assignment.model.Address;
import com.mpj.assignment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
