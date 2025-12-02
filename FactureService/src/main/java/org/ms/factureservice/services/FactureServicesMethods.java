package org.ms.factureservice.services;

import org.ms.factureservice.clients.CustomerServiceClient;
import org.ms.factureservice.clients.ProductServiceClient;
import org.ms.factureservice.entities.Facture;
import org.ms.factureservice.models.Customer;
import org.ms.factureservice.models.Product;
import org.ms.factureservice.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FactureServicesMethods {

    @Autowired
    private FactureRepository repo;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    // Récupérer toutes les factures
    public List<Facture> findAll() {
        List<Facture> factures = repo.findAll();

        factures.forEach(f -> {

            // Vérifier client
            if (f.getClientId() != null) {
                try {
                    Customer c = customerServiceClient.getCustomerById(f.getClientId());
                    if (c == null)
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Client inexistant (id=" + f.getClientId() + ")");
                    f.setClient(c);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Client inexistant (id=" + f.getClientId() + ")");
                }
            }

            // Vérifier produits
            if (f.getFactureligne() != null) {
                f.getFactureligne().forEach(l -> {
                    if (l.getProduitID() != null) {
                        try {
                            Product p = productServiceClient.getProductById(l.getProduitID());
                            if (p == null)
                                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Produit inexistant (id=" + l.getProduitID() + ")");
                            l.setProduit(p);
                        } catch (Exception e) {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Produit inexistant (id=" + l.getProduitID() + ")");
                        }
                    }
                });
            }
        });

        return factures;
    }

    // Sauvegarder une facture
    public Facture save(Facture f) {

        // Vérification du client avant sauvegarde
        if (f.getClientId() != null) {
            try {
                Customer c = customerServiceClient.getCustomerById(f.getClientId());
                if (c == null)
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Client inexistant (id=" + f.getClientId() + ")");
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Client inexistant (id=" + f.getClientId() + ")");
            }
        }

        // Vérification de chaque produit avant sauvegarde
        if (f.getFactureligne() != null) {
            f.getFactureligne().forEach(l -> {
                if (l.getProduitID() != null) {
                    try {
                        Product p = productServiceClient.getProductById(l.getProduitID());
                        if (p == null)
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Produit inexistant (id=" + l.getProduitID() + ")");
                    } catch (Exception e) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produit inexistant (id=" + l.getProduitID() + ")");
                    }
                }
            });
        }

        // Lier les lignes à la facture avant sauvegarde
        if (f.getFactureligne() != null) {
            f.getFactureligne().forEach(ligne -> {
                ligne.setFacture(f);
                ligne.setId(null);
            });
        }

        // Sauvegarde
        Facture saved = repo.save(f);

        // Peupler client
        if (saved.getClientId() != null) {
            saved.setClient(customerServiceClient.getCustomerById(saved.getClientId()));
        }

        // Peupler produits
        if (saved.getFactureligne() != null) {
            saved.getFactureligne().forEach(l -> {
                if (l.getProduitID() != null) {
                    l.setProduit(productServiceClient.getProductById(l.getProduitID()));
                }
            });
        }

        return saved;
    }

    public List<Customer> getCustomersFromCustomerService() {
        return customerServiceClient.getCustomers();
    }

    public List<Product> getProductsFromProductService() {
        return productServiceClient.getProducts();
    }
}