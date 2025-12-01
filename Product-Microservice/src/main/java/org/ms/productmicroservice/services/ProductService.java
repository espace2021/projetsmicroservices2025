package org.ms.productmicroservice.services;

import org.ms.prodcutmicroservice.entities.Product;
import org.ms.productmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repo.findById(id).orElse(null);
    }

    public void deleteProduct(String id) {
        repo.deleteById(id);
    }
}
