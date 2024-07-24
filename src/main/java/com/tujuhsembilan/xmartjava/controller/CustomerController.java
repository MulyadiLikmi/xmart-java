package com.tujuhsembilan.xmartjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.xmartjava.entity.Customer;
import com.tujuhsembilan.xmartjava.service.CustomerServiceImpl;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("/customer")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer findById(@PathVariable("id") String id) {
        return customerService.findById(id);
    }

    @PostMapping("/customer")
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @PutMapping("/customer")
    public Customer edit(@RequestBody Customer customer) {
        return customerService.edit(customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteById(@PathVariable("id") String id) {
        customerService.deleteById(id);
    }
}