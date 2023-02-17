package nearyouconsuting.invoice.invoicemanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor
public class Client implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String Address;
    private String phoneNumber;
    private String ice;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @JsonFormat(pattern = "dd-mm-yyyy")
    @Column(name = "created_at")
    private Date date;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Facture> factures;

    public Client() {
        factures = new ArrayList<Facture>();
    }
}
