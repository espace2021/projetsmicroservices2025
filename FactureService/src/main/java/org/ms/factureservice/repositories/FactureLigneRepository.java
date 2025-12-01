package org.ms.factureservice.repositories;

import org.ms.factureservice.entities.FactureLigne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import java.util.Collection;

@RepositoryRestController
public interface FactureLigneRepository extends
        JpaRepository<FactureLigne, Long> {
    Collection<FactureLigne> findByFactureId(Long factureId);
}
