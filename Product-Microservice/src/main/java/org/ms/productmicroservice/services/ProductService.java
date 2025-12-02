package org.ms.productmicroservice.services;

import org.ms.prodcutmicroservice.entities.Product;
import org.ms.productmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public Product getProductById(String id) {

        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found (id=" + id + ")"
                )
        );
    }

    public void deleteProduct(String id) {
        repo.deleteById(id);
    }
}
