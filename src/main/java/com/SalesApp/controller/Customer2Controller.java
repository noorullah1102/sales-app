package com.SalesApp.controller;


import com.SalesApp.model.Customer;
import com.SalesApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@Controller
public class Customer2Controller {


    @Autowired
    private CustomerRepository customerRepository;


    // Load Thymeleaf page with dropdown
    @GetMapping("/customer-lookup")
    public String showCustomerDropdown(Model model)
    {
        model.addAttribute("customers", customerRepository.findAll());
        return "views/customer/lookup"; // Put the Thymeleaf file here
    }


    // AJAX endpoint
    @GetMapping("/api/customers/{id}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
