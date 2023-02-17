package nearyouconsuting.invoice.invoicemanager.repository;

import nearyouconsuting.invoice.invoicemanager.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface FactureDao extends JpaRepository<Facture, Long> {

}
