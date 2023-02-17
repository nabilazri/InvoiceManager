package nearyouconsuting.invoice.invoicemanager.repository;

import nearyouconsuting.invoice.invoicemanager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ClientDao extends JpaRepository<Client, Long> {
}
