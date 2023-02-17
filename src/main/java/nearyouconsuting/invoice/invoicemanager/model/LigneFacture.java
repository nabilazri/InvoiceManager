package nearyouconsuting.invoice.invoicemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class LigneFacture implements Serializable {
    private static final double TVA_AMOUNT = 0.2;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private String name;
    private double unitPrice;
    private double totalPrice;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Produit produit;

    public double getUnitPrice(double price) {
        return this.unitPrice = produit.getPrice();
    }

    public double getTotal(){
        return quantity * unitPrice;
    }

    public double getTotalTTC() {
        return totalPrice + totalPrice * TVA_AMOUNT;
    }
}
