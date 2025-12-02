package org.ms.factureservice.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ms.factureservice.models.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Facture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    // Ajouter cascade pour sauvegarder les lignes automatiquement
    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<FactureLigne> factureligne;

    @Transient
    private Customer client;

    private Double remise;
    private Long clientId;
}