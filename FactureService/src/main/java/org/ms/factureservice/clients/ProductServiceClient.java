package org.ms.factureservice.clients;

import org.ms.factureservice.models.Customer;
import org.ms.factureservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="product-microservice")
public interface ProductServiceClient {
    @GetMapping(path="/products")
    List<Product> getProducts();
    @GetMapping(path="/products/{id}")
    Product getProductById(@PathVariable(name="id") String id);
}
