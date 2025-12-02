package org.ms.factureservice.controllers;

import org.ms.factureservice.entities.Facture;
import org.ms.factureservice.models.Customer;
import org.ms.factureservice.models.Product;
import org.ms.factureservice.services.FactureServicesMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private FactureServicesMethods factureServicesMethods;

    // Récupérer toutes les factures
    @GetMapping
    public List<Facture> findAll() {
        return factureServicesMethods.findAll();
    }

    // Sauvegarder une facture
    @PostMapping
    public Facture save(@RequestBody Facture f) {
        return factureServicesMethods.save(f);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomersFromCustomerService() {
        return factureServicesMethods.getCustomersFromCustomerService();
    }

    @GetMapping("/products")
    public List<Product> getProductsFromProductService() {
        return factureServicesMethods.getProductsFromProductService();
    }
}