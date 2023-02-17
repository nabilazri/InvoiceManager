package nearyouconsuting.invoice.invoicemanager.service;

import nearyouconsuting.invoice.invoicemanager.model.Client;
import nearyouconsuting.invoice.invoicemanager.model.Produit;

import java.util.List;

public interface ClientService {
    public List<Client> findAll();
    public void save(Client client);
    public Client findOne(Long id);
    public void delete(Long id);
    public Produit findProduitById(Long id);
}
