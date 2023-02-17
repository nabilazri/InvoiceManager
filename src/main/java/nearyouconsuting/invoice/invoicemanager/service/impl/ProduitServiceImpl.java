package nearyouconsuting.invoice.invoicemanager.service.impl;

import nearyouconsuting.invoice.invoicemanager.model.Produit;
import nearyouconsuting.invoice.invoicemanager.repository.ProduitDao;
import nearyouconsuting.invoice.invoicemanager.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "produitService")
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitDao produitDao;

    @Override
    public Produit sauvegarderProduit(Produit produit) {
        return produitDao.save(produit);
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitDao.findAll();
    }

    @Override
    public Produit findProduitById(Long id) {
        return produitDao.findById(id).orElse(null);
    }

    @Override
    public void supprimerProduitById(Long id) {

    }

    @Override
    public void modifierProduit(Produit produit) {

    }
}
