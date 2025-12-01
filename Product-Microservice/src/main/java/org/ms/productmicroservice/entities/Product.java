package org.ms.prodcutmicroservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")   // remplace @Entity
public class Product {

    @Id   // MongoDB ID — ne PAS importer jakarta.persistence.Id
    private String id;

    private String label;
    private double price;

    // Versionning avec Mongo (optimistic locking)
    @org.springframework.data.annotation.Version
    private Integer version;
}
