package org.ms.customermicroservice.services;

import org.ms.customermicroservice.entities.Customer;
import org.ms.customermicroservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Customer> customer = repo.findById(id);
        return customer.orElse(null); // ou lever une exception si non trouvé
    }

    public Customer saveCustomer(Customer customer) {
        return repo.save(customer);
    }
}
