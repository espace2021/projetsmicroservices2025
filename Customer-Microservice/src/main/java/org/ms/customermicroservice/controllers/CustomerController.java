package org.ms.customermicroservice.controllers;

import org.ms.customermicroservice.entities.Customer;
import org.ms.customermicroservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> findAll() {
        return service.getAllCustomers();
    }

    @PostMapping
    public Customer save(@RequestBody Customer c) {
        return service.saveCustomer(c);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }
}