package com.SalesApp.controller;

import com.SalesApp.model.Customer;
import com.SalesApp.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController
{
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public String viewCustomer(Model model)
    {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "views/customer/index";
    }
    @GetMapping("/customers/new")
    public String showAddForm(Model model)
    {
        model.addAttribute("customer", new Customer());
        return "/views/customer/form";
    }
    @PostMapping("/customers")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,
                               BindingResult result, Model model){
        if(result.hasErrors()){
            return "views/customer/form";
        }
        customerRepository.save(customer);
        return "redirect:/customers";
    }
}
