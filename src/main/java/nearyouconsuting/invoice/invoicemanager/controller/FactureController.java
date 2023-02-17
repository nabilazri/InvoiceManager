package nearyouconsuting.invoice.invoicemanager.controller;

import jakarta.annotation.Resource;
import nearyouconsuting.invoice.invoicemanager.exception.InvoiceNotFoundException;
import nearyouconsuting.invoice.invoicemanager.model.Facture;
import nearyouconsuting.invoice.invoicemanager.model.LigneFacture;
import nearyouconsuting.invoice.invoicemanager.model.Produit;
import nearyouconsuting.invoice.invoicemanager.service.FactureService;
import nearyouconsuting.invoice.invoicemanager.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/facture")
@SessionAttributes("facture")
@CrossOrigin(origins = "*")
public class FactureController {
    @Resource(name = "factureService")
    private FactureService factureService;

    @Autowired
    private ProduitService produitService;

    @GetMapping("/creer")
    public String showRegistration(){
        return "creerFacture";
    }

    @GetMapping("/")
    public String creerFacture() {
        return null;
    }

    @ModelAttribute
    public void getAllProduits(Model model) {
        model.addAttribute("listProduits", produitService.getAllProduits());
    }

    /*@GetMapping(value = "/charger-produit/{id}", produces = { "application/json" })
    public @ResponseBody Produit chargerProduit(@PathVariable Long id) {
        return produitService.findProduitById(id);
    }*/

    @GetMapping(value = "/charger-produit/{id}", produces = { "application/json" })
    public @ResponseBody Produit selectProduit(@PathVariable Long id) {
        return produitService.findProduitById(id);
    }

    @PostMapping("/sauvegarder")
    public String saveInvoice(@ModelAttribute Facture facture, BindingResult result, Model model,
                              @RequestParam(name = "item_id[]", required = false) Long[] itemId,
                              @RequestParam(name = "quantité[]", required = false) int[] quantité,
                              RedirectAttributes flash){
        if (result.hasErrors()) {
            model.addAttribute("title", "Creer Facture");
            return "creerFacture";
        }

        if (itemId == null || itemId.length == 0) {
            model.addAttribute("title", "Creer Facture");
            model.addAttribute("error", "Error: La facture doit contenir au moins une ligne!");
            flash.addFlashAttribute("error", "Error: La facture doit contenir au moins une ligne!");
            return "creerFacture";
        }

        for (int i = 0; i < itemId.length; i++) {
            Produit produit = produitService.findProduitById(itemId[i]);
            LigneFacture ligneFacture = new LigneFacture();
            ligneFacture.setQuantity(quantité[i]);
            ligneFacture.setProduit(produit);
            facture.addItemFacture(ligneFacture);
            //log.info("ID: " + itemId[i].toString() + ", quantité: " + quantité[i].toString());
        }

        factureService.saveInvoice(facture);
        Long id = factureService.saveInvoice(facture).getId();
        String message = "La facture : " + id + " ajoutée avec succès!";
        model.addAttribute("message", message);
        return "creerFacture";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerFacture(@PathVariable Long id, RedirectAttributes flash) {

        Facture facture = factureService.getInvoiceById(id);

        if (facture != null) {
            factureService.deleteInvoiceById(id);
            flash.addFlashAttribute("success", "Facture supprimée!");
            return "redirect:/home";
        }

        flash.addFlashAttribute("error", "La facture n'éxiste pas!");
        return "redirect:/factures";

    }

    @GetMapping("/all/factures")
    public String getAllInvoices(
            @RequestParam(value = "message", required = false) String message,
            Model model
    ) {
        List<Facture> factures = factureService.getAllInvoices();
        model.addAttribute("list", factures);
        model.addAttribute("message", message);
        return "factures";
    }

    @GetMapping("/consulter")
    public String consulterFacture(@RequestParam Long id, RedirectAttributes attributes,
                                   Model model){
        try {
            Facture facture = factureService.getInvoiceById(id);
            model.addAttribute("facture", facture);
        } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "consulterFacture";
    }

    @GetMapping("/editerFacture")
    public String updateInvoicePage(Model model, RedirectAttributes attributes,
                                @RequestParam Long id){
        String page = null;
        try {
            Facture facture = factureService.getInvoiceById(id);
            model.addAttribute("invoice", facture);
            page = "updateInvoicePage";
        } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:factures";
        }
        return page;
    }

    @PostMapping("/modifier")
    public String updateInvoice(@ModelAttribute Facture facture, RedirectAttributes attributes){
        factureService.updateInvoice(facture);
        Long id = facture.getId();
        attributes.addAttribute("message",
                "La facture : " + id + " modifiée avec succès");
        return "redirect:factures";
    }

    @GetMapping("/supprimer")
    public String deleteInvoice(@RequestParam Long id, RedirectAttributes attributes){
        try {
            factureService.deleteInvoiceById(id);
            attributes.addAttribute("message",
                    "La facture " + id + " a été supprimée avec succès!");
        } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:factures";
    }

}
