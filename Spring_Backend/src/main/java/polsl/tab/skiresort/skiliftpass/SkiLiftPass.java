package polsl.tab.skiresort.skiliftpass;

import polsl.tab.skiresort.pass.Pass;
import polsl.tab.skiresort.skilift.SkiLift;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "ski_lift_pass")
public class SkiLiftPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer passIdInvoiceItem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer skiLiftIdSkiLift;

    @NotBlank(message = "Your ski lift pass uses should not be empty!")
    private Integer uses;

    @ManyToOne()
    @JoinColumn(name = "pass_id_invoice_item")
    private Pass pass;

    @ManyToOne()
    @JoinColumn(name = "ski_lift_id_ski_lift")
    private SkiLift skiLift;

    public Integer getPassIdInvoiceItem() {
        return passIdInvoiceItem;
    }

    public void setPassIdInvoiceItem(Integer passIdInvoiceItem) {
        this.passIdInvoiceItem = passIdInvoiceItem;
    }

    public Integer getSkiLiftIdSkiLift() {
        return skiLiftIdSkiLift;
    }

    public void setSkiLiftIdSkiLift(Integer skiLiftIdSkiLift) {
        this.skiLiftIdSkiLift = skiLiftIdSkiLift;
    }

    public Integer getUses() {
        return uses;
    }

    public void setUses(Integer uses) {
        this.uses = uses;
    }

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }

    public SkiLift getSkiLift() {
        return skiLift;
    }

    public void setSkiLift(SkiLift skiLift) {
        this.skiLift = skiLift;
    }
}
