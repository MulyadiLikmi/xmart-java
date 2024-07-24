package com.tujuhsembilan.xmartjava.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.xmartjava.entity.Customer;
import com.tujuhsembilan.xmartjava.exception.ResourceNotFoundException;
import com.tujuhsembilan.xmartjava.repository.CustomerRepo;

@Service
public class CustomerServiceImpl {
    @Autowired
    private CustomerRepo customerRepo;

    public Customer findById(String id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer dengan QRCode tersebut tidak ditemukan"));
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Customer create(Customer customer) {
        customer.setQrCode(UUID.randomUUID().toString());
        return customerRepo.save(customer);
    }

    public Customer edit(Customer customer) {
        return customerRepo.save(customer);
    }

    public void deleteById(String id) {
        customerRepo.deleteById(id);
    }
}