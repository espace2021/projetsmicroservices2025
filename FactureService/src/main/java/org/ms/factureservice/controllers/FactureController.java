package org.ms.factureservice.controllers;

import org.ms.factureservice.clients.CustomerServiceClient;
import org.ms.factureservice.clients.ProductServiceClient;
import org.ms.factureservice.entities.Facture;
import org.ms.factureservice.models.Customer;
import org.ms.factureservice.models.Product;
import org.ms.factureservice.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private FactureRepository repo;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    // Récupérer toutes les factures
    @GetMapping
    public List<Facture> findAll() {
        List<Facture> factures = repo.findAll();
        // Optionnel : peupler le client et produit pour chaque facture
        factures.forEach(f -> {
            if(f.getClientId() != null) {
                f.setClient(customerServiceClient.getCustomerById(f.getClientId()));
            }
            if(f.getFactureligne() != null) {
                f.getFactureligne().forEach(l -> {
                    if(l.getProduitID() != null) {
                        l.setProduit(productServiceClient.getProductById(l.getProduitID()));
                    }
                });
            }
        });
        return factures;
    }

    // Sauvegarder une facture
    @PostMapping
    public Facture save(@RequestBody Facture f) {
        // Lier chaque ligne à la facture
        if(f.getFactureligne() != null) {
            f.getFactureligne().forEach(l -> l.setFacture(f));
        }

        // Sauvegarder la facture (id généré automatiquement)
        Facture savedFacture = repo.save(f);

        // Peupler le client
        if(savedFacture.getClientId() != null) {
            Customer client = customerServiceClient.getCustomerById(savedFacture.getClientId());
            savedFacture.setClient(client);
        }

        // Peupler les produits de chaque ligne
        if(savedFacture.getFactureligne() != null) {
            savedFacture.getFactureligne().forEach(l -> {
                if(l.getProduitID() != null) {
                    Product produit = productServiceClient.getProductById(l.getProduitID());
                    l.setProduit(produit);
                }
            });
        }

        return savedFacture;
    }

    // Récupérer tous les clients via Feign
    @GetMapping("/customers")
    public List<Customer> getCustomersFromCustomerService() {
        return customerServiceClient.getCustomers();
    }

    // Récupérer tous les produits via Feign
    @GetMapping("/products")
    public List<Product> getProductsFromProductService() {
        return productServiceClient.getProducts();
    }
}
