package nearyouconsuting.invoice.invoicemanager.service;

import nearyouconsuting.invoice.invoicemanager.model.Facture;
import nearyouconsuting.invoice.invoicemanager.model.Produit;

import java.util.List;

public interface ProduitService {
    Produit sauvegarderProduit(Produit produit);
    List<Produit> getAllProduits();
    Produit findProduitById(Long id);
    void supprimerProduitById(Long id);
    void modifierProduit(Produit produit);
}
