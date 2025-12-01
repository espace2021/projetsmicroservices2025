package org.ms.productmicroservice.controllers;

import org.ms.prodcutmicroservice.entities.Product;

import org.ms.productmicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // GET ALL
    @GetMapping
    public List<Product> findAll() {
        return service.getAllProducts();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Product findById(@PathVariable String id) {
        return service.getProductById(id);
    }

    // CREATE
    @PostMapping
    public Product save(@RequestBody Product p) {
        return service.saveProduct(p);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product update(@PathVariable String id, @RequestBody Product product) {
        Product existing = service.getProductById(id);
        if (existing == null) return null;

        existing.setLabel(product.getLabel());
        existing.setPrice(product.getPrice());

        return service.saveProduct(existing);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteProduct(id);
    }
}
