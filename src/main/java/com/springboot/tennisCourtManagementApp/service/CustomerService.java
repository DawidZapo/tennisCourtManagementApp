package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer save(Customer customer);
    void deleteById(int id);
    void delete(Customer customer);
    Customer findCustomerByIdJoinFetch(Long id);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByLastNameContaining(String lastName);
}
