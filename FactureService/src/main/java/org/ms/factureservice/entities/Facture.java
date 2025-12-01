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
    @GeneratedValue
    private Long id;

    private LocalDate date;

    @OneToMany(mappedBy = "facture")
    private Collection<FactureLigne> factureligne;

    @Transient
    private Customer client;


    private Double remise;
    private Long clientId;

}
