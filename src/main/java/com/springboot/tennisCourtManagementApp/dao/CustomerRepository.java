package com.springboot.tennisCourtManagementApp.dao;

import com.springboot.tennisCourtManagementApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
   Customer findById(Long id);
   List<Customer> findByLastName(String lastName);
   List<Customer> findByLastNameContaining(String lastName);
}
