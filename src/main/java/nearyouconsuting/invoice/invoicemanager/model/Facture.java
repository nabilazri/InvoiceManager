package nearyouconsuting.invoice.invoicemanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data @AllArgsConstructor
public class Facture implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date dateCreation;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Client client;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "facture_id")
    private List<LigneFacture> ligneFactures;

    @PrePersist
    public void prePersist(){
        dateCreation = new Date();
    }

    public Facture(){
        this.ligneFactures = new ArrayList<LigneFacture>();
    }

    public void addItemFacture(LigneFacture items) {
        this.ligneFactures.add(items);
    }

}
