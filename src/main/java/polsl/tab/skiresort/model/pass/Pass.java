package polsl.tab.skiresort.model.pass;

import polsl.tab.skiresort.model.invoiceitem.InvoiceItem;
import polsl.tab.skiresort.model.skilift.SkiLift;
import polsl.tab.skiresort.model.spa.Spa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pass")
public class Pass {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer idPass;

    private Float unitPrice;

    @ManyToOne
    @JoinColumn(name = "id_ski_lift")
    private SkiLift idSkiLift;

    @ManyToOne
    @JoinColumn(name = "id_spa")
    private Spa idSpa;

    @OneToMany(targetEntity = InvoiceItem.class)
    private List<InvoiceItem> invoiceItemsList;

    public Integer getIdPass() {
        return idPass;
    }

    public void setIdPass(Integer idPass) {
        this.idPass = idPass;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public SkiLift getIdSkiLift() {
        return idSkiLift;
    }

    public void setIdSkiLift(SkiLift idSkiLift) {
        this.idSkiLift = idSkiLift;
    }

    public Spa getIdSpa() {
        return idSpa;
    }

    public void setIdSpa(Spa idSpa) {
        this.idSpa = idSpa;
    }

    public List<InvoiceItem> getInvoiceItemsList() {
        return invoiceItemsList;
    }

    public void setInvoiceItemsList(List<InvoiceItem> invoiceItemsList) {
        this.invoiceItemsList = invoiceItemsList;
    }
}
