package com.tujuhsembilan.xmartjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tujuhsembilan.xmartjava.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, String> {

}