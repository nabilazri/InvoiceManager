package nearyouconsuting.invoice.invoicemanager.controller;

import nearyouconsuting.invoice.invoicemanager.model.Produit;
import nearyouconsuting.invoice.invoicemanager.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produit")
@SessionAttributes("produit")
@CrossOrigin(origins = "*")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping("/creer")
    public String creerProduit() {
        return "creerProduit";
    }

    @PostMapping("/sauvegarder")
    public String sauvegarderProduit(@ModelAttribute Produit produit, BindingResult result,
                              Model model){
        produitService.sauvegarderProduit(produit);
        Long id = produitService.sauvegarderProduit(produit).getId();
        String message = "Le produit : " + id + " ajouté avec succès!";
        model.addAttribute("message", message);
        return "creerProduit";
    }

    @GetMapping(value = "/all/produits", produces = {"application/json"})
    public String getProduits(@RequestParam(value = "message", required = false) String message,
                              Model model) {
        List<Produit> produits = produitService.getAllProduits();
        model.addAttribute("list", produits);
        model.addAttribute("message", message);
        return "produits";
    }

    @GetMapping(value = "/charger-produit/{id}", produces = { "application/json" })
    public @ResponseBody Produit chargerProduit(@PathVariable Long id) {
        return produitService.findProduitById(id);
    }

}
