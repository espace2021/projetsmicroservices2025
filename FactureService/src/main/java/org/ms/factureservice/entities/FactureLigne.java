package org.ms.factureservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ms.factureservice.models.Product;

import jakarta.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class FactureLigne {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String produitID; // MongoDB ObjectId → String
    private long quantity;
    private double price;

    @Transient
    private Product produit;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Facture facture;
}

