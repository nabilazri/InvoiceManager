package nearyouconsuting.invoice.invoicemanager.service.impl;

import nearyouconsuting.invoice.invoicemanager.model.Client;
import nearyouconsuting.invoice.invoicemanager.model.Produit;
import nearyouconsuting.invoice.invoicemanager.repository.ClientDao;
import nearyouconsuting.invoice.invoicemanager.repository.ProduitDao;
import nearyouconsuting.invoice.invoicemanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ProduitDao produitDao;

    @Override
    @Transactional (readOnly = true)
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    @Transactional (readOnly = true)
    public Client findOne(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    @Transactional (readOnly = true)
    public void delete(Long id) {
        clientDao.deleteById(id);
    }

    @Override
    @Transactional (readOnly = true)
    public Produit findProduitById(Long id) {
        return produitDao.findById(id).orElse(null);
    }
}
