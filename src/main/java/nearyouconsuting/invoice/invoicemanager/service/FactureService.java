package nearyouconsuting.invoice.invoicemanager.service;

import nearyouconsuting.invoice.invoicemanager.model.Facture;

import java.util.List;

public interface FactureService {
    public Facture saveInvoice(Facture facture);
    public List<Facture> getAllInvoices();
    public Facture getInvoiceById(Long id);
    public void deleteInvoiceById(Long id);
    public void updateInvoice(Facture facture);
}
