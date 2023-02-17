package nearyouconsuting.invoice.invoicemanager.service.impl;

import nearyouconsuting.invoice.invoicemanager.exception.InvoiceNotFoundException;
import nearyouconsuting.invoice.invoicemanager.model.Facture;
import nearyouconsuting.invoice.invoicemanager.repository.FactureDao;
import nearyouconsuting.invoice.invoicemanager.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "factureService")
public class FactureServiceImpl implements FactureService {

    @Autowired
    private FactureDao factureDao;

    @Override
    public Facture saveInvoice(Facture facture) {
        return factureDao.save(facture);
    }

    @Override
    public List<Facture> getAllInvoices() {
        return factureDao.findAll();
    }

    @Override
    public Facture getInvoiceById(Long id) {
        Optional<Facture> optionalInvoice = factureDao.findById(id);
        if (optionalInvoice.isPresent()){
            return optionalInvoice.get();
        } else {
            throw new InvoiceNotFoundException("La facture : " + id + " n'éxiste pas!");
        }
    }

    @Override
    public void deleteInvoiceById(Long id) {
        factureDao.delete(getInvoiceById(id));
    }

    @Override
    public void updateInvoice(Facture facture) {
        factureDao.save(facture);
    }
}
