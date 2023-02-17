package nearyouconsuting.invoice.invoicemanager.repository;

import nearyouconsuting.invoice.invoicemanager.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface ProduitDao extends JpaRepository<Produit, Long> {

}
