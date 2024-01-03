package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.dao.CustomerRepository;
import com.springboot.tennisCourtManagementApp.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    private EntityManager entityManager;

    @Autowired
    public CustomerServiceImpl(CustomerRepository employeeRepository, EntityManager entityManager){
        this.customerRepository = employeeRepository;
        this.entityManager = entityManager;
    }
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> result = Optional.ofNullable(customerRepository.findById(id));
        Customer customer = null;

        if(result.isPresent()){
            customer = result.get();
        }
        else{
            throw new RuntimeException("Did not find customer id: " + id);
        }
        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
    @Override
    public Customer findCustomerByIdJoinFetch(Long id) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "select i from Customer i "
                        + "LEFT JOIN FETCH i.courtReservations "
                        + "where i.id = :data", Customer.class);
        query.setParameter("data", id);

        List<Customer> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }
}
