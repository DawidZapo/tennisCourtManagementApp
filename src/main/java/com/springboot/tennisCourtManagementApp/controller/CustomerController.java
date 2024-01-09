package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.entity.Customer;
import com.springboot.tennisCourtManagementApp.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("isUpdate",true);
        return "customer-form";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        model.addAttribute("isUpdate",false);

        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id){
        customerService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }

}

