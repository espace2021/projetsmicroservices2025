package org.ms.factureservice.clients;

import org.ms.factureservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-microservice")
public interface CustomerServiceClient {

    @GetMapping("/customers")
    List<Customer> getCustomers();

    @GetMapping(path="/customers/{id}")
    Customer getCustomerById (@PathVariable(name="id") Long id);
}
