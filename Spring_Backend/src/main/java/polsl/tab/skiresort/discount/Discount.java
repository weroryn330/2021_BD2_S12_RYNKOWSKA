package polsl.tab.skiresort.discount;

import polsl.tab.skiresort.pass.Pass;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idDiscount;

    @NotBlank(message = "Your discount percentage should not be empty!")
    private Integer percentage;

    @NotBlank(message = "Your discount uses should not be empty!")
    private Integer uses;

    @NotBlank(message = "Your discount length should not be empty!")
    private Integer length;

    @OneToOne(mappedBy = "discountIdDiscount")
    private Pass pass;

    public Integer getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(Integer idDiscount) {
        this.idDiscount = idDiscount;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getUses() {
        return uses;
    }

    public void setUses(Integer uses) {
        this.uses = uses;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }
}
