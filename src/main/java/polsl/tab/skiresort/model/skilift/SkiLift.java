package polsl.tab.skiresort.model.skilift;

import polsl.tab.skiresort.model.invoiceitem.InvoiceItem;
import polsl.tab.skiresort.model.pass.Pass;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ski_lift")
public class SkiLift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idSkiLift;

    private String name;

    @OneToMany(mappedBy = "idSkiLift")
    private List<Pass> passList;

    public Integer getIdSkiLift() {
        return idSkiLift;
    }

    public void setIdSkiLift(Integer idSkiLift) {
        this.idSkiLift = idSkiLift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pass> getPassList() {
        return passList;
    }

    public void setPassList(List<Pass> passList) {
        this.passList = passList;
    }
}
