package org.ms.customermicroservice.services;

import org.ms.customermicroservice.entities.Customer;
import org.ms.customermicroservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Customer getCustomerById(Long id) {

            return repo.findById(id).orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Customer not found (id=" + id + ")"
                    )
            );


    }

    public Customer saveCustomer(Customer customer) {
        return repo.save(customer);
    }
}
