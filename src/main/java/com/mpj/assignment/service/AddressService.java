package com.mpj.assignment.service;

import com.mpj.assignment.model.Address;
import com.mpj.assignment.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void addAddress(Address address) {
        this.addressRepository.save(address);
    }
}
