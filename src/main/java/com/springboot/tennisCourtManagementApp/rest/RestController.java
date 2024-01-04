package com.springboot.tennisCourtManagementApp.rest;

import com.springboot.tennisCourtManagementApp.entity.Customer;
import com.springboot.tennisCourtManagementApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private CustomerService customerService;

    @Autowired
    public RestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/search")
    public List<Customer> searchByLastName(@RequestParam("lastName") String lastName){
        List<Customer> customers = customerService.findByLastName(lastName);
        return customers;
    }

    @GetMapping("/customer/searchContaining")
    public List<Customer> searchByLastNameContaining(@RequestParam("lastName") String pattern){
        List<Customer> customers = customerService.findByLastNameContaining(pattern);
        //List<Customer> customersFirstName = customerService.findByFirstNameContaining(pattern);
        return customers;
    }

}
