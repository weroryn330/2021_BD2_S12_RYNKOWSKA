package polsl.tab.skiresort.usage;

import polsl.tab.skiresort.pass.Pass;
import polsl.tab.skiresort.pricelist.PriceList;
import polsl.tab.skiresort.skilift.SkiLift;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "usages")
public class Usage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idUsage;

    @ManyToOne
    @JoinColumn(name = "passes_id_invoice_item")
    @NotNull
    private Pass passesIdInvoiceItem;

    @ManyToOne
    @JoinColumn(name = "ski_lift_id_ski_lift")
    @NotNull
    private SkiLift skiLiftIdSkiLift;

    @NotBlank(message = "Your usage use timestamp should not be empty!")
    private Timestamp useTimestamp;

    @NotBlank(message = "Your usage success flag should not be empty!")
    private Character successFlag;

    public Integer getIdUsage() {
        return idUsage;
    }

    public void setIdUsage(Integer idUsage) {
        this.idUsage = idUsage;
    }

    public Pass getPassesIdInvoiceItem() {
        return passesIdInvoiceItem;
    }

    public void setPassesIdInvoiceItem(Pass passesIdInvoiceItem) {
        this.passesIdInvoiceItem = passesIdInvoiceItem;
    }

    public SkiLift getSkiLiftIdSkiLift() {
        return skiLiftIdSkiLift;
    }

    public void setSkiLiftIdSkiLift(SkiLift skiLiftIdSkiLift) {
        this.skiLiftIdSkiLift = skiLiftIdSkiLift;
    }

    public Timestamp getUseTimestamp() {
        return useTimestamp;
    }

    public void setUseTimestamp(Timestamp useTimestamp) {
        this.useTimestamp = useTimestamp;
    }

    public Character getSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(Character successFlag) {
        this.successFlag = successFlag;
    }
}
